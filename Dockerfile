FROM       hyperiongray/ubuntujava8:1.0.0

MAINTAINER Tomas <tfornara@hyperiongray.com>


RUN mkdir -p /root/sitehound-backend/config/properties
RUN mkdir /root/sitehound-backend/config/properties-override
RUN mkdir /root/sitehound-backend/temp
RUN mkdir /root/sitehound-backend/logs

WORKDIR /root/sitehound-backend
COPY ./config/properties /root/sitehound-backend/config/properties
COPY ./config/stop-words /root/sitehound-backend/config/stop-words
COPY ./config/web-category-word-list /root/sitehound-backend/config/web-category-word-list
COPY ./src /root/sitehound-backend/src
COPY ./profiles /root/sitehound-backend/profiles
COPY ./pom.xml /root/sitehound-backend
COPY ./build.sh /root/sitehound-backend

ENV JAVA_OPTS="-server -Xms4096M -Xmx8192M -XX:MaxPermSize=256m -verbose:sizes"
RUN ["/root/sitehound-backend/build.sh"]
CMD ["java", "-jar",  "/root/sitehound-backend/sitehound-backend.jar"]
