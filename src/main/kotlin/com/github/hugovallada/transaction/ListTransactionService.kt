package com.github.hugovallada.transaction


import com.github.hugovallada.TransactionGrpcRequest
import com.github.hugovallada.TransactionGrpcResponse
import com.github.hugovallada.TransactionsGrpcResponse
import com.github.hugovallada.shared.extension.toGrpc
import com.github.hugovallada.shared.extension.toGrpcTimestamp
import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.EntityNotFoundException

@Singleton
class ListTransactionService(@Inject private val transactionRepository: TransactionRepository) {

    fun list(request: TransactionGrpcRequest) : TransactionsGrpcResponse {

        if(!transactionRepository.existsByCreditCardNumber(request.creditCard)) throw EntityNotFoundException("${request.creditCard} not found")

        transactionRepository.findTop10ByCreditCardNumberOrderByTransactionDateDesc(request.creditCard).run {
            map {
                it.toGrpc()
            }.run {
                return TransactionsGrpcResponse.newBuilder().addAllTransactions(this).build()
            }
        }
    }



}