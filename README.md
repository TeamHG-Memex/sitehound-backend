# Sitehound's Backend 

The backend for [Sitehound](https://github.com/TeamHG-Memex/sitehound)
 
## Prerequisites
    JDK 8.*
    Maven 3.*

## Required Components
### Containers
Containers are stored in [HyperionGray's docker hub](https://hub.docker.com/u/hyperiongray/dashboard/)


##### Mongodb
define a folder for the data
```bash
sudo mkdir -p /data/db
```
and run the container
```
docker run -d -p 127.0.0.1:27017:27017 --name=mongodb --hostname=mongodb -v /data/db:/data/db hyperiongray/mongodb:1.0
```

##### Kafka
```
docker run -d -p 127.0.0.1:9092:9092 -p 127.0.0.1:2181:2181 --name kafka-2.11-0.10.1.1-2.4 --hostname=hh-kafka hyperiongray/kafka-2.11-0.10.1.1:2.4
```
wait 10 secs for the service to fully start and be ready for connections
    

##### Elasticsearch
```
docker run -d -p 127.0.0.1:9200:9200 -p 127.0.0.1:9300:9300 --name=elasticsearch --hostname=elasticsearch elasticsearch:2.0
```


### Configuration

Properties are defined in /config/properties    


### Dockerized version of Sitehound-backend

Alternatively, a container can be run instead of the local installation

```
sitehound_backend_version=4.0.5
docker run -d --name=sitehound-backend-$sitehound_backend_version --hostname=sitehound-backend -v /home/ubuntu/sitehound-backend/config/properties-override:/root/sitehound-backend/config/properties-override --link kafka-2.11-0.10.1.1-2.4:hh-kafka --link mongodb:mongodb --link elasticsearch:hh-elasticsearch hyperiongray/sitehound-backend:$sitehound_backend_version
```
To configure this container, place your own cofiguration for other components under (i.e.) /home/ubuntu/sitehound-backend/config/properties-override and mount the directory
