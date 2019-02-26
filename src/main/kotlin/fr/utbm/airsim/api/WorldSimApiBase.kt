package fr.utbm.airsim.api

import org.msgpack.annotation.Message
import org.msgpack.annotation.MessagePackOrdinalEnum

@MessagePackOrdinalEnum
enum class WeatherParameter(val value: Int) {
    RAIN(0),
    ROADWETNESS(1),
    SNOW(2),
    ROAD_SNOW(3),
    MAPLE_LEAR(4),
    ROAD_LEAF(5),
    DUST(6),
    FOG(7),
    ENABLED(8)
}