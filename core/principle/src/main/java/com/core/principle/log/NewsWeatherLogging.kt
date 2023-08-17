package com.core.principle.log

import androidx.annotation.VisibleForTesting
import com.core.principle.log.api.AppLogger
import javax.inject.Inject

object NewsWeatherLogging : NewsWeatherLoggingBase() {
    /**
     * @see [AppLogger.debug]
     */
    fun debug(tag: String, message: String, exe: Throwable? = null) {
        logger.debug(tag, message, exe)
    }

    /**
     * @see [AppLogger.error]
     */
    fun error(tag: String, message: String, exe: Throwable? = null) {
        logger.error(tag, message, exe)
    }

    /**
     * @see [AppLogger.warning]
     */
    fun warning(tag: String, message: String, exe: Throwable? = null) {
        logger.warning(tag, message, exe)
    }

    /**
     * @see [AppLogger.info]
     */
    fun info(tag: String, message: String, exe: Throwable? = null) {
        logger.info(tag, message, exe)
    }

    /**
     * @see [AppLogger.verbose]
     */
    fun verbose(tag: String, message: String, exe: Throwable? = null) {
        logger.verbose(tag, message, exe)
    }
}

sealed class NewsWeatherLoggingBase {
    @Inject
    @VisibleForTesting
    internal lateinit var logger: AppLogger
}
