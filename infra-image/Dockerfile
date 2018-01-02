FROM centos:7

MAINTAINER xu

RUN yum -y upgrade
RUN yum -y install wget

RUN wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-linux-x64.rpm" -O jdk-8.rpm
RUN yum -y install jdk-8.rpm

ENV JAVA_HOME /usr/java/latest

COPY wait-for-it.sh /apps/wait-for-it.sh
RUN chmod +x /apps/wait-for-it.sh

CMD "bash"

