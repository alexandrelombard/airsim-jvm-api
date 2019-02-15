package fr.utbm.airsim.api.annotations

/**
 * This annotation indicates that a field or a property should be using the value as its name for the serialization.
 * It is expected to be used when the name of the property is different from the name provided by AirSim.
 * @author Alexandre Lombard
 */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class SerialName(val value: String)