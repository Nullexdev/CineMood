package tech.nullexdev.cinemood.core.domain.common

import kotlinx.serialization.SerializationException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException

fun Throwable.asUiMessage(): String {
    return when (this) {
        is IOException -> "Network error. Please check your internet connection."
        is SerializationException -> "Data error. We're having trouble processing the information from the server."
        is ClientRequestException -> "Invalid request. Please try again later."
        is ServerResponseException -> "Server error. Our team is working on it."
        else -> "Something went wrong. Please try again."
    }
}
