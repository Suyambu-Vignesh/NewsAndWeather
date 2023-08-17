package com.newandweather.common.utils

/**
 * Helper Method to convert String to [ByteArray]
 *
 * @return [ByteArray]
 */
fun String.getBytes(): ByteArray {
    val len = this.length
    if (len % 2 != 0) {
        return ByteArray(0)
    }
    val data = ByteArray(len / 2)
    var index = 0
    while (index < len) {
        data[index / 2] = (
                (Character.digit(this[index], 16) shl 4) + Character.digit(this[index + 1], 16)
                ).toByte()
        index += 2
    }
    return data
}

