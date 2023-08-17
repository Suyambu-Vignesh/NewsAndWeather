package com.newsandweather.network.cookie

import androidx.annotation.VisibleForTesting
import com.newandweather.common.utils.getBytes
import com.newandweather.common.utils.getString
import java.io.*
import java.net.HttpCookie

internal class SerializableCookie private constructor(
    private var cookie: HttpCookie,
    private var createdAt: Long = System.currentTimeMillis()
) : Serializable {

    /**
     * Method to get the Unique Key for the Cookie
     *
     * @return [String]
     */
    internal fun getCookieKey(): String {
        return cookie.getCookieKey()
    }

    /**
     * Method to get the Cookie as String.
     *
     * @return [String] or null
     */
    internal fun getEncodedString(): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()

        try {
            val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
            objectOutputStream.writeObject(this)
        } catch (ioException: IOException) {
            //todo log error
            return null
        }

        return byteArrayOutputStream.toByteArray().getString()
    }

    /**
     * Method to return the [HttpCookie]
     *
     * @return [HttpCookie]
     */
    internal fun getHttpCookies(): HttpCookie {
        return cookie
    }

    /**
     * Helper fun to say the cookie is Expired.
     */
    internal fun hasExpired(): Boolean {
        if (cookie.maxAge == 0L) {
            return false
        }

        if (cookie.maxAge == -1L) {
            // see [cookie.maxAge] description
            return true
        }

        val now = System.currentTimeMillis()

        val delta = (now - createdAt) / 1000

        return delta > cookie.maxAge
    }

    /**
     * To help De-Serialize to Stream from [SerializableCookie]
     *
     * @param inputStream - [ObjectInputStream]
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    @VisibleForTesting
    private fun readObject(inputStream: ObjectInputStream) {
        val name = inputStream.readObject() as String
        val value = inputStream.readObject() as String

        val cookie = HttpCookie(name, value)
        cookie.comment = inputStream.readObject() as? String
        cookie.commentURL = inputStream.readObject() as? String
        cookie.domain = inputStream.readObject() as? String
        cookie.maxAge = inputStream.readLong()
        cookie.path = inputStream.readObject() as? String

        this.cookie = cookie
        this.createdAt = inputStream.readLong()
    }

    /**
     * To help Serialize to Stream from [SerializableCookie]
     *
     * @param outputStream - [ObjectOutputStream]
     *
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun writeObject(outputStream: ObjectOutputStream) {
        outputStream.writeObject(cookie.name)
        outputStream.writeObject(cookie.value)
        outputStream.writeObject(cookie.comment)
        outputStream.writeObject(cookie.commentURL)
        outputStream.writeObject(cookie.domain)
        outputStream.writeLong(cookie.maxAge)
        outputStream.writeObject(cookie.path)
        outputStream.writeLong(createdAt)
    }

    companion object {
        fun createSerializableCookie(cookie: HttpCookie, createdAt: Long = System.currentTimeMillis()): SerializableCookie = SerializableCookie(cookie, createdAt)

        fun createSerializableCookie(encodedString: String): SerializableCookie? {
            val inputStream = ByteArrayInputStream(encodedString.getBytes())

            return try {
                ObjectInputStream(inputStream).readObject() as? SerializableCookie
            } catch (ioException: IOException) {
                //todo log error
                null
            } catch (classNotFoundExe: ClassNotFoundException) {
                //todo log error
                null
            }
        }
    }
}

internal fun HttpCookie.getCookieKey() = this.name + this.domain