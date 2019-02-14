package fr.utbm.airsim.api

import org.msgpack.annotation.Message
import org.msgpack.annotation.MessagePackOrdinalEnum

@Message
data class YawMode(
        var isRate: Boolean = true,
        var yawOrRate: Float = 0f
)

@Message
data class MultirotorState(
        val collisionInfo: CollisionInfo = CollisionInfo(),
        val kinematicsStateEstimated: KinematicsState = KinematicsState(),
        val kinematicsStateTrue: KinematicsState = KinematicsState(),
        val gpsLocation: GeoPoint = GeoPoint(),
        val timestamp: Long = 0,
        val landedState: LandedState = LandedState.LANDED,
        val rcData: RcData = RcData(),
        val controllerMessages: Array<String> = arrayOf()
)

@MessagePackOrdinalEnum
enum class LandedState {
    LANDED
}

@MessagePackOrdinalEnum
enum class DrivetrainType {
    MAX_DEGREE_OF_FREEDOM,
    FORWARD_ONLY
}