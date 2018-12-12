FROM java:8

MAINTAINER techhublisbon@daimler.com

RUN echo "deb http://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list

RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823

RUN apt-get update && apt-get -y install sbt

# Will force download and cache of sbt dependencies
RUN sbt sbtVersion
