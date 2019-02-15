package fr.utbm.airsim.api

import fr.utbm.airsim.api.annotations.SerialName
import org.msgpack.MessagePackable
import org.msgpack.packer.Packer
import org.msgpack.type.ValueType
import org.msgpack.unpacker.Unpacker
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties

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

    override fun writeTo(pk: Packer) {
        // Properties to serialize
        val properties = this::class.declaredMemberProperties
                .filter {
                    it.annotations.filter {
                        it is org.msgpack.annotation.Ignore
                    }.isEmpty()
                }

        // Write the properties
        pk.writeMapBegin(properties.size)
        for(property in properties) {
            val serialNameAnnotation = property.annotations.firstOrNull { it is SerialName }
            val key =
                    if(serialNameAnnotation != null) {
                        (serialNameAnnotation as SerialName).value
                    } else {
                        property.name
                    }

            pk.write(key)
            pk.write(property.getter.call(this))
        }
        pk.writeMapEnd()
    }
}