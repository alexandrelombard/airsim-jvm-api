# JVM API for AirSim

This repository aim to provide a JVM interface to the AirSim API in order
to allow its use with Java, Kotlin, Scala, SARL, etc.

## Organization

The package fr.utbm.airsim.api located in src/main/kotlin contains the class representing the possible
messages and the interface to send or retrieve these messages from AirSim.

The package fr.utbm.airsim.api located in src/test/kotlin contains examples on how to
use this API. 

## Mapped functions

### Common

#### Related to the connection with the API

    fun confirmConnection()
    fun reset()

    fun getConnectionState(): Int
    fun ping(): Boolean
    fun getClientVersion(): Int
    fun getServerVersion(): Int
    fun getMinRequiredServerVersion(): Int
    fun getMinRequiredClientVersion(): Int
    
    fun armDisarm(arm: Boolean, vehicleName: String = ""): Boolean
    fun isApiControlEnabled(vehicleName: String = ""): Boolean
    fun enableApiControl(isEnabled: Boolean, vehicleName: String = "")

#### Control of the simulation

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
    
    fun simPrintLogMessage(message: String, messageParam: String, severity: Int = 0)

#### Getting vehicle data

    fun getHomeGeoPoint(vehicleName: String = ""): GeoPoint

    fun getLidarData(lidarName: String = "", vehicleName: String = ""): LidarData

    fun simSetSegmentationObjectID(meshName: String, objectId: String, isNameRegex: Boolean = true)
    fun simGetSegmentationObjectID(meshName: String)

    fun simGetVehiclePose(vehicleName: String = ""): Pose
    fun simSetVehiclePose(pose: Pose, ignoreCollision: Boolean, vehicleName: String = "")

    fun simGetImages(request: Array<ImageRequest>, vehicleName: String = ""): Array<ImageResponse>
    fun simGetImage(cameraName: String, type: ImageType, vehicleName: String = "")

    fun simGetCollisionInfo(vehicleName: String = ""): CollisionInfo

    fun simGetCameraInfo(cameraName: String, vehicleName: String = ""): CameraInfo
    fun simSetCameraOrientation(cameraName: String, orientation: Quaternionr, vehicleName: String = "")

    fun simGetGroundTruthKinematics(vehicleName: String = ""): KinematicsState
    fun simGetGroundTruthEnvironment(vehicleName: String = ""): EnvironmentState

#### Character control

    fun simCharSetFaceExpression(expressionName: String, value: Float, characterName: String = "")
    fun simCharGetFaceExpression(expressionName: String, characterName: String = ""): Float
    fun simCharGetAvailableFaceExpressions(): Array<String>
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
    fun simGetBonePoses(boneNames: Array<String>, characterName: String = "")

### Car

    fun setCarControls(controls: CarControls, vehicleName: String = "")
    fun getCarState(vehicleName: String = ""): CarState

#### Multirotor (drones)

    fun takeoffAsync(timeoutSec: Float = 20f, vehicleName: String = ""): Future<Void>
    fun landAsync(timeoutSec: Float = 60f, vehicleName: String = ""): Future<Void>
    fun goHomeAsync(timeoutSec: Float = Float.MAX_VALUE, vehicleName: String = ""): Future<Void>

    fun moveByAngleZAzync(pitch: Float, roll: Float, z: Float, yaw: Float, duration: Float, vehicleName: String = ""): Future<Void>
    fun moveByAngleThrottleAsync(pitch: Float, roll: Float, z: Float, yaw: Float, duration: Float, vehicleName: String = ""): Future<Void>
    fun moveByVelocityAsync(vx: Float, vy: Float, vz: Float, duration: Float,
                            drivetrainType: DrivetrainType = DrivetrainType.MAX_DEGREE_OF_FREEDOM,
                            yawMode: YawMode = YawMode(), vehicleName: String = ""): Future<Void>
    fun moveByVelocityZAsync(vx: Float, vy: Float, z: Float, duration: Float,
                             drivetrain: DrivetrainType = DrivetrainType.MAX_DEGREE_OF_FREEDOM, yawMode: YawMode = YawMode(),
                             vehicleName: String = ""): Future<Void>
    fun moveOnPathAsync(path: Array<Vector3r>, velocity: Float, timeoutSec: Float = Float.MAX_VALUE,
                        drivetrainType: DrivetrainType = DrivetrainType.MAX_DEGREE_OF_FREEDOM, yawMode: YawMode = YawMode(),
                        lookahead: Float = -1f, adaptiveLookahead: Float = -1f, vehicleName: String = ""): Future<Void>
    fun moveToPositionAsync(x: Float, y: Float, z: Float, velocity: Float, timeoutSec: Float = Float.MAX_VALUE,
                            drivetrainType: DrivetrainType = DrivetrainType.MAX_DEGREE_OF_FREEDOM, yawMode: YawMode = YawMode(),
                            lookahead: Float = -1f, adaptiveLookahead: Float = -1f, vehicleName: String = ""): Future<Void>
    fun moveToZAsync(z: Float, velocity: Float, timeoutSec: Float = Float.MAX_VALUE,
                     drivetrainType: DrivetrainType = DrivetrainType.MAX_DEGREE_OF_FREEDOM, yawMode: YawMode = YawMode(),
                     vehicleName: String = ""): Future<Void>
    fun moveByManualAsync(vxMax: Float, vyMax: Float, zMin: Float, duration: Float,
                          drivetrainType: DrivetrainType = DrivetrainType.MAX_DEGREE_OF_FREEDOM, yawMode: YawMode = YawMode(),
                          vehicleName: String = ""): Future<Void>
    fun rotateToYawAsync(yaw: Float, timeoutSec: Float = Float.MAX_VALUE, margin: Float = 5.0f, vehicleName: String = "")
    fun rotateByYawRateAsync(yawRate: Float, duration: Float, vehicleName: String = "")
    fun hoverAsync(vehicleName: String): Future<Void>

    fun moveByRc(rcData: RcData, vehicleName: String = "")

    fun getMultirotorState(vehicleName: String = ""): MultirotorState

    fun setSafety(enableReasons: SafetyViolationType, obsClearance: Float, obsStrategy: ObsAvoidanceStrategy,
                  obsAvoidanceVel: Float, origin: Vector3r, xyLength: Float, maxZ: Float, minZ: Float,
                  vehicleName: String = "")