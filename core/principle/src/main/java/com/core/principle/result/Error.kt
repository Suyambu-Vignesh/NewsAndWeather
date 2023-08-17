package com.core.principle.result

/**
 * Base Interface Of Task Result which can be either Success/Error
 */
sealed interface Error {

    /**
     * Helper method help to get the Error Message
     */
    fun getErrorMessage(): String?
}

/**
 * Error that represent there is no connection error
 */
sealed interface NoNetworkError: Error {
    val message: String?

    override fun getErrorMessage(): String? {
        return message
    }
}

/**
 * Error that represent there is a timeout/slow network
 */
sealed interface TimeOutError: Error {
    val message: String?

    override fun getErrorMessage(): String? {
        return message
    }
}

/**
 * Error that represent there is a Service Side Error
 */
sealed interface ServiceError: Error {
    val message: String?

    val code: Int

    override fun getErrorMessage(): String? {
        return message
    }

    fun getErrorCode(): Int {
        return code
    }
}

/**
 * Error that represent there is a Exception
 */
sealed interface ExceptionError: Error {
    val message: String?
    val exception: Exception

    override fun getErrorMessage(): String? {
        return message
    }

    fun getErrorException(): Exception {
        return exception
    }
}

/**
 * Error that represent that belong to one of tail end Error
 */
sealed interface GenericError: Error {
    val message: String?

    override fun getErrorMessage(): String? {
        return message
    }
}

/**
 * Method get the NetworkError Object
 *
 * @param message - String or null. message to add more information to the error
 */
fun getNetworkError(message: String?): NoNetworkError {
    return NoNetworkErrorImpl(message)
}

/**
 * Method get the TimeOutError Object
 *
 * @param message - String or null. message to add more information to the error
 */
fun getTimeOutError(message: String?): NoNetworkError {
    return NoNetworkErrorImpl(message)
}

/**
 * Method get the ServiceError Object
 *
 * @param code - Int. HttpCode
 * @param message - String or null. message to add more information to the error
 */
fun getServiceError(code: Int, message: String?): ServiceError {
    return ServiceErrorImpl(code, message)
}

/**
 * Method get the Exception Object
 *
 * @param exception - [Exception].
 * @param message - String or null. message to add more information to the error
 */
fun getExceptionError(exception: Exception, message: String?): ExceptionError {
    return ExceptionErrorImpl(exception, message)
}

/**
 * Method get the Generic Object
 *
 * @param message - String or null. message to add more information to the error
 */
fun getGenericError(message: String?): GenericError {
    return GenericErrorImpl(message)
}

/**
 * Implementation of Error that represent No Network.
 *
 * @param message - Error message
 */
internal class NoNetworkErrorImpl(override val message: String? = null) : NoNetworkError

/**
 * Implementation of Error that represent TimeOut.
 *
 * @param message - Error message
 */
internal class TimeOutErrorImpl(override val message: String? = null) : TimeOutError

/**
 * Implementation of Error that represent Service Side Issue.
 *
 * @param message - Error message
 */
internal class ServiceErrorImpl(override val code: Int, override val message: String? = null) : ServiceError

/**
 * Implementation of Error that represent Exception
 *
 * @param exception - Exception.
 * @param message - Error message
 */
internal class ExceptionErrorImpl(override val exception: Exception, override val message: String? = null) : ExceptionError

/**
 * Implementation of Error that represent Service Side Issue.
 *
 * @param message - Error message
 */
internal class GenericErrorImpl(override val message: String? = null) : GenericError
