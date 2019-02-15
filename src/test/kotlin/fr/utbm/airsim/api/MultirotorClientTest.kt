package fr.utbm.airsim.api

import org.msgpack.rpc.Client
import org.msgpack.rpc.loop.EventLoop
import java.lang.Math.cos
import java.lang.Math.sin
import java.lang.Thread.sleep

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

            println("QuadCopter will done start doing circles...")

            var e = 0.0
            val dt = 0.1
            val amplitude = 10

            while(true) {
                multirotorClient.moveByVelocityAsync(amplitude * cos(e).toFloat(), amplitude * sin(e).toFloat(), 0f, dt.toFloat())
                e += 0.05
                sleep((dt * 1000).toLong())
            }
        }
    }
}