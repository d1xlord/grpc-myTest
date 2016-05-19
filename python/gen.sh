# Python

protoc -I ../src/main/proto/ --python_out=. --grpc_out=. --plugin=protoc-gen-grpc=`which grpc_python_plugin` ../src/main/proto/helloworld.proto