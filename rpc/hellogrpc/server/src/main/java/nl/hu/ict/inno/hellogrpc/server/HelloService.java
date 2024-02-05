package nl.hu.ict.inno.hellogrpc.server;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import nl.hu.ict.inno.hellogrpc.gen.MyServiceGrpc;
import nl.hu.ict.inno.hellogrpc.gen.HelloReply;
import nl.hu.ict.inno.hellogrpc.gen.HelloRequest;

@GrpcService
public class HelloService extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello " + request.getName())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
