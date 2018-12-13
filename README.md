# base-docker
Base docker image to run TDH CI/CD

### How-to-build:
```
VERSION=$(cat version)
docker build -t tdh/base-docker:$VERSION .

```

### How-to-run (interactive mode):
```
docker run -it --name base-docker tdh/base-docker:$VERSION
```

### How-to-test
```
cd test/
sbt test
```

### To-Do:
* Add CI with this image as base image (2nd iteration)
* Deploy image to Docker Hub
