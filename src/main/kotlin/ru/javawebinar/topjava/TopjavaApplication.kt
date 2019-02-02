package ru.javawebinar.topjava

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TopjavaApplication

fun main(args: Array<String>) {
    print("Hello Topjava Enterprise!")
    runApplication<TopjavaApplication>(*args)
}

