package fr.utbm.airsim.api.annotations

@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class SerialName(val value: String)