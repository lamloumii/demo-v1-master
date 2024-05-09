# Step 1: Start with a base image with Java 17
FROM eclipse-temurin:21 AS build

# Step 2: Install Maven 3.2.5 manually
RUN apt-get update && apt-get install -y wget
RUN mkdir -p /usr/share/maven && \
    wget -qO- "https://archive.apache.org/dist/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz" | tar -xzC /usr/share/maven --strip-components=1 && \
    ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# Set the environment variable for Maven
ENV MAVEN_HOME /usr/share/maven

# Step 3: Set the working directory in the container
WORKDIR /app

# Step 4: Copy the Maven pom.xml file and source code into the container
COPY pom.xml .
COPY src ./src

# Step 5: Build the application using Maven and package it into a JAR file
# The -DskipTests option skips running tests to speed up the build process.
RUN mvn clean package -DskipTests

# Step 6: Use a new stage with Java 17 for running the application, to keep the image small
FROM eclipse-temurin:21

# Step 7: Set the working directory in the new container stage
WORKDIR /app

# Step 8: Copy only the built JAR file from the build stage into the new container stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar ./myapp.jar

# Step 4: Define environment variables for SQL Server connection
ENV SPRING_DATASOURCE_URL="jdbc:sqlserver://DESKTOP-ECJR0OG\\SQLEXPRESS01;databaseName=students;portNumber=1434"
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=Azerty
# Step 9: Expose the port the application uses
EXPOSE 8080

# Step 10: Define the command to run the application
ENTRYPOINT ["java", "-jar", "myapp.jar"]
