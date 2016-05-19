## gRPC Testing
*Used gRPC with Java and Python*
A Server and Client code for both languages
---
>**To Compile the** *.proto* **file**

  * **For Python**
	* Use the gen.sh to compile the helloworld.proto

  * **For Java**
    * Use gradle to compile the helloworld.proto into two files. One for *Protobuf*, another for the *Service*
    * Make sure the *.proto* file is included as in the sourceDirectory for successful compilation
    * After compilation, add both files to the sourceDir for further usage with the Server and Client
