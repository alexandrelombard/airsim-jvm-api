package fr.utbm.airsim.api

import org.msgpack.rpc.Future

/**
 * Interface for controlling the multirotor system (the quadcopter)
 * @author Alexandre Lombard
 */
interface MultirotorClientInterface : RpcLibClientBase {
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

}