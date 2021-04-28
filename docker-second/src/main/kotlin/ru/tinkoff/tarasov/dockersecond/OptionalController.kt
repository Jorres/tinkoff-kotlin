package ru.tinkoff.tarasov.dockersecond

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * Controller that provides optional data and communicates with MySQL database.
 * Internal container port is 9091, externally mapped to 9091.
 * TODO: make it connected with database
 */
@RestController
class OptionalController {
    val opt = MyOptionalData("optional")
    @GetMapping("/optional/{id}")
    fun getDepartmentById(@PathVariable id: String): ResponseEntity<MyOptionalData> {
        return ResponseEntity.ok(opt)
    }
}

data class MyOptionalData(val a: String)