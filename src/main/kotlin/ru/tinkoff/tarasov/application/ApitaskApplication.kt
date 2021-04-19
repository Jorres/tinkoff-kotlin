package ru.tinkoff.tarasov.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApitaskApplication

fun main(args: Array<String>) {
    runApplication<ApitaskApplication>(*args)
}
