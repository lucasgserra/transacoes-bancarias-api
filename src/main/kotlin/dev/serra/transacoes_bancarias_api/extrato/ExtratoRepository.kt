package dev.serra.transacoes_bancarias_api.extrato

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExtratoRepository: JpaRepository<Extrato, UUID> {
}