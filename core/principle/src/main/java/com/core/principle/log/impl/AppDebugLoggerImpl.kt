package com.core.principle.log.impl

import android.util.Log
import com.core.principle.log.api.AppLogger

/**
 * App Logger for debug mode where the log will print in stacktrace for debugging
 */
internal class AppDebugLoggerImpl : AppLogger {

    /**
     * @see [AppLogger.debug]
     */
    override fun debug(tag: String, message: String, exe: Throwable?) {
        Log.d(tag, message, exe)
    }

    /**
     * @see [AppLogger.error]
     */
    override fun error(tag: String, message: String, exe: Throwable?) {
        Log.e(tag, message, exe)
    }

    /**
     * @see [AppLogger.warning]
     */
    override fun warning(tag: String, message: String, exe: Throwable?) {
        Log.w(tag, message, exe)
    }

    /**
     * @see [AppLogger.info]
     */
    override fun info(tag: String, message: String, exe: Throwable?) {
        Log.i(tag, message, exe)
    }

    /**
     * @see [AppLogger.verbose]
     */
    override fun verbose(tag: String, message: String, exe: Throwable?) {
        Log.v(tag, message, exe)
    }
}
