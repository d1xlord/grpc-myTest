package self.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

/**
 * @author Nafis Ahmed
 */
public class HelloWorldClient {

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greet(String name) {
        Helloworld.HelloRequest req = Helloworld.HelloRequest.newBuilder().setName(name)
                .build();
        Helloworld.Empty req2 = Helloworld.Empty.newBuilder().build();

        try {
            Helloworld.HelloReply resp = blockingStub.sayHello(req);
            System.out.println("Got Reply:\n" + resp.getMessage());

            Helloworld.HelloObj resp2 = blockingStub.getObject(req2);
            System.out.println("Got Object:\n" + resp2);
        } catch (StatusRuntimeException err) {
            System.out.println("ERROR " + err);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("localhost", 50051);
        try {
            String user = "nafisFromJava";
            client.greet(user);
        } finally {
            client.shutdown();
        }
    }
}
