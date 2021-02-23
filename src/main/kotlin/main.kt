interface CleaningRobot {
    val model: String

    fun cleanRoom()

    fun wetCleanRoom() {
        println("This model does not support wet cleaning")
    }
}

// Demonstrates implementing an abstract method in the interface
class SimpleCleaningRobot(override val model: String) : CleaningRobot {
    override fun cleanRoom() {
        println("Simple $model cleaning robot has cleaned your room")
    }
}

// Demonstrates overriding of default method in the interface
class WetCleaningRobot(override val model: String) : CleaningRobot {
    override fun cleanRoom() {
        println("Wet $model cleaning robot has cleaned your room")
    }

    override fun wetCleanRoom() {
        println("Wet $model cleaning robot has WET cleaned your room")
    }
}

// Demonstrates having additional functionality besides interface methods:
// additional method and additional properties
class CameraCleaningRobot(override val model: String,
                          private val cameraResolution: Pair<Int, Int>) : CleaningRobot {
    override fun cleanRoom() {
        println("Camera $model cleaning robot has cleaned your room")
    }

    fun sendRoomView() {
        println("Sending current frame " +
                "${cameraResolution.first}x${cameraResolution.second} " +
                "from camera to the user's smartphone...")
    }
}

fun main() {
    val cameraCleaningRobot = CameraCleaningRobot("Mi C", Pair(1920, 1080))

    val myCleaningRobots = listOf(
        SimpleCleaningRobot("Mi S"),
        WetCleaningRobot("Mi W"),
        cameraCleaningRobot
        // notice that I can put a more precise type
        // into an array typed with its interface
    )

    // calling abstract method on the interface
    myCleaningRobots.forEach{robot -> robot.cleanRoom()}
    println("-----")

    // calling default method on the interface,
    // second output should be overridden
    myCleaningRobots.forEach{robot -> robot.wetCleanRoom()}
    println("-----")

    // calling specific functionality
    cameraCleaningRobot.sendRoomView()
}