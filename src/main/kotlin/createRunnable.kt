class MyRunnableTask : Runnable {
    override fun run() {
        Thread.sleep(1000)
        println("Instance of MyRunnableTask is running...")
    }
}

fun runMyRunnableTask() {
    val myThread = Thread(MyRunnableTask())
    myThread.start()
    myThread.join()
    println("MyRunnableTask has run.")
}

