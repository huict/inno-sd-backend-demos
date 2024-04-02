FROM rabbitmq:3-management
COPY --chmod=600 ./cookie /var/lib/rabbitmq/.erlang.cookie
COPY --chmod=600 ./cookie /root/.erlang.cookie
COPY --chmod=644 ./20-cluster.conf /etc/rabbitmq/conf.d/20-cluster.conf