package dev.serra.transacoes_bancarias_api.extrato

import dev.serra.transacoes_bancarias_api.extrato.enum.TipoTransacao
import dev.serra.transacoes_bancarias_api.usuario.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExtratoService(private val repository: ExtratoRepository) {

    fun adicionarExtrato(extrato: Extrato) = repository.save(extrato)

}