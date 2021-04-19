package ru.tinkoff.tarasov.http

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/department")
class DepartmentService {
    val departments = mapOf(
        Pair(1, Department(1, "Physics")),
        Pair(2, Department(2, "Computer Science")),
        Pair(3, Department(3, "Linguistic")),
    )

    @GetMapping("/{id}")
    fun getDepartmentById(@PathVariable id: String): ResponseEntity<Department> {
        val maybeDepartment = Optional.ofNullable(departments[id.toInt()])

        return if (maybeDepartment.isPresent) {
            ResponseEntity.ok(maybeDepartment.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
}