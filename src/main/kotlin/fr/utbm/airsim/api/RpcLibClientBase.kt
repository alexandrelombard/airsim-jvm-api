package fr.utbm.airsim.api

import org.msgpack.annotation.Message

/**
 * Base interface of the AirSim clients (for both car and drone)
 * @author Alexandre Lombard
 */
interface RpcLibClientBase {
    fun confirmConnection()
    fun reset()
    fun getConnectionState(): Int
    fun ping(): Boolean
    fun getClientVersion(): Int
    fun getServerVersion(): Int
    fun getMinRequiredServerVersion(): Int
    fun getMinRequiredClientVersion(): Int
    fun simIsPaused(): Boolean
    fun simPause(isPaused: Boolean)
    fun simContinueForTime(seconds: Double)
    fun simEnableWeather(enable: Boolean)
    fun isApiControlEnabled(vehicleName: String = ""): Boolean
    fun enableApiControl(isEnabled: Boolean, vehicleName: String = "")
}

@Message
enum class ConnectionState {
    INITIAL,
    CONNECTED,
    DISCONNECTED,
    RESET,
    UNKNOWN
}