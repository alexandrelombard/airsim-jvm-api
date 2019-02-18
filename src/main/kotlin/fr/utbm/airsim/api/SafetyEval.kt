package fr.utbm.airsim.api

import org.msgpack.annotation.MessagePackOrdinalEnum

@MessagePackOrdinalEnum
enum class SafetyViolationType(val value: Int) {
    NO_SAFETY_VIOLATION(0),
    GEO_FENCE(1 shl 0),
    OBSTACLE(1 shl 1),
    VELOCITY_LIMIT(1 shl 2),
    // FIXME: The C++ API is using the max value of uint
    ALL(Int.MAX_VALUE)
}

@MessagePackOrdinalEnum
enum class ObsAvoidanceStrategy {
    /** Will return suggested velocity vector = 0 */
    RAISE_EXCEPTION,
    /** Find closest obstacle free destination along desired destination */
    CLOSEST_MOVE,
    /** Find closest obstacle free destination along opposite of desired destination */
    OPPOSITE_MOVE
};