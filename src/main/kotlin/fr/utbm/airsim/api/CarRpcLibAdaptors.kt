package fr.utbm.airsim.api

import org.msgpack.MessagePack
import org.msgpack.MessagePackable
import org.msgpack.annotation.Message
import org.msgpack.annotation.MessagePackBeans
import org.msgpack.annotation.MessagePackDelegate
import org.msgpack.annotation.MessagePackOrdinalEnum
import org.msgpack.packer.Packer
import org.msgpack.template.Templates
import org.msgpack.type.MapValue
import org.msgpack.type.Value
import org.msgpack.type.ValueType
import org.msgpack.unpacker.Unpacker

@Message
data class CarControls(
        var throttle: Float = 0f,
        var steering: Float = 0f,
        var brake: Float = 0f,
        var handbrake: Boolean = false,
        var isManualGear: Boolean = false,
        var manualGear: Int = 0,
        var gearImmediate: Boolean = true) : MessagePackable {
    override fun readFrom(u: Unpacker?) {

    }

    override fun writeTo(pk: Packer) {
        pk.write(throttle)
        pk.write(steering)
        pk.write(brake)
        pk.write(handbrake)
        pk.write(isManualGear)
        pk.write(manualGear)
        pk.write(gearImmediate)
    }

}

@Message
data class CarState(
        var speed: Float = 0f,
        var gear: Int = 0,
        var rpm: Float = 0f,
        var maxRpm: Float = 0f,
        var handbrake: Boolean = false,
        var kinematicsState: KinematicsState = KinematicsState(),
        var timestamp: Long = 0) : MessagePackable {
    override fun readFrom(u: Unpacker) {
        if(u.nextType == ValueType.MAP) {
            u.readMapBegin()
            this["speed"] = u.readFloat()
            val value = u.readValue()
//            val str = u.readString()
//            val f = u.readFloat()

            u.readMapEnd()
        }
    }

    override fun writeTo(pk: Packer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}