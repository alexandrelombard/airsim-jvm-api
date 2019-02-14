package fr.utbm.airsim.api

interface CarClientInterface : RpcLibClientBase {
    fun setCarControls(controls: CarControls, vehicleName: String = "")
    fun getCarState(vehicleName: String = ""): CarState
}