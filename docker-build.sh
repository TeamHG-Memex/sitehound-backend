sitehound_backend_version=5.3.1
docker build --tag hyperiongray/sitehound-backend:$sitehound_backend_version .
docker push hyperiongray/sitehound-backend:$sitehound_backend_version

#docker run -d --name=sitehound-backend-$sitehound_backend_version --hostname=sitehound-backend -v /home/ubuntu/sitehound-backend/config/properties-override:/root/sitehound-backend/config/properties-override --link kafka-2.11-0.10.1.1-2.4:hh-kafka --link mongodb:mongodb --link elasticsearch:hh-elasticsearch hyperiongray/sitehound-backend:$sitehound_backend_version

