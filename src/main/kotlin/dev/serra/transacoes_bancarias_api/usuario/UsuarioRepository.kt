package dev.serra.transacoes_bancarias_api.usuario

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsuarioRepository: JpaRepository<Usuario, UUID> {
}