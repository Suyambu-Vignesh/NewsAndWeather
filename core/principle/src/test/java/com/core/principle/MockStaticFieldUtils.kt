package com.core.principle

import java.lang.reflect.Field
import java.lang.reflect.Modifier

object MockStaticFieldUtils {
    internal fun <T> mockBuildConfigDebug(fieldString: String, value: T) {
        val field = BuildConfig::class.java.getField(fieldString)
        field.isAccessible = true
        Field::class.java.getDeclaredField("modifiers").also {
            it.isAccessible = true
            it.set(field, field.modifiers and Modifier.FINAL.inv())
        }
        field.set(null, value)
    }
}