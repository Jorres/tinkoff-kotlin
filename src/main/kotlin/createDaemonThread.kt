import kotlin.concurrent.thread

fun runMyDaemonThread() {
    thread(start = true, isDaemon = true) {
        while (true) {
            // As this is daemon thread, infinite loop is not a problem.
            // The application will exit once all user threads will exit.
        }
    }
    println("MyRunnableTask has run.")
}
