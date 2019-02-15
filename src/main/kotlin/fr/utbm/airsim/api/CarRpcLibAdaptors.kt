package fr.utbm.airsim.api

import fr.utbm.airsim.api.annotations.SerialName
import org.msgpack.annotation.Message

/**
 * Message for car control
 * @author Alexandre Lombard
 */
@Message
data class CarControls(
        var throttle: Float = 0f,
        var steering: Float = 0f,
        var brake: Float = 0f,
        var handbrake: Boolean = false,
        var isManualGear: Boolean = false,
        var manualGear: Int = 0,
        var gearImmediate: Boolean = true) : AirSimRpcMessageTrait

/**
 * Message for car state
 * @author Alexandre Lombard
 */
@Message
data class CarState(
        var speed: Float = 0f,
        var gear: Int = 0,
        var rpm: Float = 0f,
        @SerialName("maxrpm")
        var maxRpm: Float = 0f,
        var handbrake: Boolean = false,
        @SerialName("kinematics_estimated")
        var kinematicsState: KinematicsState = KinematicsState(),
        var timestamp: Long = 0) : AirSimRpcMessageTrait