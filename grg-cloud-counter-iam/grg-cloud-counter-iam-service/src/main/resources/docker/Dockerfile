FROM 10.1.7.60:9001/jre:8
MAINTAINER ibank <ibank@grgbanking.com>

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx384m -Djava.security.egd=file:/dev/./urandom"

WORKDIR /application
EXPOSE 9090
ADD grg-cloud-counter-iam.jar ./
ADD ./lib/* /application/lib/
CMD sleep 60;java -javaagent:/agent/skywalking-agent.jar $JAVA_OPTS -jar -Dloader.path=.,/application/lib/ grg-cloud-counter-iam.jar