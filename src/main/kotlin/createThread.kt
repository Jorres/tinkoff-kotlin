class MyThread : Thread() {
    override fun run() {
        sleep(1000)
        println("Instance of MyThread is running...")
    }
}

fun runMyThread() {
    val myThread = MyThread()
    myThread.start()
    myThread.join()
    println("MyRunnableTask has run.")
}
