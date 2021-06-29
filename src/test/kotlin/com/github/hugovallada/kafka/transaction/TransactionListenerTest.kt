package com.github.hugovallada.kafka.transaction

import com.github.hugovallada.transaction.TransactionRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.awaitility.Awaitility
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Duration
import javax.inject.Inject

@MicronautTest(transactional = false)
internal class TransactionListenerTest(@Inject private val transactionRepository: TransactionRepository){


    @Test
    internal fun `test kafka consumer`() {

        Awaitility.setDefaultTimeout(Duration.ofMinutes(5))
        Awaitility.await().until {
            transactionRepository.findAll().size >= 1
        }



    }
}