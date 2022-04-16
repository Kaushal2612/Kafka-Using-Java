This project is to created Producer and Consumer to send and receive message from the topic using Apache Kafka

About Apache Kafka: https://medium.com/analytics-vidhya/apache-kafka-architecture-getting-started-with-apache-kafka-771d69ac6cef

Steps to Follow for Running this project:
1. Setup Java and Maven
2. Setup Apache Kafka
3. Download this project

To setup Java: https://devwithus.com/install-java-windows-10/

To setup Maven: https://medium.com/@aman.sharma163/maven-installation-on-windows-60d073c0653

To setup Apache Kafka:
- Download the latest tar.gz from this link: https://kafka.apache.org/quickstart
- Unpack the package : tar -xzf <your downloaded kafka file>
- Rename the folder to Kafka (Not a mandatory step but it short the path)
- move the folder to C:
  - Inside your kafka, create directory kafka-logs and zookeeper-data
  - Open config/zookeeper.properties and modify dataDir -> dataDir=C:/kafka/zookeeper-data and save it
  - Open config/server.properties and modify log.dir -> log.dirs=C:/kafka/kafka-logs and save it

  ![image](https://user-images.githubusercontent.com/24723794/163668640-cc63f94c-3891-4b5c-a08b-4e83e2acd982.png)

 - Go to your Kafka directory, open another cmd and execute **.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties** to start Zookeeper 

  ![image](https://user-images.githubusercontent.com/24723794/163668874-1c7ded34-dd00-4228-8158-3b5429c93789.png)
  
 - Go to your Kafka directory, open cmd and execute **.\bin\windows\kafka-server-start.bat .\config\server.properties**  to start kafka server

  ![image](https://user-images.githubusercontent.com/24723794/163668948-ee8167a3-7cba-4fef-b2bf-d3daa9c94153.png)

Your Apache Kafka is up and Running !! 😊
  
Now, Clone the Project Open HelloProducer and HelloConsumer in different Intellij Window.
  - Run HelloProducer Application, 
  - Run HelloConsumer Application

You can also test the message produced by producer on the topic by executing below command in your kafka folder: 
  **.\bin\windows\kafka-console-consumer.bat -bootstrap-server 127.0.0.1:9092 -topic <topic name> -group first_app**

  
