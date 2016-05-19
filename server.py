import time
import helloworld_pb2

_ONE_DAY_IN_SECOND = 24*60*60

class Greeter(helloworld_pb2.BetaGreeterServicer):
	def SayHello(self, request, context):
		return helloworld_pb2.HelloReply(message="Hello, %s!" % request.name)

	def GetObject(self, request, context):
		myName = "nafisahmedPython"
		return helloworld_pb2.HelloObj(name=myName, email=myName+"@gmail.com", id=560)

def serve():
	server = helloworld_pb2.beta_create_Greeter_server(Greeter())
	port = 50051
	server.add_insecure_port('[::]:%d' % port)
	server.start()
	print("Server started at port: %d" % port)
	try:
		while True:
			time.sleep(_ONE_DAY_IN_SECOND)
	except KeyboardInterrupt:
		server.stop(0)

if __name__ == '__main__':
	serve()