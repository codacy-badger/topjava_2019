package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceInMemoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
  private static final Logger log = getLogger(MealServlet.class);
  private MealService service;

  @Override
  public void init() throws ServletException {
    super.init();
    service = new MealServiceInMemoryImpl();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    Integer id;
    Meal meal;
    switch (action == null ? "all" : action) {
      case "add":
      case "edit":
        id = getId(request);
        log.debug(id == null ? "Add" : "Edit" + " meal");
        meal = id == null ? NEW_MEAL : service.get(id);
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
        break;
      case "delete":
        id = getId(request);
        log.debug("delete meal");
        service.delete(id);
        response.sendRedirect("meals");
        break;
      case "all":
      default:
        log.debug("redirect to meals");
        request.setAttribute("meals", getFilteredWithExcess(service.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    Integer id = getId(req);
    LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
    String description = req.getParameter("description");
    int calories = Integer.parseInt(req.getParameter("calories"));
    Meal meal;
    if (id == null) {
      meal = new Meal(dateTime, description, calories);
    } else {
      meal = new Meal(id, dateTime, description, calories);
    }
    service.save(meal);
    req.setAttribute("meals", getFilteredWithExcess(service.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY));
    req.getRequestDispatcher("/meals.jsp").forward(req, resp);
  }

  private static Integer getId(HttpServletRequest request) {
    String id = request.getParameter("id");
    return id.isEmpty() ? null : Integer.parseInt(id);
  }
}