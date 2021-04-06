import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println(Combiner().launchScenario())
        println(Combiner().demonstrateOptionality())
    }

    runBlocking {
        SubscriptionScenario().launchScenario()
    }
}