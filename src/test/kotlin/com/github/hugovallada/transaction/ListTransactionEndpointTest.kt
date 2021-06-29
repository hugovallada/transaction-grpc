package com.github.hugovallada.transaction

import com.github.hugovallada.TransactionGrpcRequest
import com.github.hugovallada.TransactionServiceGrpc
import io.grpc.ManagedChannel
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@MicronautTest(transactional = false)
internal class ListTransactionEndpointTest(
    @Inject private val grpcClient: TransactionServiceGrpc.TransactionServiceBlockingStub,
    @Inject private val transactionRepository: TransactionRepository
){


    @Test
    internal fun `should return Status not found when the credit card number is not found in the database`() {
        val request = TransactionGrpcRequest.newBuilder().setCreditCard("9999-9999-9999-9999").build()

        shouldThrow<StatusRuntimeException> {
            grpcClient.find(request)
        }.run {
            status.code.shouldBe(Status.NOT_FOUND.code)
            status.description.shouldBe("9999-9999-9999-9999 not found")
        }
    }

    @Test
    internal fun `should return a list of transactions when the credit card number exists`() {
        val transaction = Transaction("08b05a71-4d73-4c4d-a264-c0c3034acf6b",
        BigDecimal.valueOf(2900),"5541da9c-79c5-44fb-b701-cc50ed07b45d","Tom Morrow", LocalDateTime.now())

        transactionRepository.save(transaction)

        val request = TransactionGrpcRequest.newBuilder().setCreditCard("5541da9c-79c5-44fb-b701-cc50ed07b45d").build()

        grpcClient.find(request).run {
            transactionsCount.shouldBeGreaterThan(0)
            transactionsList.shouldNotBeEmpty()
        }
    }

    @Factory
    internal class GrpcFactory{
        @Singleton
        fun generateListTransactionGrpcClient(@GrpcChannel(GrpcServerChannel.NAME) channel: ManagedChannel) = TransactionServiceGrpc.newBlockingStub(channel)
    }

}