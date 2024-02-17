## Prometheus/Grafana Metrics

This project shows how to expose Prometheus metrics from Micronaut and send it to Grafana

### Dependencies 

- The project uses `Docker`
  - To run containers
- Java 17
  - To run application

### How to run

- Run `make start` and it will up a docker-compose with an instance of Prometheus, a Grafana, a Mongo and MongoExporter 

### Executing

- Start Micronaut application
- Access Grafana page (login: admin/admin) and add Prometheus datasource
- Run script `make do-requests` to send requests to the application 

##### URLs

- Prometheus: http://localhost:9090/
- Granafa: http://localhost:3000/?orgId=1
- MongoDB: mongodb://localhost:27017/metrics

### How to stop

- Press Ctrl + C to stop the script `do-requests`
- Run `make stop` to stop docker-compose but keep the containers created with the data
- Run `make remove` to stop docker-compose and remove the containers and all data (not the images)
