package fr.utbm.airsim.api

import org.msgpack.rpc.Client
import org.msgpack.rpc.loop.EventLoop
import java.lang.Math.cos
import java.lang.Math.sin
import java.lang.Thread.sleep

/**
 * Test class for Multirotor client
 */
class MultirotorClientLidarTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val loop = EventLoop.defaultEventLoop()

            val rpcClient = Client("127.0.0.1", 41451, loop)
            val multirotorClient = rpcClient.proxy(MultirotorClientInterface::class.java)

            multirotorClient.enableApiControl(true)

            println("API control enabled: ${multirotorClient.isApiControlEnabled()}")

            val lidarData = multirotorClient.getLidarData("Lidar", "Alpha")

            println("Lidar data received at: ${lidarData.timeStamp}")
        }
    }
}