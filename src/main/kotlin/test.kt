/**
 * Prints results of the task.
 *
 * I hope it was not necessary to create thorough test suite,
 * it would require a lot of boring copypasting, so I just
 * verified manually. Seems legit.
 */
fun test() {
    val serviceClass = ServiceClass()

    val _5a = serviceClass.createNamedPurchases()
    println(_5a)

    val _5b = serviceClass.sortNamedPurchasesByName()
    println(_5b)

    val _5c = serviceClass.groupByTimestamp()
    println(_5c)

    val _5d = serviceClass.myFilter { p -> p.timestamp <= 42 }
    println(_5d)
}