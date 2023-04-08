## File Store
Simple service that implements CRUD operations for files.

## Initial setup & generation

### Installation guide

* Install [SDK man](https://sdkman.io/)

* Install micronaut `3.8.3` using SDK man.
```bash
sdk update
sdk install micronaut 3.8.3
```

## Packaging and Running tests

* If you just want to compile and run service
```bash
./mvnw clean package -DskipTests
```

* To run `unit` tests only
```bash
./mvnw test
```

## Banner generation

Banner generated using [ascii-banner](https://manytools.org/hacker-tools/ascii-banner/) and saved inside 
`src/main/resources/micronaut-banner.txt` file.

---

## Running metadata-service locally
To run application locally you need to complete the following steps:
* create and run metadata-db docker using `./create-db.sh`
* run application locally using `./run.sh`
* (Optional) you can run metadata-service as docker container, to do so follow instruction from `run-docker.sh` 

## Docker 

To build and run docker locally check `run-docker.sh` file.
We suggest to do not build fat jar file, but use slim jar file with dependencies. If you want to read more check this page 
[Don’t build fat jars for Docker applications](https://medium.com/holisticon-consultants/dont-build-fat-jars-for-docker-applications-6252a5571248)

We also ignore all files for docker context using `.dockerignore`, so if you want to add additional files into your docker 
image, you should whitelist all such files inside `.dockerignore`. 

### Standard docker
Based on [hcgbu-ol7-java17](https://bitbucket.oci.oraclecorp.com/projects/COVSYS/repos/docker-hcgbu-base-ol7/browse) image (check `Dockerfile`). 
Big docker image size `488MB`, but full JDK presents.

## K8S

### local k8s cluster

Check that kubectl has the right context (should be `rancher-desktop` for local k8s cluster)

```bash
kubectl config get-contexts
```

```
CURRENT   NAME              CLUSTER               AUTHINFO           NAMESPACE
          omics-dev         cluster-cq2dp2anbpa   user-cq2dp2anbpa   
*         rancher-desktop   rancher-desktop       rancher-desktop 
```

Deploy to cluster 
```bash 
kubectl apply -f deployment/app-local.yaml
``` 
There should be single deployment with 1 pod and single `NodePort` service. To access service just check `Base ENVs` section 
from `operation.txt`

Don't forget to clean up 
```bash
kubectl delete -f deployment/app-local.yaml
```

