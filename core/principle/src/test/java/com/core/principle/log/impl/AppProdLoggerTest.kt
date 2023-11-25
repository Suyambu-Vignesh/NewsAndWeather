package com.core.principle.log.impl

import android.util.Log
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test

/**
 * Testsuite of [AppProdLoggerImpl]
 */
class AppProdLoggerTest {
    @Before
    fun setup() {
        mockkStatic(Log::class)
    }

    @Test
    fun `test AppLogger debug`() {
        val appLogger = AppProdLoggerImpl()

        appLogger.debug("tag", "message", null)
        verify(exactly = 0) { Log.d(any(), any(), any()) }
        verify(exactly = 0) { Log.e(any(), any(), any()) }
        verify(exactly = 0) { Log.w(any(), any(), any()) }
        verify(exactly = 0) { Log.i(any(), any(), any()) }
        verify(exactly = 0) { Log.v(any(), any(), any()) }

        appLogger.debug("tag", "message", Exception())
        verify(exactly = 0) { Log.d(any(), any(), any()) }
        verify(exactly = 0) { Log.e(any(), any(), any()) }
        verify(exactly = 0) { Log.w(any(), any(), any()) }
        verify(exactly = 0) { Log.i(any(), any(), any()) }
        verify(exactly = 0) { Log.v(any(), any(), any()) }
    }

    @Test
    fun `test AppLogger error`() {
        val appLogger = AppProdLoggerImpl()

        appLogger.error("tag", "message", null)
        verify(exactly = 0) { Log.d(any(), any(), any()) }
        verify(exactly = 0) { Log.e(any(), any(), any()) }
        verify(exactly = 0) { Log.w(any(), any(), any()) }
        verify(exactly = 0) { Log.i(any(), any(), any()) }
        verify(exactly = 0) { Log.v(any(), any(), any()) }

        appLogger.error("tag", "message", Exception())
        verify(exactly = 0) { Log.d(any(), any(), any()) }
        verify(exactly = 0) { Log.e(any(), any(), any()) }
        verify(exactly = 0) { Log.w(any(), any(), any()) }
        verify(exactly = 0) { Log.i(any(), any(), any()) }
        verify(exactly = 0) { Log.v(any(), any(), any()) }
    }

    @Test
    fun `test AppLogger warning`() {
        val appLogger = AppProdLoggerImpl()

        appLogger.warning("tag", "message", null)
        verify(exactly = 0) { Log.d(any(), any(), any()) }
        verify(exactly = 0) { Log.e(any(), any(), any()) }
        verify(exactly = 0) { Log.w(any(), any(), any()) }
        verify(exactly = 0) { Log.i(any(), any(), any()) }
        verify(exactly = 0) { Log.v(any(), any(), any()) }

        appLogger.warning("tag", "message", Exception())
        verify(exactly = 0) { Log.d(any(), any(), any()) }
        verify(exactly = 0) { Log.e(any(), any(), any()) }
        verify(exactly = 0) { Log.w(any(), any(), any()) }
        verify(exactly = 0) { Log.i(any(), any(), any()) }
        verify(exactly = 0) { Log.v(any(), any(), any()) }
    }

    @Test
    fun `test AppLogger info`() {
        val appLogger = AppProdLoggerImpl()

        appLogger.info("tag", "message", null)
        verify(exactly = 0) { Log.d(any(), any(), any()) }
        verify(exactly = 0) { Log.e(any(), any(), any()) }
        verify(exactly = 0) { Log.w(any(), any(), any()) }
        verify(exactly = 0) { Log.i(any(), any(), any()) }
        verify(exactly = 0) { Log.v(any(), any(), any()) }

        appLogger.info("tag", "message", Exception())
        verify(exactly = 0) { Log.d(any(), any(), any()) }
        verify(exactly = 0) { Log.e(any(), any(), any()) }
        verify(exactly = 0) { Log.w(any(), any(), any()) }
        verify(exactly = 0) { Log.i(any(), any(), any()) }
        verify(exactly = 0) { Log.v(any(), any(), any()) }
    }

    @Test
    fun `test AppLogger verbose`() {
        val appLogger = AppProdLoggerImpl()

        appLogger.verbose("tag", "message", null)
        verify(exactly = 0) { Log.d(any(), any(), any()) }
        verify(exactly = 0) { Log.e(any(), any(), any()) }
        verify(exactly = 0) { Log.w(any(), any(), any()) }
        verify(exactly = 0) { Log.i(any(), any(), any()) }
        verify(exactly = 0) { Log.v(any(), any(), any()) }

        appLogger.verbose("tag", "message", Exception())
        verify(exactly = 0) { Log.d(any(), any(), any()) }
        verify(exactly = 0) { Log.e(any(), any(), any()) }
        verify(exactly = 0) { Log.w(any(), any(), any()) }
        verify(exactly = 0) { Log.i(any(), any(), any()) }
        verify(exactly = 0) { Log.v(any(), any(), any()) }
    }
}