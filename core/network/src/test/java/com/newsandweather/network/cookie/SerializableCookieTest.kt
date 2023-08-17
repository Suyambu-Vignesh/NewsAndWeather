package com.newsandweather.network.cookie

import com.google.common.truth.Truth
import io.mockk.OfTypeMatcher
import io.mockk.every
import io.mockk.mockkConstructor
import io.mockk.unmockkConstructor
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream
import java.net.HttpCookie

/**
 * Testsuite for [SerializableCookie]
 */
class SerializableCookieTest {

    @Test
    fun `test createSerializableCookie with HttpCookie`() {
        val httpCookie = HttpCookie("name", "value")
        httpCookie.maxAge = 120

        val serializableCookie = SerializableCookie.createSerializableCookie(httpCookie)

        Truth.assertThat(serializableCookie.hasExpired()).isFalse()
        Truth.assertThat(serializableCookie.getHttpCookies()).isEqualTo(httpCookie)
    }

    @Test
    fun `test createSerializableCookie with encoded string`() {
        val httpCookie = HttpCookie("name", "value")
        httpCookie.maxAge = 120

        val serializableCookie1 = SerializableCookie.createSerializableCookie(httpCookie)
        val encodeStringForCookie1 = serializableCookie1.getEncodedString()

        Truth.assertThat(encodeStringForCookie1).isNotNull()

        val serializableCookie2 = SerializableCookie.createSerializableCookie(encodeStringForCookie1!!)

        Truth.assertThat(serializableCookie2).isNotNull()
        Truth.assertThat(serializableCookie2!!.getHttpCookies().name).isEqualTo(httpCookie.name)
    }

    @Test
    fun `test getCookie Key`() {
        val httpCookie = HttpCookie("name", "value")
        httpCookie.domain = "http"
        httpCookie.maxAge = 120

        val serializableCookie = SerializableCookie.createSerializableCookie(httpCookie)

        Truth.assertThat(serializableCookie.getCookieKey()).isEqualTo("namehttp")
    }

    @Test
    fun `test getEncodedString creates the same cookie`() {
        val httpCookie = HttpCookie("name", "value")
        httpCookie.maxAge = 120

        val serializableCookie1 = SerializableCookie.createSerializableCookie(httpCookie)
        val encodeStringForCookie1 = serializableCookie1.getEncodedString()

        Truth.assertThat(encodeStringForCookie1).isNotNull()

        val serializableCookie2 = SerializableCookie.createSerializableCookie(encodeStringForCookie1!!)

        Truth.assertThat(serializableCookie2).isNotNull()
        Truth.assertThat(serializableCookie2!!.getHttpCookies().name).isEqualTo(httpCookie.name)
    }

    @Test
    fun `test getEncodedString returns null when throws IO Exe` () {
        val httpCookie = HttpCookie("name", "value")
        httpCookie.maxAge = 120

        mockkConstructor(ObjectOutputStream::class)

        val serializableCookie = SerializableCookie.createSerializableCookie(httpCookie)

        every {
            constructedWith<ObjectOutputStream>(OfTypeMatcher<ByteArrayOutputStream>(ByteArrayOutputStream::class)).writeObject(any())
        } throws IOException()

        Truth.assertThat(serializableCookie.getEncodedString()).isNull()
        unmockkConstructor(ObjectOutputStream::class)
    }

    @Test
    fun `test getHttpCookies`() {
        val httpCookie = HttpCookie("name", "value")
        httpCookie.maxAge = 120

        val serializableCookie = SerializableCookie.createSerializableCookie(httpCookie)

        Truth.assertThat(serializableCookie.getHttpCookies()).isEqualTo(httpCookie)
    }

    @Test
    fun `test hasExpired with un expired cookie`() {
        val httpCookie = HttpCookie("name", "value")
        httpCookie.maxAge = 120

        val serializableCookie = SerializableCookie.createSerializableCookie(httpCookie)

        Truth.assertThat(serializableCookie.hasExpired()).isFalse()
    }

    @Test
    fun `test hasExpired with expired cookie`() {
        val httpCookie = HttpCookie("name", "value")
        httpCookie.maxAge = 10

        val serializableCookie = SerializableCookie.createSerializableCookie(httpCookie, System.currentTimeMillis() - 100000)

        Truth.assertThat(serializableCookie.hasExpired()).isTrue()
    }

    @Test
    fun `test hasExpired with instant expired cookie`() {
        val httpCookie = HttpCookie("name", "value")
        httpCookie.maxAge = -1

        val serializableCookie = SerializableCookie.createSerializableCookie(httpCookie, System.currentTimeMillis() - 100000)

        Truth.assertThat(serializableCookie.hasExpired()).isTrue()
    }

    @Test
    fun `test hasExpired with never un-expired cookie`() {
        val httpCookie = HttpCookie("name", "value")
        httpCookie.maxAge = 0

        val serializableCookie = SerializableCookie.createSerializableCookie(httpCookie, System.currentTimeMillis() - 100000)

        Truth.assertThat(serializableCookie.hasExpired()).isFalse()
    }
}
