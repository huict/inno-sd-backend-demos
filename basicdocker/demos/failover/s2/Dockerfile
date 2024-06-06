FROM ubuntu


ENV DEBIAN_FRONTEND="noninteractive"

RUN ["apt-get", "update", "-y", "-q"]
RUN ["apt-get", "install", "-y", "-q", "iproute2", "iputils-ping", "socat", "keepalived", "tcpdump"]

COPY --chmod=644 ./s2.conf /etc/keepalived/keepalived.conf

ENTRYPOINT ["keepalived", "-lnD"]