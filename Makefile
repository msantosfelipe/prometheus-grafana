start:
	cd metrics-data/ && docker-compose up -d

stop:
	cd metrics-data/ && docker-compose stop

remove:
	cd metrics-data/ && docker-compose down

do-requests:
	cd metrics-data/ && chmod +x requests.sh && ./requests.sh