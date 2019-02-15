package fr.utbm.airsim.api

import org.msgpack.MessagePackable
import org.msgpack.type.ValueType
import org.msgpack.unpacker.Unpacker
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.jvmErasure

interface AirSimRpcMessageTrait : MessagePackable {
    override fun readFrom(u: Unpacker) {
        if(u.nextType == ValueType.MAP) {
            u.readMapBegin()
            for(field in this.javaClass.declaredFields) {
                val key = u.readString()
                val value = u.read(field.type)
                val property = this::class.declaredMemberProperties
                        .first { it.name == field.name }

                if(property is KMutableProperty<*>) {
                    property.setter.call(this, value)
                }
            }
            u.readMapEnd()
        }
    }
}