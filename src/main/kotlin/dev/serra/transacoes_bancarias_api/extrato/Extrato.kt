package dev.serra.transacoes_bancarias_api.extrato

import dev.serra.transacoes_bancarias_api.extrato.enum.TipoTransacao
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.Valid
import org.jetbrains.annotations.NotNull
import java.time.OffsetDateTime
import java.util.UUID

@Entity(name = "extrato")
data class Extrato(
    @Id @GeneratedValue
    val idTransacao: UUID? = null,

    @field:NotNull
    @Valid
    val idUsuario: UUID,

    @field:NotNull
    @Valid
    val transacao: TipoTransacao,

    @Valid
    val data: OffsetDateTime? = OffsetDateTime.now(),

    @field:NotNull
    @Valid
    val valor: Double,

    @Valid
    val idDestinatario: UUID? = null
) {

}