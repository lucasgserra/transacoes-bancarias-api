package dev.serra.transacoes_bancarias_api.extrato

import dev.serra.transacoes_bancarias_api.exceptions.NotFoundEntity
import dev.serra.transacoes_bancarias_api.usuario.UsuarioService
import org.hibernate.query.sqm.tree.SqmNode.log
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExtratoService(private val extratoRepository: ExtratoRepository) {

    private val log: Logger = LoggerFactory.getLogger(ExtratoService::class.java)

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

    fun pegarExtratoID(id: String): Extrato {
        log.info("Iniciando processamente de extrato")
        val extrato: Optional<Extrato> = extratoRepository.findById(UUID.fromString(id))
        if (extrato.isPresent) {
            log.info("Extrato encontrado")
            return extrato.get()
        } else {
            log.error("ID de Extrato nao foi encontrado")
            throw NotFoundEntity("ID de Extrato nao encontrado")
        }
    }
}