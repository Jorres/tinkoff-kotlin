import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

/**
 *  I was hoping to make an experiment that would demonstrate how priorities work.
 *  I have set up 2 * [numberOfThreads] threads performing heavy calculation,
 *  first half with the highest priority, second half with the lowest.
 *  What I wanted to see is how many of the low priority threads are still running
 *  when the last high priority thread finishes.
 *
 *  Unfortunately, the experiment failed, priority did not reflect anyhow on the
 *  performance of the groups. They were finishing almost at the same time.
 *  Perhaps the experiment was not large enough OR/AND I do not fully understand
 *  Java/Kotlin scheduling.
 *
 *  Anyway this is at least the example of creating threads with different priorities.
 *
 *  By the way, this example also counts as DSL thread creation (I'm using Kotlin's
 *  [thread]  function).
 */
class DemonstratePriorities {
    private val numberOfThreads = 1000
    private val highPriorityRem = AtomicInteger(numberOfThreads)
    private val lowPriorityRem = AtomicInteger(numberOfThreads)

    private var logWritten = false

    private fun doSomeWork(remaining: AtomicInteger) {
        Thread.sleep(10)

        synchronized(this) {
            if (highPriorityRem.get() == 0) {
                logWritten = true
                println("Low priority remaining: " + lowPriorityRem.get())
            }
        }

        remaining.decrementAndGet()
    }

    fun demonstratePriorities() {
        val highPriorityThreads = List(numberOfThreads) {
            thread(start = false, priority = 10) {
                doSomeWork(highPriorityRem)
            }
        }

        val lowPriorityThreads = List(numberOfThreads) {
            thread(start = false, priority = 1) {
                doSomeWork(lowPriorityRem)
            }
        }

        highPriorityThreads.zip(lowPriorityThreads).forEach { (h, l) ->
            h.start()
            l.start()
        }
    }
}
