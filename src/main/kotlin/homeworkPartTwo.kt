import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class homeworkPartTwo {
    private var sharedMemory = AtomicInteger(0)

    fun launch() {
        val observers = List(3) {
            thread(start = false) {
                for (i in 0 until 10) {
                    println(sharedMemory.get())
                    Thread.sleep(100)
                }
            }
        }

        val modifier = thread(start = false) {
            for (i in 0 until 10) {
                sharedMemory.incrementAndGet()
                Thread.sleep(100)
            }
        }

        observers.forEach { o -> o.start() }
        modifier.start()
    }
}