# base-docker

[![CircleCI](https://circleci.com/gh/TechhubLisbon/base-docker.svg?style=svg)](https://circleci.com/gh/TechhubLisbon/base-docker)

Base docker image to run Tech & Data Hub CI/CD pipelines

Docker hub repo: https://hub.docker.com/r/techhublisbon/base-image

### Prerequisites

* Install [SBT](https://www.scala-sbt.org/download.html)
* Install [Docker](https://docs.docker.com/install/linux/docker-ce/ubuntu/) 
* Install [JQ](https://stedolan.github.io/jq/download/)

### Continuous Integration

Check CI status [here](https://circleci.com/gh/techhublisbon/base-docker)

### How-to-build
```
VERSION=$(jq -r .version version.json)
docker build -t techhublisbon/base-image:$VERSION .
```

### How-to-run (interactive mode)
```
docker run --rm -it --name base-image techhublisbon/base-image:$VERSION
```

### How-to-test
```
(cd test/;sbt 'clean' 'test')
```

### How-to-deploy
```
docker login -u techhublisbon -p (password)
docker push techhublisbon/base-image:$VERSION
```

### Dependencies
This docker image uses the following dependencies/libraries:
* [SBT](https://github.com/sbt/sbt)
  * License: [Apache 2.0](https://github.com/sbt/sbt/blob/develop/LICENSE)
* [Docker CE](https://github.com/docker/docker-ce)
  * License: [Apache 2.0](https://github.com/docker/docker-ce/blob/master/components/engine/LICENSE)
* [JQ (Json Parser)]()
  * License: [Creative Commons 3.0](https://github.com/stedolan/jq/blob/master/COPYING)

### ToDo
* Automate deployment to Docker Hub

### Contributing

Review [the contributing guidelines](CONTRIBUTING.md) before you make your awesome contribution

### License

This project is licensed under the terms of the MIT license. See [LICENSE](LICENSE) 