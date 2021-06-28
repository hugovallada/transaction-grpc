package com.github.hugovallada.kafka.transaction

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaListener
class TransactionListener {

    @Topic("transacoes")
    fun appendTransactions(transactionData: TransactionData){
        println(transactionData.id)
    }

}