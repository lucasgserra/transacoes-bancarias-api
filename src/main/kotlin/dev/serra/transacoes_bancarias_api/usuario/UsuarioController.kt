package dev.serra.transacoes_bancarias_api.usuario

import dev.serra.transacoes_bancarias_api.usuario.dto.DepositoRequestDTO
import dev.serra.transacoes_bancarias_api.usuario.dto.TransferenciaRequestDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @Operation(summary = "Retorna o objeto do usuario correspondente")
    @ApiResponses(value = [
        ApiResponse(responseCode = "404", description = "Usuario nao encontrado"),
        ApiResponse(responseCode = "200", description = "Usuario encontrado"),
    ])
    @GetMapping("/{id}")
    fun buscarUsuario(@PathVariable id: String) = usuarioService.buscarUsuario(id)

    @Operation(summary = "Criar um usuario no banco")
    @ApiResponses(value = [
        ApiResponse(responseCode = "500", description = "Parametro inserido nao requerido para criacao de usuario"),
        ApiResponse(responseCode = "400", description = "Algum campo ficou pendente para criacao de usuario"),
        ApiResponse(responseCode = "200", description = "Usuario criado"),
    ])
    @PostMapping("/criar")
    fun criarUsuario(@RequestBody usuario: Usuario) {
        usuarioService.criarUsuario(usuario)
        ResponseEntity.status(HttpStatus.CREATED).build<Void>()
    }

    @Operation(summary = "Realiza um deposito na conta correspondente")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Deposito realizado com sucesso"),
        ApiResponse(responseCode = "404", description = "Usuario nao encontrado"),
        ApiResponse(responseCode = "406", description = "Valor inserido nao corresponde ao valor requerido"),
    ])
    @PostMapping("/deposito")
    fun deposito(@RequestBody request: DepositoRequestDTO) {
        usuarioService.realizarDeposito(request.id, request.valor)
        ResponseEntity.status(HttpStatus.ACCEPTED).build<Void>()
    }

    @Operation(summary = "Realiza transferencia de dinheiro de uma conta para outra")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Transferencia realizada com sucesso"),
        ApiResponse(responseCode = "404", description = "Usuario nao encontrado"),
        ApiResponse(responseCode = "406", description = "Valor inserido nao corresponde ao valor requerido"),
        ApiResponse(responseCode = "422", description = "Usuario nao possui valor suficiente para a transferencia"),
        ApiResponse(responseCode = "400", description = "Erro na requisicao"),
    ])
    @PostMapping("/transferencia")
    fun transferencia(@RequestBody request: TransferenciaRequestDTO) {
        usuarioService.realizarTransferencia(request.idRemetente, request.idDestinatario, request.valor)
        ResponseEntity.status(HttpStatus.ACCEPTED).build<Void>()
    }
}