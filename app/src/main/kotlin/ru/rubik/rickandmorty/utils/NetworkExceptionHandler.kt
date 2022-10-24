package ru.rubik.rickandmorty.utils

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler

object NetworkErrorCodes {
	const val INTERNET_CONNECTION_ERROR = 3432523
	const val UNKNOWN = 78885
}

inline fun coroutineNetworkExceptionHandler(
	crossinline handler: (code: Int) -> Unit,
): CoroutineExceptionHandler =
	object : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {
		override fun handleException(context: CoroutineContext, exception: Throwable)  {
			handler.invoke(exception.toNetworkException().code)
		}
	}

fun Throwable.toNetworkException(): NetworkException {
	val code = when(this) {
		is Exception -> NetworkErrorCodes.INTERNET_CONNECTION_ERROR

		else           -> NetworkErrorCodes.UNKNOWN
	}

	return NetworkException(code, message)
}