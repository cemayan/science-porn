FROM adoptopenjdk/openjdk11:jre-11.0.7_10-alpine
ENV TIME_ZONE Europe/Istanbul
RUN apk --no-cache add \
	tzdata \
    && echo "${TIME_ZONE}" > /etc/timezone \
	&& ln -sf /usr/share/zoneinfo/${TIME_ZONE} /etc/localtime
ADD /target/*.jar content-service.jar
EXPOSE 8082

ENTRYPOINT ["java","-Dspring.profiles.active=prod","-Djava.security.egd=file:/dev/./urandom","-jar","/content-service.jar"]