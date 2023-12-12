To run the application locally need to copy repository, start docker and open cmd.
You need to move to program directory and enter command: "mvn clean install", which will create jar file quotes-handler.jar. 
Further, need to build docker container: "docker build -t quotes-handler.jar ."
Now, you need to enter command "docker-compose up -d".
You can watch on endpoints by address "http://localhost:8080/swagger-ui.html".
For stopping programm need to enter command "docker-compose down"
