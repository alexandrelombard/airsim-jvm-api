package fr.utbm.airsim.api

import org.msgpack.rpc.Client
import org.msgpack.rpc.loop.EventLoop
import java.lang.Thread.sleep

/**
 * Test class for multiple multirotors client
 */
class MultipleMultirotorClientTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val loop = EventLoop.defaultEventLoop()

            val rpcClient = Client("127.0.0.1", 41451, loop)
            val multirotorClient = rpcClient.proxy(MultirotorClientInterface::class.java)

            val drones = arrayListOf("Alpha", "Bravo", "Charly", "Delta", "Echo")

            drones.forEach { multirotorClient.enableApiControl(true, it) }
            drones.forEach { multirotorClient.takeoffAsync(vehicleName = it) }

            sleep(5000)

            while (true) {
                drones.forEach {
                    multirotorClient.moveByVelocityAsync(5f, 0f, 0f, 0.1f, vehicleName = it)
                }
            }
        }
    }
}