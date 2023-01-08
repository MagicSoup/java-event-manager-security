# Event Manager : The security application

## Quality

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=EventManager_java-event-manager-security-app&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=EventManager_java-event-manager-security-app)

## Documentation

### Docker installation

On Ubuntu follow the guideline : https://docs.docker.com/engine/install/ubuntu/#prerequisites

### Launch a PostgreSQL instance with Docker

Follow guideline : https://hevodata.com/learn/docker-postgresql/

#### PostgreSQL installation with Docker

Pull the PostgreSQL image :
```
docker pull postgres
```

Run the PostgreSQL Docker image by providing a default user and binding the data directory from the 
image to a local directory in order to keep the data each time the image is launched.
```
docker run --name postgresql \
-e POSTGRES_USER=manager \
-e POSTGRES_PASSWORD=p@ssw0rd \
-p 5432:5432 \
-v /data:/var/lib/postgresql/data -d postgres
```
You need to change the /data by a local path  where the image data will be registered.
```
docker container ls
docker ps -a
```
These commands allow you to see and retrieve the container ids that are running on our computer.

To start and stop the container you can also execute the following commands :
```
docker stop postgresql

docker start postgresql
```

#### PGAdmin installation with Docker

PGAdmin is a web based interface that allow you to easily manage your PostgreSQL instance.

Pull the PGAdmin image :
```
docker pull dpage/pgadmin4:latest
```
Run the PGAdmin Docker image by providing a default user. You need to replace the **manager@domain.local** by a real email address.
```
docker run --name pgadmin -p 8081:80 \
-e 'PGADMIN_DEFAULT_EMAIL=manager@domain.local' \
-e 'PGADMIN_DEFAULT_PASSWORD=p@ssw0rd' \
-d dpage/pgadmin4
```
Verify that the container is correctly running 
```
docker container ls
docker ps -a
```
To start and stop the container you can also execute the following commands :
```
docker stop pgadmin

docker start pgadmin
```
You can then access to the interface by going to the following address : http://localhost:8081/ with
username **manager@domain.local** (replaced) and password **p@ssw0rd**

Once logged, you can add a new server, for the connection you will need the IpAddress of the PostgreSQL container instance.
You can retrieve it by executing the following command :
```
docker inspect postgresql -f '{{json .NetworkSettings.Networks }}'
```