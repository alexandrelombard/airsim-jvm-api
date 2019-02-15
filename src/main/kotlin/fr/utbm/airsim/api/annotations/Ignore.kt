package fr.utbm.airsim.api.annotations

/**
 * This annotation indicates that a field or a property should be ignored by msgpack serializer
 * @author Alexandre Lombard
 */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Ignore