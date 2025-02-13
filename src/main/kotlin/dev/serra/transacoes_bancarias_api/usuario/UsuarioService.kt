package dev.serra.transacoes_bancarias_api.usuario

import dev.serra.transacoes_bancarias_api.exceptions.NotAcceptableEntity
import dev.serra.transacoes_bancarias_api.exceptions.NotFoundEntity
import dev.serra.transacoes_bancarias_api.exceptions.UnprocessableEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    private val log: Logger = LoggerFactory.getLogger(UsuarioService::class.java)

    fun buscarUsuario(id: UUID): Usuario {
        val usuario: Optional<Usuario> = repository.findById(id)
        log.info("Usuario buscado no banco de dados")
        if (usuario.isPresent) {
            log.info("Usuario encontrado!")
            return usuario.get()
        } else {
            log.error("Usuario nao encontrado com o ID informado")
            throw NotFoundEntity("Usuario nao encontrado")
        }
    }

    fun criarUsuario(usuario: Usuario): Unit {
        val u: Usuario = usuario.copy(saldo = 0.0)
        log.info("Iniciando processo de criar usuario")
        repository.save(u)
        log.info("Usuario criado com sucesso!")
    }

    fun realizarDeposito(id: UUID, valor: Double): Unit {
        log.info("Realizando o processo de deposito")
        val u: Optional<Usuario> = repository.findById(id);
        if (u.isPresent) {
            if (valor <= 0) {
                log.error("O valor do deposito esta menor ou igual a 0")
                throw NotAcceptableEntity("O valor do deposito esta menor ou igual a 0")
            }
            val saldoAtual: Double = u.get().saldo
            val saldoFinal = saldoAtual + valor
            val usuario: Usuario = u.get().copy(saldo = saldoFinal)

            repository.save(usuario)
        } else {
            log.error("Usuario nao encontrado com o ID informado ")
            throw NotFoundEntity("Usuario nao encontrado")
        }
    }

    fun realizarTransferencia(idRemetente: UUID, idDestinatario: UUID, valor: Double): Unit {
        log.info("Iniciado processo de transferencia bancaria")
        val usuarioRemetente: Optional<Usuario> = repository.findById(idRemetente)
        val usuarioDestinatario: Optional<Usuario> = repository.findById(idDestinatario)
        if (usuarioDestinatario.isPresent && usuarioRemetente.isPresent) {
            log.info("Ambos os usuarios foram encontrados")
            if (usuarioRemetente.get().saldo < valor) {
                log.error("O valor da transferencia e maior que o saldo do usuario remetente")
                throw UnprocessableEntity("O valor da transferencia e maior que o saldo do usuario remetente\"")
            }
            val copyRemetente: Usuario = usuarioRemetente.get().copy(
                saldo = (usuarioRemetente.get().saldo-valor)
            )
            val copyDestinatario: Usuario = usuarioDestinatario.get().copy(
                saldo = (usuarioDestinatario.get().saldo+valor)
            )
            log.info("Transferencia realizada com sucesso!")
            repository.save(copyDestinatario)
            repository.save(copyRemetente)
        } else {
            log.error("Um dos usuarios informados nao foram encontrados!")
            throw NotFoundEntity("Um dos usuarios nao foram encontrados!")
        }
    }
}