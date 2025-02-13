package dev.serra.transacoes_bancarias_api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
class UnprocessableEntity(message: String):RuntimeException(message) {
}