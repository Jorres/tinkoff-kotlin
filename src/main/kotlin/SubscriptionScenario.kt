import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

@ExperimentalCoroutinesApi
fun CoroutineScope.produceMessages(): ReceiveChannel<String> = produce {
    var messageId = 0
    repeat(10) {
        delay(100)
        send("I am a message " + messageId++)
    }
}

fun CoroutineScope.runConsumer(id: Int, channel: ReceiveChannel<String>): Job = launch {
    for (message in channel) {
        println("Consumer $id got message $message")
    }
}

/**
 *  For the sake of an experiment, I made several consumers who all
 *  consume data from a single Channel. It is a bit more interesting
 *  this way.
 */
class SubscriptionScenario {
    suspend fun launchScenario(): Unit = coroutineScope {
        val producer = produceMessages()
        repeat(5) {
            runConsumer(it, producer)
        }
    }
}