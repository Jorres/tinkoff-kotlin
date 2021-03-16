/**
 *  The main part of the homework.
 */
class ServiceClass {
    /**
     * 5a, combines elements from both DAO's.
     */
    fun createNamedPurchases(): List<CompletePurchase> {
        val commodities = SetOfCommodities()
        val purchaseDetails = SetOfPurchaseDetails()

        return purchaseDetails.getCollection()
            .map { record ->
                val commodity = commodities.getCollection()
                    .filter { (id) -> id == record.id }
                    .firstOrNull() ?: throw NoSuchElementException("No corresponding commodity found")

                CompletePurchase(
                    commodity.price * record.quantity,
                    commodity.name,
                    record.id,
                    record.timestamp,
                    commodity.id
                )
            }
    }

    /**
     * 5b, sorts objects from 5a by commodity name.
     */
    fun sortNamedPurchasesByName(): List<CompletePurchase> =
        createNamedPurchases().sortedBy(CompletePurchase::name)

    /**
     * 5c, groups objects from 5a by timestamp of the purchase.
     */
    fun groupByTimestamp(): Map<Int, List<CompletePurchase>> =
        createNamedPurchases().groupBy(CompletePurchase::timestamp)

    /**
     * 5d, filters objects from 5a by supplied predicate.
     */
    fun myFilter(p: (CompletePurchase) -> Boolean): List<CompletePurchase> =
        createNamedPurchases().filter(p)
}

fun main() {
    test()
}