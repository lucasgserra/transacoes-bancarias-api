package dev.serra.transacoes_bancarias_api.usuario.dto

data class TransferenciaRequestDTO(
    val idRemetente: String,
    val idDestinatario: String,
    val valor: Double,
)
