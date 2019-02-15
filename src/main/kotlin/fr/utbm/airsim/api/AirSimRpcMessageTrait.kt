package fr.utbm.airsim.api

import fr.utbm.airsim.api.annotations.SerialName
import org.msgpack.MessagePackable
import org.msgpack.packer.Packer
import org.msgpack.type.ValueType
import org.msgpack.unpacker.Unpacker
import java.lang.IllegalStateException
import java.lang.reflect.Field
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation

/**
 * This interface is a trait for reading and writing packet from and to AirSim.
 * @author Alexandre Lombard
 */
interface AirSimRpcMessageTrait : MessagePackable {
    override fun readFrom(u: Unpacker) {
        if(u.nextType == ValueType.MAP) {
            u.readMapBegin()

            // Build the map associating a name to a field
            val fields = hashMapOf<String, Field>()
            for(property in this::class.declaredMemberProperties) {
                val serialName = property.findAnnotation<SerialName>()
                val field = this.javaClass.declaredFields.first { it.name == property.name }
                if(serialName != null) {
                    fields[serialName.value] = field
                } else {
                    fields[property.name] = field
                }
            }

            // We read as long as there are map keys (whose value type are RAW)

            while(!u.trySkipNil() && u.nextType == ValueType.RAW) {
                val key = u.readString()

                // Looking for the corresponding field, using either the name or the serial name
                val field = fields[key] ?: throw IllegalStateException("Field ${key} could not be deserialized: not found in class ${this.javaClass.name}")

                // Reading the value using the found field type
                val value = u.read(field.type)

                // Setting the associated property
                val property = this::class.declaredMemberProperties.first { it.name == field.name }
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