package dev.serra.transacoes_bancarias_api.extrato

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class DispararExtrato(private val eventPublisher: ApplicationEventPublisher) {

    fun publicarEvento(event: Extrato) {
        eventPublisher.publishEvent(event)
    }

}