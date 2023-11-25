package com.core.principle.registery

import android.content.Context
import androidx.annotation.MainThread

/**
 * The Module which provide lifecyle where the module can initialize the resource of the Module
 *
 * The Module will not initialize till a user invokes the feature it offers, so provide the lazy
 * initialization of resource
 */
interface Module  {

    @MainThread
    fun onCreate(cotext: Context, registry: Registry)
}