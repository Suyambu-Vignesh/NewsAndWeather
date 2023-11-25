package com.core.principle

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DebugRetention

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProdRetention