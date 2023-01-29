#!/bin/bash

##############################################################################
# Use this script to build the docker images required to run the blog api app.
# Will exit with an error message if any image build fails.
##############################################################################

BLOG_API_VERSION=0.0.1
# to be used as the build context.
PROJECT_ROOT_DIR=../

if ! DOCKER_BUILDKIT=1 docker build -t msaadawi/blog-api:$BLOG_API_VERSION -t msaadawi/blog-api:latest \
  -f Dockerfile $PROJECT_ROOT_DIR; then
  echo "Error: building blog-api image has failed."
  exit 1
fi
echo "Success: blog-api image built successfully."

echo "Images building finished without errors."
