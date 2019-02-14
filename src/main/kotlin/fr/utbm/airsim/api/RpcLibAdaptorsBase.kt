package fr.utbm.airsim.api

import org.msgpack.annotation.Message

@Message
data class KinematicsState(
        var position: Vector3r = Vector3r(),
        var orientation: Quaternionr = Quaternionr(),
        var linearVelocity: Vector3r = Vector3r(),
        var angularVelocity: Vector3r = Vector3r(),
        var linearAcceleration: Vector3r = Vector3r(),
        var angularAcceleration: Vector3r = Vector3r()
)

@Message
data class Vector3r(
        var x: Float = 0f,
        var y: Float = 0f,
        var z: Float = 0f
)

@Message
data class Quaternionr(
        var w: Float = 0f,
        var x: Float = 0f,
        var y: Float = 0f,
        var z: Float = 0f
)

@Message
data class EnvironmentState(
        var position: Vector3r = Vector3r(),
        var geoPoint: GeoPoint = GeoPoint(),
        var gravity: Vector3r = Vector3r(),
        var airPressure: Float = 0f,
        var temperature: Float = 0f,
        var airDensity: Float = 0f
)

@Message
data class GeoPoint(
        var latitude: Float = 0f,
        var longitude: Float = 0f,
        var altitude: Float = 0f
)

data class ProjectionMatrix(
        var matrix: Array<FloatArray> = arrayOf(
                floatArrayOf(0f, 0f, 0f, 0f),
                floatArrayOf(0f, 0f, 0f, 0f),
                floatArrayOf(0f, 0f, 0f, 0f),
                floatArrayOf(0f, 0f, 0f, 0f))
) {
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