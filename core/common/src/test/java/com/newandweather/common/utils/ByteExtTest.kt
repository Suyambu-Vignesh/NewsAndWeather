package com.newandweather.common.utils

import com.google.common.truth.Truth
import org.junit.Test
import java.io.*

/**
 * Testsuite for [ByteExt]
 */
class ByteExtTest {

    @Test
    fun `test custom object serialization and de-serialization`() {
        class CustomObj(var str: String) : java.io.Serializable {
            private fun writeObject(outputStream: ObjectOutputStream) {
                outputStream.writeObject(str)
            }

            private fun readObject(inputStream: ObjectInputStream) {
                this.str = inputStream.readObject() as String
            }
        }

        val obj = CustomObj("str")

        // ------- starting encoding ------
        val outputStream = ByteArrayOutputStream()
        try {
            val objectOutputStream = ObjectOutputStream(outputStream)
            objectOutputStream.writeObject(obj)
        } catch (e: IOException) {
            // do nothing
        }

        val encodesStr = outputStream.toByteArray().getString()

        // ------- starting decoding ------
        val bytes = encodesStr.getBytes()
        val inputStream = ByteArrayInputStream(bytes)

        var obj2: CustomObj? = null
        obj2 = try {
            val objectInputStream = ObjectInputStream(inputStream)
            objectInputStream.readObject() as CustomObj
        } catch (e: IOException) {
            // donothing
            null
        }

        Truth.assertThat(obj2).isNotNull()
        Truth.assertThat(obj2!!.str).isEqualTo(obj.str)
    }

    @Test
    fun `test edge case for getBytes when string is Empty() or odd Length`() {
        var str = ""
        Truth.assertThat(str.getBytes().size).isEqualTo(0)

        str = "ert"
        Truth.assertThat(str.getBytes().size).isEqualTo(0)
    }
}