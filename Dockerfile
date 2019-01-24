FROM openjdk:8

LABEL maintainer="techhublisbon@daimler.com"
LABEL version="beta"
LABEL url="https://hub.docker.com/u/techhublisbon"

# Install SBT
RUN echo "deb http://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list

RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823

RUN apt-get update && apt-get -y install sbt

# Will force download and cache of sbt dependencies
RUN sbt sbtVersion

# Install Docker CE
RUN apt -y install apt-transport-https ca-certificates curl \
    software-properties-common net-tools dnsutils

RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -

RUN add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"

RUN apt update && apt -y install docker-ce

## Install JSON parser
RUN apt -y install jq
