# Info Streaming 

## Introduction

The application is about sharing some information from different computers simultaneously. Basically it consists of two main functionalities - `screen streaming` and a simple `chat`. 

##### Technichal perequisites:
* Programming language - **Java 8**
* Building - **Maven**
* JVM - **1.8**


The application supports multiple connections so - it contains multithreading in it. In order for it to work it should be in the same network.

#### Screen Streaming

The streaming of data was done via Client-Server `TCP` connection. Basically it is just creating screenshots each 10 seconds  to the TCP-server screen and sending them to all the TCP-clients connected to the same network. 

Mainly here is used `connection-oriented` socket programming.

#### Chat

The chat was done via `UDP` connection. Each client of the application has the possibility to write and read messages from the chatview. Every message has the name of the sender, reached from the login to the application from the beginning.

Basically here is used `connection-less` socket programming.

## Implementation summary

#### Screen Streaming
The main data structures used to perform the connections from `java.net.*` API are:
* `ServerSocket(int port)` - creates TCP-server and binds it to the given port.
* `Socket(String ip, int port)` - for either TCP-client and server. In case of server it just needs to connect it to the client through serverSocket using accept() method: **Socket socket = serverSocket.accept();**


##### The screenshot maker
The data structure used to create the screenshots are:
* `Robot()` - in order to create the image it's used the method createScreenCapture(Rectangle rectangle) creates an buffered image.
* `BufferedImage()` - structure used to store the result image from the creation of it with Robot().

#### Chat

The main data structures used to perform the connections from `java.net.*` API are:
* `DatagramSocket(int port)` - a connection-less socket, binded to the given port, for **sending** and **receiving** datagram packets.
* `DatagramPacket(byte[] buffer, int bufferLength)` - basically an information but there is no guarantee of its content, arrival or arrival time. This constructor is used to **receive** packets.
* `DatagramPacket(byte[] buffer, int bufferLength, InetAddress address, int port)` - it creates a datagram packet. This constructor is used to **send** the packets.


