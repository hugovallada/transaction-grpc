package com.github.hugovallada.shared.extension

import com.github.hugovallada.TransactionGrpcResponse
import com.github.hugovallada.transaction.Transaction
import com.google.protobuf.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun Transaction.toGrpc() = TransactionGrpcResponse.newBuilder()
    .setCreditCard(creditCardNumber)
    .setEstablishment(establishmentName)
    .setId(transactionIdentifier)
    .setValue(transactionValue.toString())
    .setDate(transactionDate.toGrpcTimestamp()).build()


fun LocalDateTime.toGrpcTimestamp(): Timestamp {
    val instant = atZone(ZoneId.of("UTC")).toInstant()
    return Timestamp.newBuilder()
        .setSeconds(instant.epochSecond)
        .setNanos(instant.nano).build()
}