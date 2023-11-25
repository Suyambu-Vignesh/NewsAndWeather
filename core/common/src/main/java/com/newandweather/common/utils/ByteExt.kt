package com.newandweather.common.utils

import java.util.*

private val HEX_ARRAY = "0123456789ABCDEF".toCharArray()

/**
 * Helper fun to convert [ByteArray] to [String]
 *
 * @return [String]
 */
fun ByteArray.getString(): String {
    val sb = StringBuilder(this.size * 2)
    for (element in this) {
        val v: Int = element.toInt() and 0xff
        sb.append(HEX_ARRAY[v ushr 4])
        sb.append(HEX_ARRAY[v and 0x0F])
    }
    return sb.toString().uppercase(Locale.US)
}
