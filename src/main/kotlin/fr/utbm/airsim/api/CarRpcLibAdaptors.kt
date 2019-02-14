package fr.utbm.airsim.api

import org.msgpack.annotation.Message

@Message
data class CarControls(
        var throttle: Float = 0f,
        var steering: Float = 0f,
        var brake: Float = 0f,
        var handbrake: Boolean = false,
        var isManualGear: Boolean = false,
        var manualGear: Int = 0,
        var gearImmediate: Boolean = true
)

@Message
data class CarState(
        var speed: Float = 0f,
        var gear: Int = 0,
        var rpm: Float = 0f,
        var maxRpm: Float = 0f,
        var handbrake: Boolean = false,
        var kinematicsState: KinematicsState = KinematicsState(),
        var timestamp: Long = 0
)