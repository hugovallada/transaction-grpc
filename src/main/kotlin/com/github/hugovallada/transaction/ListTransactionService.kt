package com.github.hugovallada.transaction


import com.github.hugovallada.TransactionGrpcRequest
import com.github.hugovallada.TransactionsGrpcResponse
import com.github.hugovallada.shared.extension.toGrpc
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListTransactionService(@Inject private val transactionRepository: TransactionRepository) {

    fun list(request: TransactionGrpcRequest) : TransactionsGrpcResponse {
        transactionRepository.findTop10ByCreditCardNumberOrderByTransactionDateDesc(request.creditCard)?.run {
            map {
                it.toGrpc()
            }.run {
                return TransactionsGrpcResponse.newBuilder().addAllTransactions(this).build()
            }
        } ?: throw IllegalArgumentException("Something went wrong")
    }

}