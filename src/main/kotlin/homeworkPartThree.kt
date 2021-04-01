import java.util.concurrent.Executors

class homeworkPartThree {
    private fun runPool(size: Int, id: Int) {
        val pool = Executors.newFixedThreadPool(size)
        var poolSharedVar = 0
        var logWritten = false
        repeat(size) {
            pool.submit {
                while (true) {
                    synchronized(pool) {
                        if (poolSharedVar == 1_000_000) {
                            if (!logWritten) {
                                logWritten = true
                                println("Pool $id finished its job!")
                            }
                            return@submit
                        }
                        poolSharedVar += 1
                    }
                }
            }
        }

        pool.shutdown()
    }

    fun createPools() {
        listOf(Pair(10, 1), Pair(20, 2), Pair(30, 3)).forEach { (size, id) ->
            runPool(size, id)
        }
    }
}