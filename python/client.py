from grpc.beta import implementations

import helloworld_pb2

_TIMEOUT_SECOND = 10

def run():
	channel = implementations.insecure_channel('localhost', 50051)
	stub = helloworld_pb2.beta_create_Greeter_stub(channel)
	response = stub.SayHello(helloworld_pb2.HelloRequest(name='Nafis'), _TIMEOUT_SECOND)
	print "Greeter client recieved:", response.message

	response = stub.GetObject(helloworld_pb2.Empty(), _TIMEOUT_SECOND)
	print "Object recieved:", response.name, response.email, response.id

if __name__ == '__main__':
	run()