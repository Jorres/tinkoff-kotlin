package ru.tinkoff.tarasov.dockerfirst

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerFirstApplication

fun main(args: Array<String>) {
	runApplication<DockerFirstApplication>(*args)
}
