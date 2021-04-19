package ru.tinkoff.tarasov.http

import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@RestController
@RequestMapping("/student")
class StudentService {
    private val webClient = WebClient.create("http://localhost:8080")

    private val students = mutableMapOf(
        Pair(1, Student(1, "Egor", 1)),
        Pair(2, Student(2, "Vasya", 2)),
        Pair(3, Student(3, "Petya", 3)),
        Pair(4, Student(4, "Vladimir", 1))
    )

    @ApiOperation("Standart ping handler to check health, returns `pong`")
    @GetMapping("/ping")
    fun ping(): String {
        return "pong"
    }

    @ApiOperation("Returns 200 OK and student info in the body if student is found and 404 otherwise.")
    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: String): ResponseEntity<StudentFullInfo> {
        println("Get request on student with id $id")
        val maybeStudent = Optional.ofNullable(students[id.toInt()])
        return if (maybeStudent.isPresent) {
            ResponseEntity.ok(makeStudentFullInfo(maybeStudent.get()))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @ApiOperation("Returns 200 OK and the list of all students in the body.")
    @GetMapping("/all")
    fun getAllStudents(): List<StudentFullInfo> {
        println("Get all request")
        return students.toList().map { (_, student) -> makeStudentFullInfo(student) }
    }

    @ApiOperation(
        "Returns 200 OK and creates a student supplied in the request body if " +
                "student with this id does not exist and 400 otherwise."
    )
    @PutMapping
    fun addStudent(@RequestBody newStudent: Student): ResponseEntity<String> {
        println("Add student request with data = $newStudent")
        return if (students.containsKey(newStudent.id)) {
            ResponseEntity.badRequest().body("Student with supplied id ${newStudent.id} already exists")
        } else {
            students[newStudent.id] = newStudent
            ResponseEntity.ok("Student successfully created")
        }
    }

    @ApiOperation(
        "Returns 200 OK and deletes a student with supplied id " +
                "or 400 if the student with supplied id does not exist."
    )
    @DeleteMapping("/{sid}")
    fun deleteStudent(@PathVariable sid: String): ResponseEntity<String> {
        val id = sid.toInt()
        println("Delete student request with id = $id")
        return if (students.containsKey(id)) {
            students.remove(id)
            ResponseEntity.ok("Student successfully removed")
        } else {
            ResponseEntity.badRequest().body("Student with supplied id $id does not exist")
        }
    }

    @ApiOperation(
        "Updates student record. Accepts student description in the body. Returns 200 OK" +
                " and creates a student if the record didn't previously exist."
    )
    @PostMapping
    fun updateStudent(@RequestBody student: Student): String {
        println("Delete student request with data = $student")
        students[student.id] = student
        return "Student ${student.id} successfully updated"
    }

    /**
     * An assumption is made that any query to [DepartmentService] completes successfully
     * and returns result. Of course it is not always true due to network errors (though here
     * none will occur due to localhost) or 404 errors. But error processing is avoided for simplicity.
     */
    private fun makeStudentFullInfo(student: Student): StudentFullInfo {
        val department = webClient
            .get()
            .uri("/department/" + student.departmentId)
            .retrieve()
            .bodyToMono(Department::class.java)
            .block()
        return StudentFullInfo(student, department!!.name)
    }
}