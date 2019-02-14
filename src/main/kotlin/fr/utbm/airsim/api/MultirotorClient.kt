package fr.utbm.airsim.api

interface MultirotorClientInterface : RpcLibClientBase {
    fun takeoffAsync(timeoutSec: Float = 20f, vehicleName: String = "")
    fun landAsync(timeoutSec: Float = 60f, vehicleName: String = "")
    fun goHomeAsync(timeoutSec: Float = Float.MAX_VALUE, vehicleName: String = "")

    fun moveByAngleZAzync(pitch: Float, roll: Float, z: Float, yaw: Float, duration: Float, vehicleName: String = "")
    fun moveByAngleThrottleAsync(pitch: Float, roll: Float, z: Float, yaw: Float, duration: Float, vehicleName: String = "")
    fun moveByVelocityAsync(vx: Float, vy: Float, vz: Float, duration: Float,
                            drivetrainType: DrivetrainType = DrivetrainType.MAX_DEGREE_OF_FREEDOM,
                            yawMode: YawMode = YawMode(), vehicleName: String = "")

    fun moveByRc(rcData: RcData, vehicleName: String = "")

    fun getMultirotorState(vehicleName: String = ""): MultirotorState

}