#!/usr/bin/env bash

#build
echo "starting init.sh"
mvn clean install -P dev -Dmaven.test.skip=true
#mvn package -P dev -Dmaven.test.skip=true
chmod +x target/sitehound-backend-*.jar

#stop
for KILLPID in `ps ax | grep sitehound-backend | awk ' { print $1;}'`; do
  #kill -9 $KILLPID;
  echo $KILLPID;
done

#deploy

ls -al target
mv target/sitehound-backend-*.jar /root/sitehound-backend/sitehound-backend.jar
ls -al /root/sitehound-backend
file /root/sitehound-backend/sitehound-backend.jar


cd ../
#clean
rm -rf target
#rm -rf logs
#rm nohup.out

#score feature
rm -rf /root/sitehound-backend/scorer/index-directory
mkdir -p /root/sitehound-backend/scorer/index-directory

echo "deployed!"
