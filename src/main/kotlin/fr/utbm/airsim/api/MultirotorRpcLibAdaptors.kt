package fr.utbm.airsim.api

import fr.utbm.airsim.api.annotations.SerialName
import org.msgpack.annotation.Message
import org.msgpack.annotation.MessagePackOrdinalEnum

@Message
data class YawMode(
        @SerialName("is_rate")
        var isRate: Boolean = true,
        @SerialName("yaw_or_rate")
        var yawOrRate: Float = 0f
) : AirSimRpcMessageTrait

@Message
data class MultirotorState(
        @SerialName("collision")
        val collisionInfo: CollisionInfo = CollisionInfo(),
        @SerialName("kinematics_estimated")
        val kinematicsStateEstimated: KinematicsState = KinematicsState(),
        @SerialName("kinematics_state_true")
        val kinematicsStateTrue: KinematicsState = KinematicsState(),
        @SerialName("gps_location")
        val gpsLocation: GeoPoint = GeoPoint(),
        val timestamp: Long = 0,
        @SerialName("landed_state")
        val landedState: LandedState = LandedState.LANDED,
        @SerialName("rc_data")
        val rcData: RcData = RcData(),
        @SerialName("controller_messages")
        val controllerMessages: Array<String> = arrayOf()
) : AirSimRpcMessageTrait

@MessagePackOrdinalEnum
enum class LandedState {
    LANDED,
    FLYING
}

@MessagePackOrdinalEnum
enum class DrivetrainType {
    MAX_DEGREE_OF_FREEDOM,
    FORWARD_ONLY
}