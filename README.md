# base-docker
Base docker image to run Tech & Data Hub CI/CD pipelines.

Includes SBT and Docker support

### How-to-build:
```
VERSION=$(jq -r .version version.json)
docker build -t techhublisbon/base-image:$VERSION .
```

### How-to-run (interactive mode):
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
