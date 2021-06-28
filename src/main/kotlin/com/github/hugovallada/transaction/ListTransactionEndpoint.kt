package com.github.hugovallada.transaction

import com.github.hugovallada.TransactionGrpcRequest
import com.github.hugovallada.TransactionGrpcResponse
import com.github.hugovallada.TransactionServiceGrpc
import com.github.hugovallada.TransactionsGrpcResponse
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListTransactionEndpoint(@Inject private val service: ListTransactionService) : TransactionServiceGrpc.TransactionServiceImplBase() {

    override fun find(request: TransactionGrpcRequest, responseObserver: StreamObserver<TransactionsGrpcResponse>) {
        service.list(request).run {
            responseObserver.onNext(this)
        }

        responseObserver.onCompleted()
    }


}