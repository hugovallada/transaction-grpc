package com.github.hugovallada.kafka.transaction

import com.github.hugovallada.transaction.TransactionRepository
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import javax.inject.Inject

@KafkaListener
class TransactionListener(@Inject private val transactionRepository: TransactionRepository) {

    @Topic("transacoes")
    fun appendTransactions(transactionData: TransactionData){
        transactionRepository.save(transactionData.toModel())
    }

}