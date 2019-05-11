FROM jenkins/slave:alpine
USER root

RUN apk update && apk add git curl net-tools autoconf automake libtool binutils gcc g++ make

USER jenkins