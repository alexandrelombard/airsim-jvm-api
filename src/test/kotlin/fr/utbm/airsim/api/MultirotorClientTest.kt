package fr.utbm.airsim.api

import org.msgpack.rpc.Client
import org.msgpack.rpc.loop.EventLoop

/**
 * Test class for Multirotor client
 */
class MultirotorClientTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val loop = EventLoop.defaultEventLoop()

            val rpcClient = Client("127.0.0.1", 41451, loop)
            val multirotorClient = rpcClient.proxy(MultirotorClientInterface::class.java)

            multirotorClient.enableApiControl(true)

            println("API control enabled: ${multirotorClient.isApiControlEnabled()}")

            val vehicleState = multirotorClient.getMultirotorState()
            println(vehicleState)

            if(vehicleState.landedState == LandedState.LANDED) {
                println("QuadCopter is landed, will take off...")
                multirotorClient.takeoffAsync().join()
                println("QuadCopter should now be flying")
            }

            val newState = multirotorClient.getMultirotorState()
            println(newState)

//            multirotorClient.simPause(true)
//            sleep(1000)
//            multirotorClient.simContinueForTime(1.0)
//            sleep(2000)
//            multirotorClient.simContinueForTime(1.0)

//            multirotorClient.simPause(true)
            println("1")
            System.`in`.read()
            multirotorClient.simPause(true)
            println("2")
            System.`in`.read()
            multirotorClient.moveByVelocityAsync(0f, 0f, -5f, 1f)
            println("3")
            System.`in`.read()
            multirotorClient.simContinueForTime(1.0)
            println("4")
            System.`in`.read()
            rpcClient.close()


//            println("QuadCopter will now start doing circles...")
//
//            var e = 0.0
//            val dt = 0.1
//            val amplitude = 10
//
//            while(true) {
//                multirotorClient.moveByVelocityAsync(amplitude * cos(e).toFloat(), amplitude * sin(e).toFloat(), 0f, dt.toFloat())
//                e += 0.05
//                sleep((dt * 1000).toLong())
//            }
        }
    }
}