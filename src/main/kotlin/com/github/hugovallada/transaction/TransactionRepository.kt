package com.github.hugovallada.transaction

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {

    fun existsByCreditCardNumber(creditCard: String): Boolean

    fun findTop10ByCreditCardNumberOrderByTransactionDateDesc(creditCard: String): List<Transaction>

}