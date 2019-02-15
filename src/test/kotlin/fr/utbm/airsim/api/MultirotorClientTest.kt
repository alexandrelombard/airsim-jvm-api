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


        }
    }
}