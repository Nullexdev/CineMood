package tech.nullexdev.cinemood.core.data.common

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import tech.nullexdev.cinemood.core.domain.common.DomainError

fun Throwable.toDomainError(): DomainError {
    return when (this) {
        is IOException -> DomainError.Network
        is SerializationException -> DomainError.Serialization
        is ClientRequestException -> DomainError.Client
        is ServerResponseException -> DomainError.Server
        else -> DomainError.Unknown
    }
}
