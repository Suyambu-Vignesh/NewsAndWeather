package com.core.principle.di

import com.core.principle.MockStaticFieldUtils.mockBuildConfigDebug
import com.core.principle.log.di.LoggingModule
import com.core.principle.log.impl.AppDebugLoggerImpl
import com.core.principle.log.impl.AppProdLoggerImpl
import com.google.common.truth.Truth
import org.junit.Test

/**
 * Testsuite for [LoggingModule]
 */
class LoggingModuleTest {

    @Test
    fun `test provideLogger for debug build, when the build is of debug type`() {
        mockBuildConfigDebug("DEBUG", true)

        val module = LoggingModule()
        Truth.assertThat(module.provideLogger()).isInstanceOf(AppDebugLoggerImpl::class.java)
    }

    @Test
    fun `test provideLogger for release build, when the build is of release type`() {
        mockBuildConfigDebug("DEBUG", false)

        val module = LoggingModule()
        Truth.assertThat(module.provideLogger()).isInstanceOf(AppProdLoggerImpl::class.java)
    }
}