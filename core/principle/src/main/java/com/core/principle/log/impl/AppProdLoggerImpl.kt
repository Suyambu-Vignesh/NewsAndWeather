package com.core.principle.log.impl

import com.core.principle.log.api.AppLogger

/**
 * App Logger for prod mode where it will skip the log will print in stacktrace for debugging
 */
internal class AppProdLoggerImpl : AppLogger {
    /**
     * @see [AppLogger.debug]
     */
    override fun debug(tag: String, message: String, exe: Throwable?) {
    }

    /**
     * @see [AppLogger.error]
     */
    override fun error(tag: String, message: String, exe: Throwable?) {
    }

    /**
     * @see [AppLogger.warning]
     */
    override fun warning(tag: String, message: String, exe: Throwable?) {
    }

    /**
     * @see [AppLogger.info]
     */
    override fun info(tag: String, message: String, exe: Throwable?) {
    }

    /**
     * @see [AppLogger.verbose]
     */
    override fun verbose(tag: String, message: String, exe: Throwable?) {
    }
}
