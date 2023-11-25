package com.core.principle.log

import com.core.principle.log.impl.AppProdLoggerImpl
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

/**
 * Testsuite for [NewsWeatherLogging]
 */
class NewsWeatherLoggingTest {
    @Test
    fun `test NewsWeatherLogging's debug`() {
        val logger = mockk<AppProdLoggerImpl>(
            relaxed = true,
            relaxUnitFun = true
        )
        NewsWeatherLogging.logger = logger

        NewsWeatherLogging.debug("tag1", "message")
        verify(exactly = 1) { logger.debug("tag1", "message", null) }
        verify(exactly = 0) { logger.info(any(), any(), any()) }
        verify(exactly = 0) { logger.warning(any(), any(), any()) }
        verify(exactly = 0) { logger.error(any(), any(), any()) }
        verify(exactly = 0) { logger.verbose(any(), any(), any()) }

        val exe = java.lang.Exception()
        NewsWeatherLogging.debug("tag1", "message", exe)
        verify(exactly = 1) { logger.debug("tag1", "message", exe) }
        verify(exactly = 0) { logger.info(any(), any(), any()) }
        verify(exactly = 0) { logger.warning(any(), any(), any()) }
        verify(exactly = 0) { logger.error(any(), any(), any()) }
        verify(exactly = 0) { logger.verbose(any(), any(), any()) }
    }

    @Test
    fun `test NewsWeatherLogging's error`() {
        val logger = mockk<AppProdLoggerImpl>(
            relaxed = true,
            relaxUnitFun = true
        )
        NewsWeatherLogging.logger = logger

        NewsWeatherLogging.error("tag1", "message")
        verify(exactly = 1) { logger.error("tag1", "message", null) }
        verify(exactly = 0) { logger.info(any(), any(), any()) }
        verify(exactly = 0) { logger.warning(any(), any(), any()) }
        verify(exactly = 0) { logger.debug(any(), any(), any()) }
        verify(exactly = 0) { logger.verbose(any(), any(), any()) }

        val exe = java.lang.Exception()
        NewsWeatherLogging.error("tag1", "message", exe)
        verify(exactly = 1) { logger.error("tag1", "message", exe) }
        verify(exactly = 0) { logger.info(any(), any(), any()) }
        verify(exactly = 0) { logger.warning(any(), any(), any()) }
        verify(exactly = 0) { logger.debug(any(), any(), any()) }
        verify(exactly = 0) { logger.verbose(any(), any(), any()) }
    }

    @Test
    fun `test NewsWeatherLogging's warning`() {
        val logger = mockk<AppProdLoggerImpl>(
            relaxed = true,
            relaxUnitFun = true
        )
        NewsWeatherLogging.logger = logger

        NewsWeatherLogging.warning("tag1", "message")
        verify(exactly = 1) { logger.warning("tag1", "message", null) }
        verify(exactly = 0) { logger.info(any(), any(), any()) }
        verify(exactly = 0) { logger.error(any(), any(), any()) }
        verify(exactly = 0) { logger.debug(any(), any(), any()) }
        verify(exactly = 0) { logger.verbose(any(), any(), any()) }

        val exe = java.lang.Exception()
        NewsWeatherLogging.warning("tag1", "message", exe)
        verify(exactly = 1) { logger.warning("tag1", "message", exe) }
        verify(exactly = 0) { logger.info(any(), any(), any()) }
        verify(exactly = 0) { logger.error(any(), any(), any()) }
        verify(exactly = 0) { logger.debug(any(), any(), any()) }
        verify(exactly = 0) { logger.verbose(any(), any(), any()) }
    }

    @Test
    fun `test NewsWeatherLogging's info`() {
        val logger = mockk<AppProdLoggerImpl>(
            relaxed = true,
            relaxUnitFun = true
        )
        NewsWeatherLogging.logger = logger

        NewsWeatherLogging.info("tag1", "message")
        verify(exactly = 1) { logger.info("tag1", "message", null) }
        verify(exactly = 0) { logger.warning(any(), any(), any()) }
        verify(exactly = 0) { logger.error(any(), any(), any()) }
        verify(exactly = 0) { logger.debug(any(), any(), any()) }
        verify(exactly = 0) { logger.verbose(any(), any(), any()) }

        val exe = java.lang.Exception()
        NewsWeatherLogging.info("tag1", "message", exe)
        verify(exactly = 1) { logger.info("tag1", "message", exe) }
        verify(exactly = 0) { logger.warning(any(), any(), any()) }
        verify(exactly = 0) { logger.error(any(), any(), any()) }
        verify(exactly = 0) { logger.debug(any(), any(), any()) }
        verify(exactly = 0) { logger.verbose(any(), any(), any()) }
    }

    @Test
    fun `test NewsWeatherLogging's verbose`() {
        val logger = mockk<AppProdLoggerImpl>(
            relaxed = true,
            relaxUnitFun = true
        )
        NewsWeatherLogging.logger = logger

        NewsWeatherLogging.verbose("tag1", "message")
        verify(exactly = 1) { logger.verbose("tag1", "message", null) }
        verify(exactly = 0) { logger.warning(any(), any(), any()) }
        verify(exactly = 0) { logger.error(any(), any(), any()) }
        verify(exactly = 0) { logger.debug(any(), any(), any()) }
        verify(exactly = 0) { logger.info(any(), any(), any()) }

        val exe = java.lang.Exception()
        NewsWeatherLogging.verbose("tag1", "message", exe)
        verify(exactly = 1) { logger.verbose("tag1", "message", exe) }
        verify(exactly = 0) { logger.warning(any(), any(), any()) }
        verify(exactly = 0) { logger.error(any(), any(), any()) }
        verify(exactly = 0) { logger.debug(any(), any(), any()) }
        verify(exactly = 0) { logger.info(any(), any(), any()) }
    }
}
