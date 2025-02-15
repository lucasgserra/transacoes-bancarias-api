package dev.serra.transacoes_bancarias_api.extrato

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/extrato")
class ExtratoController(private val service: ExtratoService) {

    @Operation(summary = "Retorna todos os extratos registrados")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Extratos retornados"),
    ])
    @GetMapping
    fun pegarExtratos(): List<Extrato> = service.pegarExtratos()

    @Operation(summary = "Retorna o objeto do extrato correspondente")
    @ApiResponses(value = [
        ApiResponse(responseCode = "404", description = "Extrato nao encontrado"),
        ApiResponse(responseCode = "200", description = "Extrato encontrado"),
    ])
    @GetMapping("/{id}")
    fun pegarExtratoID(@PathVariable id: String) = service.pegarExtratoID(id)

}