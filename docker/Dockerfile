# syntax = docker/dockerfile:latest

FROM openjdk:17
ENV BLOG_API_VERSION=0.0.1
ENV BLOG_API_ROOT_DIR=/usr/blog_api
COPY build/libs/* $BLOG_API_ROOT_DIR/bin/
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar $BLOG_API_ROOT_DIR/bin/blog-api-$BLOG_API_VERSION.jar"]
