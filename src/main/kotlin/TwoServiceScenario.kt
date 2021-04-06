import kotlinx.coroutines.*

class RequiredSupplier {
    suspend fun getRequiredInfo(): String {
        delay(3000)
        return "Required info"
    }
}

class OptionalSupplier {
    suspend fun getOptionalInfo(shouldBeSlow: Boolean): String {
        if (shouldBeSlow) {
            delay(5000)
        } else {
            delay(3000)
        }
        return "Optional info"
    }
}

/**
 *  Note on parallelism in the task:
 *
 *  Queries for resources are running in parallel.
 *  Each scenario makes two queries.
 *  Each query contains a delay of three seconds.
 *  Each scenario completes in about three seconds.
 *  If they were running concurrently, it would take
 *  for scenario around six seconds to complete.
 */
class Combiner {
    suspend fun launchScenario(): Pair<String, String?> {
        val reqInfoFuture = GlobalScope.async {
            RequiredSupplier().getRequiredInfo()
        }

        val fastOptionalInfo = withTimeoutOrNull(4000L) {
            OptionalSupplier().getOptionalInfo(false)
        }

        val reqInfo = reqInfoFuture.await()
        return Pair(reqInfo, fastOptionalInfo)
    }

    suspend fun demonstrateOptionality(): Pair<String, String?> {
        val reqInfoFuture = GlobalScope.async {
            RequiredSupplier().getRequiredInfo()
        }

        val slowOptionalInfo = withTimeoutOrNull(4000L) {
            OptionalSupplier().getOptionalInfo(true)
        }

        val reqInfo = reqInfoFuture.await()
        return Pair(reqInfo, slowOptionalInfo)
    }
}