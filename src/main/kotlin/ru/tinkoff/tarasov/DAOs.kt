package ru.tinkoff.tarasov

data class Library(
    val name: String,
    val signedReaders: List<Int>
)

data class Registration(
    val studentId: Int,
    val libraryId: Int
)

data class Book(
    val name: String,
    val pageCount: Int
)

data class Reader(
    val name: String,
    val favouriteBooks: List<Int>
)



