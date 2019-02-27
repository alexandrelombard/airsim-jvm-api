package fr.utbm.airsim.api

import com.sun.xml.internal.fastinfoset.util.StringArray
import fr.utbm.airsim.api.common.ImageType
import org.msgpack.annotation.Message
import java.awt.ComponentOrientation

/**
 * Base interface of the AirSim clients (for both car and drone)
 * @author Alexandre Lombard
 */
@kotlin.ExperimentalUnsignedTypes
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

    fun simSetTimeOfDay(isEnabled: Boolean, startDatetime: String = "", isStartDatetimeDst: Boolean = false,
                        celestialClockSpeed: Float = 1f, updateIntervalSecs: Float = 60f, moveSun: Boolean = true)

    fun simEnableWeather(enable: Boolean)
    fun simSetWeatherParameter(param: WeatherParameter, value: Float)

    fun simGetObjectPose(objectName: String): Pose
    fun simSetObjectPose(objectName: String, pose: Pose, teleport: Boolean = true): Boolean

    fun cancelLastTask(vehicleName: String)
    // fun waitOnLastTask(taskResult: Boolean? = null, tiemoutSec: Float = Float.MAX_VALUE)

    fun armDisarm(arm: Boolean, vehicleName: String = ""): Boolean
    fun isApiControlEnabled(vehicleName: String = ""): Boolean
    fun enableApiControl(isEnabled: Boolean, vehicleName: String = "")

    fun getHomeGeoPoint(vehicleName: String = ""): GeoPoint

    fun getLidarData(lidarName: String = "", vehicleName: String = ""): LidarData

    fun simGetVehiclePose(vehicleName: String = ""): Pose
    fun simSetVehiclePose(pose: Pose, ignoreCollision: Boolean, vehicleName: String = "")

    fun simGetImages(request: Array<ImageRequest>, vehicleName: String = ""): Array<ImageResponse>
    fun simGetImage(cameraName: String, type: ImageType, vehicleName: String = "")

    fun simGetCollisionInfo(vehicleName: String = ""): CollisionInfo

    fun simGetCameraInfo(cameraName: String, vehicleName: String = ""): CameraInfo
    fun simSetCameraOrientation(cameraName: String, orientation: Quaternionr, vehicleName: String = "")

    fun simGetGroundTruthKinematics(vehicleName: String = ""): KinematicsState
    fun simGetGroundTruthEnvironment(vehicleName: String = ""): EnvironmentState

    // region APIs to control a character in scene
    fun simCharSetFaceExpression(expressionName: String, value: Float, characterName: String = "")
    fun simCharGetFaceExpression(expressionName: String, characterName: String = ""): Float
    fun simCharGetAvailableFaceExpressions(): StringArray
    fun simCharSetSkinDarkness(value: Float, characterName: String = "")
    fun simCharGetSkinDarkness(characterName: String = ""): Float
    fun simCharSetSkinAgeing(value: Float, characterName: String = "")
    fun simCharGetSkinAgeing(characterName: String = ""): Float
    fun simCharSetHeadRotation(q: Quaternionr, characterName: String = "")
    fun simCharGetHeadRotation(characterName: String = ""): Quaternionr
    fun simCharSetBonePose(boneName: String, pose: Pose, characterName: String = "")
    fun simCharGetBonePose(boneName: String, characterName: String = ""): Pose
    fun simCharResetBonePose(boneName: String, characterName: String = "")
    fun simCharSetFacePreset(presetName: String, value: Float, characterName: String = "")
    fun simSetFacePresets(presets: Map<String, Float>, characterName: String = "")
    fun simSetBonePoses(poses: Map<String, Pose>, characterName: String = "")
    fun simGetBonePoses(boneNames: StringArray, characterName: String = "")
    // endregion
}

@Message
enum class ConnectionState {
    INITIAL,
    CONNECTED,
    DISCONNECTED,
    RESET,
    UNKNOWN
}