package ru.tinkoff.tarasov.apitask

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApitaskApplication

fun main(args: Array<String>) {
	runApplication<ApitaskApplication>(*args)
}
