/**
 * Representation of some purchase record.
 * @param id - unique identifier of the purchase.
 * @param timestamp - commodity which participated in the purchase.
 * @param quantity - volume of the purchase in units.
 */
data class PurchaseRecord(val id: Int, val quantity: Int, val timestamp: Int)

/**
 * DAO object, supplying a list of purchase records.
 */
class SetOfPurchaseDetails {
    fun getCollection() = listOf(
        PurchaseRecord(0, 0, 0),
        PurchaseRecord(1, 42, 42),
        PurchaseRecord(2, 42, 42),
        PurchaseRecord(3, 666, 666),
    )
}

/**
 * Representation of some commodity type.
 * @param id - unique identifier of the commodity.
 * @param price - price tag for this commodity type.
 * @param name - textual description of the commodity type.
 */
data class Commodity(val id: Int, val price: Int, val name: String)

/**
 * DAO emulation, supplying a list of commodities.
 */
class SetOfCommodities {
    fun getCollection() = listOf(
        Commodity(0, 100, "smth cheap"),
        Commodity(1, 200, "smth average"),
        Commodity(2, 300, "smth expensive"),
        Commodity(3, 1000, "smth luxurious")
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

