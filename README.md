## gRPC Testing
*Used gRPC with Java and Python*

Server and Client code for both languages are included

---

>**To Compile the** *.proto* **file**

  * You need `protoc` compiler as gRPC uses it to compile the *.proto*  
  * You will also need to install gRPC plugins for languages to compile the *.proto* into language specific stub code
  
  For **Python** and some other c-based language the plugin can be installed following [this](https://github.com/grpc/grpc/blob/master/INSTALL.md)  
  For **Java**, you need to compile through gradle. You can follow [this](https://github.com/grpc/grpc-java/blob/master/README.md)

---

  * **For Python**
	* Use the gen.sh to compile the helloworld.proto

  * **For Java**
    * Use gradle to compile the helloworld.proto into two files. One for *Protobuf*, another for the *Service*
    * Make sure the *.proto* file is included as in the sourceDirectory for successful compilation
    * After compilation, add both files to the sourceDir for further usage with the Server and Client
