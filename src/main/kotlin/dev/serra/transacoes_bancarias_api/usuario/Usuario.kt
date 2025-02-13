package dev.serra.transacoes_bancarias_api.usuario

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.Valid
import org.jetbrains.annotations.NotNull
import java.util.UUID

@Entity
data class Usuario(
    @Id @GeneratedValue
    val id: UUID? = null,

    @field:NotNull
    @Valid
    val nome: String,

    @field:NotNull
    @Valid
    val cpf: String,

    @field:NotNull
    @Valid
    val email: String,

    val saldo: Double
)
