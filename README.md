This is an example Spring Boot application that is both a Kafka producer and consumer that reports out
its own kakfa performance metrics to InfluxDB.

## Getting Started

In the `example` directory start the Docker composition 

```bash
docker-compose -d
```

The composition starts
* Zookeeper
* Kafka broker, listening on 29092
* InfluxDB, listening on 8086
* Chronograf, listening on 8888

Start the application by running:

```bash
./mvn spring-boot:run
```

With the default configuration the application reports metrics to InfluxDB every 10 seconds.

You can explore the metrics in the `kafka-app.autogen` table using Chronograf:

[http://localhost:8888/sources/0/chronograf/data-explorer]()


## Metrics

The following is the list of metric names observed running the Kafka client library v1.0.1

Metric Name | Group | Tags
------------|-------|------------------------
count | kafka-metrics-count | tags={client-id=consumer-1}]
fetch-throttle-time-avg | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
fetch-throttle-time-max | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
connection-close-total | consumer-metrics | tags={client-id=consumer-1}]
connection-close-rate | consumer-metrics | tags={client-id=consumer-1}]
connection-creation-total | consumer-metrics | tags={client-id=consumer-1}]
connection-creation-rate | consumer-metrics | tags={client-id=consumer-1}]
successful-authentication-total | consumer-metrics | tags={client-id=consumer-1}]
successful-authentication-rate | consumer-metrics | tags={client-id=consumer-1}]
failed-authentication-total | consumer-metrics | tags={client-id=consumer-1}]
failed-authentication-rate | consumer-metrics | tags={client-id=consumer-1}]
network-io-total | consumer-metrics | tags={client-id=consumer-1}]
network-io-rate | consumer-metrics | tags={client-id=consumer-1}]
outgoing-byte-total | consumer-metrics | tags={client-id=consumer-1}]
outgoing-byte-rate | consumer-metrics | tags={client-id=consumer-1}]
request-total | consumer-metrics | tags={client-id=consumer-1}]
request-rate | consumer-metrics | tags={client-id=consumer-1}]
request-size-avg | consumer-metrics | tags={client-id=consumer-1}]
request-size-max | consumer-metrics | tags={client-id=consumer-1}]
incoming-byte-total | consumer-metrics | tags={client-id=consumer-1}]
incoming-byte-rate | consumer-metrics | tags={client-id=consumer-1}]
response-total | consumer-metrics | tags={client-id=consumer-1}]
response-rate | consumer-metrics | tags={client-id=consumer-1}]
select-total | consumer-metrics | tags={client-id=consumer-1}]
select-rate | consumer-metrics | tags={client-id=consumer-1}]
io-wait-time-ns-avg | consumer-metrics | tags={client-id=consumer-1}]
io-waittime-total | consumer-metrics | tags={client-id=consumer-1}]
io-wait-ratio | consumer-metrics | tags={client-id=consumer-1}]
io-time-ns-avg | consumer-metrics | tags={client-id=consumer-1}]
iotime-total | consumer-metrics | tags={client-id=consumer-1}]
io-ratio | consumer-metrics | tags={client-id=consumer-1}]
connection-count | consumer-metrics | tags={client-id=consumer-1}]
heartbeat-response-time-max | consumer-coordinator-metrics | tags={client-id=consumer-1}]
heartbeat-total | consumer-coordinator-metrics | tags={client-id=consumer-1}]
heartbeat-rate | consumer-coordinator-metrics | tags={client-id=consumer-1}]
join-time-avg | consumer-coordinator-metrics | tags={client-id=consumer-1}]
join-time-max | consumer-coordinator-metrics | tags={client-id=consumer-1}]
join-total | consumer-coordinator-metrics | tags={client-id=consumer-1}]
join-rate | consumer-coordinator-metrics | tags={client-id=consumer-1}]
sync-time-avg | consumer-coordinator-metrics | tags={client-id=consumer-1}]
sync-time-max | consumer-coordinator-metrics | tags={client-id=consumer-1}]
sync-total | consumer-coordinator-metrics | tags={client-id=consumer-1}]
sync-rate | consumer-coordinator-metrics | tags={client-id=consumer-1}]
last-heartbeat-seconds-ago | consumer-coordinator-metrics | tags={client-id=consumer-1}]
commit-latency-avg | consumer-coordinator-metrics | tags={client-id=consumer-1}]
commit-latency-max | consumer-coordinator-metrics | tags={client-id=consumer-1}]
commit-total | consumer-coordinator-metrics | tags={client-id=consumer-1}]
commit-rate | consumer-coordinator-metrics | tags={client-id=consumer-1}]
assigned-partitions | consumer-coordinator-metrics | tags={client-id=consumer-1}]
fetch-size-avg | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
fetch-size-max | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
bytes-consumed-total | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
bytes-consumed-rate | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
records-per-request-avg | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
records-consumed-total | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
records-consumed-rate | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
fetch-latency-avg | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
fetch-latency-max | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
fetch-total | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
fetch-rate | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
records-lag-max | consumer-fetch-manager-metrics | tags={client-id=consumer-1}]
