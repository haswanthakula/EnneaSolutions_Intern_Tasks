# Static Website Deployed with Docker
This project demonstrates the deployment of a static website using Docker. The website includes HTML, CSS, and JavaScript files, served through a lightweight Nginx web server.

## Docker Basics

### Container Commands
* docker run hello-world - Runs the Hello World application.
* docker ps - Lists running containers.
* docker ps -a - Lists all containers (past and present).
* docker stop <container-id> - Stops a running container.
* docker rm <container-id> - Removes a container.

### Image Commands
* docker images - Lists all images.
* docker rmi <image-id> - Removes an image.

## Deployment Steps

### 1. Prepare the Dockerfile

FROM nginx:alpine    
* //lightweight web server image

COPY . /usr/share/nginx/html    
* //Copy our static files to the web server's default directory

EXPOSE 80

### 2. Build the Docker image

docker build -t static-website .    
* //-t static-website: Tags the image as static-website

### 3. Run the Docker container

docker run -d -p 8080:80 static-website

* //-d: Runs the container in detached mode.
* //-p 8080:80: Maps port 80 of the container to port 8080 on our machine.


# The static website is now deployed and can be accessed locally or through a Docker-enabled environment. Visit http://localhost:8080 to view it.


## Push Docker Image to Docker Hub

* docker login
* docker tag static-website haswanthakula08/static-website:latest
* docker push haswanthakula08/static-website:latest
