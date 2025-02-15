package dev.serra.transacoes_bancarias_api.extrato

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class ExtratoService(private val extratoRepository: ExtratoRepository) {

    @EventListener
    fun registrarExtrato(event: Extrato) {
        val extrato = Extrato(
            idUsuario = event.idUsuario,
            valor = event.valor,
            transacao = event.transacao,
            idDestinatario = event.idDestinatario ?: null,
        )
        extratoRepository.save(extrato)
    }

    fun pegarExtratos(): List<Extrato> = extratoRepository.findAll()
}