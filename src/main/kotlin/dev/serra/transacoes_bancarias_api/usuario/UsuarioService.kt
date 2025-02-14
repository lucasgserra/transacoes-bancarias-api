package dev.serra.transacoes_bancarias_api.usuario

import dev.serra.transacoes_bancarias_api.exceptions.NotAcceptableEntity
import dev.serra.transacoes_bancarias_api.exceptions.NotFoundEntity
import dev.serra.transacoes_bancarias_api.exceptions.UnprocessableEntity
import dev.serra.transacoes_bancarias_api.extrato.Extrato
import dev.serra.transacoes_bancarias_api.extrato.ExtratoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    private val log: Logger = LoggerFactory.getLogger(UsuarioService::class.java)

    fun buscarTodosUsuarios(): List<Usuario> = repository.findAll()

    fun buscarUsuario(id: String): Usuario {
        val usuario: Optional<Usuario> = repository.findById(UUID.fromString(id))
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

    fun realizarDeposito(id: String, valor: Double): Unit {
        log.info("Realizando o processo de deposito")
        val u: Optional<Usuario> = repository.findById(UUID.fromString(id));
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

    fun realizarTransferencia(idRemetente: String, idDestinatario: String, valor: Double): Unit {
        log.info("Iniciado processo de transferencia bancaria")
        val usuarioRemetente: Optional<Usuario> = repository.findById(UUID.fromString(idRemetente))
        val usuarioDestinatario: Optional<Usuario> = repository.findById(UUID.fromString(idDestinatario))
        if (usuarioDestinatario.isPresent && usuarioRemetente.isPresent) {
            log.info("Ambos os usuarios foram encontrados")
            if (usuarioRemetente.get().saldo < valor) {
                log.error("O valor da transferencia e maior que o saldo do usuario remetente")
                throw UnprocessableEntity("O valor da transferencia e maior que o saldo do usuario remetente")
            }
            if (valor <= 0) {
                log.error("O valor da transferencia nao pode ser menor ou igual a zero")
                throw NotAcceptableEntity("O valor da transferencia nao pode ser menor ou igual a zero")
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