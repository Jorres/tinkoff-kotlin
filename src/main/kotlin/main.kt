/**
 * Representation of some purchase record.
 * @param timestamp - commodity which participated in the purchase.
 * @param quantity - volume of the purchase in units.
 * @param id - unique identifier of the purchase.
 */
data class PurchaseRecord(val quantity: Int, val timestamp: Int, val id: Int)

/**
 * DAO object, supplying a list of purchase records.
 */
class SetOfPurchaseDetails {
    fun getCollection() = listOf(
        PurchaseRecord(0, 0, 0),
        PurchaseRecord(42, 42, 1),
        PurchaseRecord(42, 42, 2),
        PurchaseRecord(666, 666, 3),
    )
}

/**
 * Representation of some commodity type.
 * @param price - price tag for this commodity type.
 * @param name - textual description of the commodity type.
 * @param id - unique identifier of the commodity.
 */
data class Commodity(val price: Int, val name: String, val id: Int)

/**
 * DAO emulation, supplying a list of commodities.
 */
class SetOfCommodities {
    fun getCollection() = listOf(
        Commodity(100, "smth cheap", 0),
        Commodity(200, "smth average", 1),
        Commodity(300, "smth expensive", 2),
        Commodity(1000, "smth luxurious", 3)
    )
}

/**
 * Representation of the purchase containing only one unit of commodity.
 * @param cost - price tag for the deal.
 * @param name - for convenience, purchase is named the same way as the commodity.
 * @param id - unique identifier of the purchase.
 * @param timestamp - timestamp of the purchase.
 * @param commodityId - which type of commodity was present in the purchase.
 */
data class CompletePurchase(
    val cost: Int,
    val name: String,
    val id: Int,
    val timestamp: Int,
    val commodityId: Int
)

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
            .zip(commodities.getCollection())
            .map { (record, commodity) ->
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
        createNamedPurchases().sortedWith { a, b -> a.name.compareTo(b.name) }

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