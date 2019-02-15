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
    fun  hoverAsync(vehicleName: String): Future<Void>

    fun moveByRc(rcData: RcData, vehicleName: String = "")

    fun getMultirotorState(vehicleName: String = ""): MultirotorState

}