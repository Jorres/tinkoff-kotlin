data class Library(
    val id: Int,
    val name: String,
    val signedReaders: List<Int>
)

data class Registration(
    val studentId: Int,
    val libraryId: Int
)

data class Book(
    val id: Int,
    val name: String,
    val pageCount: Int
)

data class Reader(
    val id: Int,
    val name: String,
    val favouriteBooks: List<Int>
)



