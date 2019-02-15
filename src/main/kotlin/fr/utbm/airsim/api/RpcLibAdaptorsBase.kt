package fr.utbm.airsim.api

import fr.utbm.airsim.api.annotations.SerialName
import org.msgpack.annotation.Message

@Message
data class KinematicsState(
        var position: Vector3r = Vector3r(),
        var orientation: Quaternionr = Quaternionr(),
        @SerialName("linear_velocity")
        var linearVelocity: Vector3r = Vector3r(),
        @SerialName("angular_velocity")
        var angularVelocity: Vector3r = Vector3r(),
        @SerialName("linear_acceleration")
        var linearAcceleration: Vector3r = Vector3r(),
        @SerialName("angular_acceleration")
        var angularAcceleration: Vector3r = Vector3r()
) : AirSimRpcMessageTrait

@Message
data class Vector3r(
        @SerialName("x_val")
        var x: Float = 0f,
        @SerialName("y_val")
        var y: Float = 0f,
        @SerialName("z_val")
        var z: Float = 0f
) : AirSimRpcMessageTrait

@Message
data class Quaternionr(
        @SerialName("w_val")
        var w: Float = 0f,
        @SerialName("x_val")
        var x: Float = 0f,
        @SerialName("y_val")
        var y: Float = 0f,
        @SerialName("z_val")
        var z: Float = 0f
) : AirSimRpcMessageTrait

@Message
data class EnvironmentState(
        var position: Vector3r = Vector3r(),
        var geoPoint: GeoPoint = GeoPoint(),
        var gravity: Vector3r = Vector3r(),
        var airPressure: Float = 0f,
        var temperature: Float = 0f,
        var airDensity: Float = 0f
) : AirSimRpcMessageTrait

@Message
data class GeoPoint(
        var latitude: Float = 0f,
        var longitude: Float = 0f,
        var altitude: Float = 0f
) : AirSimRpcMessageTrait

@Message
data class ProjectionMatrix(
        var matrix: Array<FloatArray> = arrayOf(
                floatArrayOf(0f, 0f, 0f, 0f),
                floatArrayOf(0f, 0f, 0f, 0f),
                floatArrayOf(0f, 0f, 0f, 0f),
                floatArrayOf(0f, 0f, 0f, 0f))
) : AirSimRpcMessageTrait {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProjectionMatrix

        if (!matrix.contentDeepEquals(other.matrix)) return false

        return true
    }

    override fun hashCode(): Int {
        return matrix.contentDeepHashCode()
    }
}

@Message
data class CollisionInfo(
        @SerialName("has_collided")
        var hasCollided: Boolean = false,
        var normal: Vector3r = Vector3r(),
        @SerialName("impact_point")
        var impactPoint: Vector3r = Vector3r(),
        var position: Vector3r = Vector3r(),
        @SerialName("penetration_depth")
        var penetrationDepth: Float = 0f,
        var timestamp: Long = 0,
        @SerialName("collision_count")
        var collisionCount: Int = 0,
        @SerialName("object_name")
        var objectName: String = "",
        @SerialName("object_id")
        var objectId: Int = -1
) : AirSimRpcMessageTrait

@Message
data class RcData(
        var timestamp: Long = 0,
        var pitch: Float = 0f,
        var roll: Float = 0f,
        var throttle: Float = 0f,
        var yaw: Float = 0f,
        var leftZ: Float = 0f,
        var rightZ: Float = 0f,
        var switches: Short = 0,
        var vendorId: String = "",
        var isInitialized: Boolean = false,
        var isValid: Boolean = false
) : AirSimRpcMessageTrait