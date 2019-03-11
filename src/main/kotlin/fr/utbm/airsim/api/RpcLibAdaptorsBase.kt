package fr.utbm.airsim.api

import fr.utbm.airsim.api.annotations.SerialName
import fr.utbm.airsim.api.common.ImageType
import org.msgpack.annotation.Message
import kotlin.math.asin
import kotlin.math.atan2
import kotlin.math.sqrt

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
) : AirSimRpcMessageTrait {
    operator fun plus(v: Vector3r) = Vector3r(x + v.x, y + v.y, z + v.z)
    operator fun minus(v: Vector3r) = Vector3r(x - v.x, y - v.y, z - v.z)
    operator fun times(s: Float) = Vector3r(x * s, y * s, z * s)
    fun cross(v: Vector3r) = Vector3r(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)
    fun dot(v: Vector3r) = x * v.x + y * v.y + z * v.z
    fun squaredLength() = x * x + y * y + z * z
    fun length() = sqrt(squaredLength())
    fun normalized() = this * (1f / length())
}

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
) : AirSimRpcMessageTrait {
    /**
     * Converts this quaternion to its euler angles representation
     */
    fun toEuleurAngles() : Vector3r {
        return Vector3r(
                atan2(2 * y * w - 2 * x * z, 1 - 2 * y * y - 2 * z * z),
                asin(2 * x *  y + 2 * z * w),
                atan2(2 * x * w - 2 * y * z, 1 - 2 * x * x - 2 * z * z)
        )
    }
}

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
data class Pose(
        var position: Vector3r = Vector3r(),
        var orientation: Quaternionr = Quaternionr()
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
        @SerialName("time_stamp")
        var timestamp: Long = 0,
        @SerialName("collision_count")
        var collisionCount: Int = 0,
        @SerialName("object_name")
        var objectName: String = "",
        @SerialName("object_id")
        var objectId: Int = -1
) : AirSimRpcMessageTrait

@Message
data class CameraInfo(
        var pose: Pose = Pose(),
        var fov: Float = 0f,
        @SerialName("proj_mat")
        var projMat: ProjectionMatrix = ProjectionMatrix()
) : AirSimRpcMessageTrait

data class ImageRequest(
        @SerialName("camera_name")
        var cameraName: String = "",
        @SerialName("image_type")
        var imageType: ImageType,
        @SerialName("pixels_as_float")
        var pixelsAsFloat: Boolean = false,
        var compress: Boolean = false
) : AirSimRpcMessageTrait

@Message
data class ImageResponse(
        @SerialName("image_data_uint8")
        var imageDataUint8: Array<Byte> = arrayOf(),
        @SerialName("image_data_float")
        var imageDataFloat: Array<Float> = arrayOf(),
        @SerialName("camera_name")
        var cameraName: String = "",
        @SerialName("camera_position")
        var cameraPosition: Vector3r = Vector3r(),
        @SerialName("camera_orientation")
        var cameraOrientation: Quaternionr = Quaternionr(),
        @SerialName("time_stamp")
        var timeStamp: Long = 0L,
        var message: String = "",
        @SerialName("pixels_as_float")
        var pixelsAsFloat: Boolean = false,
        var compress: Boolean = false,
        var width: Int = 0,
        var height: Int = 0,
        @SerialName("image_type")
        var imageType: ImageType = ImageType.SCENE
) : AirSimRpcMessageTrait

@Message
data class LidarData(
        @SerialName("time_stamp")
        var timeStamp: Long = 0,
        @SerialName("point_cloud")
        var pointCloud: FloatArray = floatArrayOf(),
        var pose: Pose = Pose()
) : AirSimRpcMessageTrait

@Message
data class RcData(
        var timestamp: Long = 0,
        var pitch: Float = 0f,
        var roll: Float = 0f,
        var throttle: Float = 0f,
        var yaw: Float = 0f,
        @SerialName("left_z")
        var leftZ: Float = 0f,
        @SerialName("right_z")
        var rightZ: Float = 0f,
        var switches: Short = 0,
        @SerialName("vendor_id")
        var vendorId: String = "",
        @SerialName("is_initialized")
        var isInitialized: Boolean = false,
        @SerialName("is_valid")
        var isValid: Boolean = false
) : AirSimRpcMessageTrait