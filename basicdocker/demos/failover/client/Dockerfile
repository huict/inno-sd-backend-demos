FROM ubuntu


ENV DEBIAN_FRONTEND="noninteractive"

RUN ["apt-get", "update", "-y", "-q"]
RUN ["apt-get", "install", "-y", "-q", "iproute2", "iputils-ping", "socat"]

ENTRYPOINT ["ping", "192.168.42.200"]