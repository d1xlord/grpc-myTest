package self.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

/**
 * @author Nafis Ahmed
 */
public class HelloWorldServer {

    private int port = 50051;
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build()
                .start();
        System.out.println("Server started on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** Shutting down gRPC server as JVM shutting down");
                HelloWorldServer.this.stop();
                System.err.println("*** Server shut down");
            }
        });
    }

    public void stop() {
        if(server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if(server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }

    private class GreeterImpl extends GreeterGrpc.AbstractGreeter{

        @Override
        public void sayHello(Helloworld.HelloRequest req, StreamObserver<Helloworld.HelloReply> responseObserver) {
            Helloworld.HelloReply reply = Helloworld.HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void getObject(Helloworld.Empty req, StreamObserver<Helloworld.HelloObj> responseObserver) {
            int val = ((int)(Math.random() * 1000))%100;
            String name = "nafisahmedJava";
            Helloworld.HelloObj obj = Helloworld.HelloObj.newBuilder().setName(name)
                    .setEmail(name+"@gmail.com")
                    .setId(val)
                    .build();
            responseObserver.onNext(obj);
            responseObserver.onCompleted();
        }
    }
}