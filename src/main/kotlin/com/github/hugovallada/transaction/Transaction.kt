package com.github.hugovallada.transaction

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name  = "tb_transaction")
class Transaction(
    val transactionIdentifier: String,
    val transactionValue: BigDecimal,
    val creditCardNumber: String,
    val establishmentName: String,
    val transactionDate: LocalDateTime
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}