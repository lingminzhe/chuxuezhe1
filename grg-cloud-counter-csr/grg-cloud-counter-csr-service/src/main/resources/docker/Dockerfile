# 基础镜像,已集成SkyWalking Agent客户端在/agent目录
FROM 10.1.7.60:9001/jre:8
# 作者
MAINTAINER ibank <ibank@grgbanking.com>

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx384m -Djava.security.egd=file:/dev/./urandom"

# 指定工作目录
WORKDIR /application
# 声明外露端口
EXPOSE 9011
# 添加到镜像
ADD grgms-fileserver-service.jar ./
ADD ./lib/* /application/lib/
# 启动命令
CMD sleep 60;java -javaagent:/agent/skywalking-agent.jar $JAVA_OPTS -jar -Dloader.path=.,/application/lib/ grgms-fileserver-service.jar
