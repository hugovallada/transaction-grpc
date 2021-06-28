package com.github.hugovallada.kafka.transaction

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class TransactionData(
    val id: String,
    val valor: BigDecimal,
    val estabelecimento: EstablishimentData,
    val cartao: CreditCard,
    val efetivadaEm: LocalDateTime
)

data class EstablishimentData(
    val nome: String,
    val cidade: String,
    val endereco: String
)

data class CreditCard(
    val id: String,
    val email: String
)
