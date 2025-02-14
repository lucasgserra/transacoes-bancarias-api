package dev.serra.transacoes_bancarias_api.usuario

import dev.serra.transacoes_bancarias_api.usuario.dto.DepositoRequestDTO
import dev.serra.transacoes_bancarias_api.usuario.dto.TransferenciaRequestDTO
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/usuario")
class UsuarioController(private val usuarioService: UsuarioService) {

    @GetMapping
    fun buscarUsuarios() = usuarioService.buscarTodosUsuarios()

    @GetMapping("/{id}")
    fun buscarUsuario(@RequestParam id: String){
        usuarioService.buscarUsuario(id)
        ResponseEntity.status(HttpStatus.FOUND).build<Void>()
    }

    @PostMapping("/criar")
    fun criarUsuario(@RequestBody usuario: Usuario) {
        usuarioService.criarUsuario(usuario)
        ResponseEntity.status(HttpStatus.CREATED).build<Void>()
    }

    @PostMapping("/deposito")
    fun deposito(@RequestBody request: DepositoRequestDTO) {
        usuarioService.realizarDeposito(request.id, request.valor)
        ResponseEntity.status(HttpStatus.ACCEPTED).build<Void>()
    }

    @PostMapping("/transferencia")
    fun transferencia(@RequestBody request: TransferenciaRequestDTO) {
        usuarioService.realizarTransferencia(request.idRemetente, request.idDestinatario, request.valor)
        ResponseEntity.status(HttpStatus.ACCEPTED).build<Void>()
    }
}