package fr.utbm.airsim.api

/**
 * This interface allows to control the AirSim car.
 * @author Alexandre Lombard
 */
interface CarClientInterface : RpcLibClientBase {
    /**
     * Applies the given car controls
     * @param controls the controls to apply
     * @param vehicleName the name of the vehicle to control (default value is empty string, it will control the
     *                    only vehicle)
     */
    fun setCarControls(controls: CarControls, vehicleName: String = "")

    /**
     * Gets the given car state
     * @param vehicleName the name of the vehicle we want to retrieve the state (default value is empty string, it will
     *                    return the value of the only vehicle)
     * @return the state of the vehicle
     */
    fun getCarState(vehicleName: String = ""): CarState
}