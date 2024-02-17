#!/bin/bash

words=("Americanas" "Fedex" "Amazon" "Correios" "MercadoLivre")
endpoint="http://localhost:8080/package/"

endpoint1() {
    word=${words[$RANDOM % ${#words[@]}]}
    url="$endpoint$word"
    response=$(curl -s -o /dev/null -w "%{http_code}" "$url")
    echo "Call to $url, Status Code: $response"
}

endpoint2() {
    response=$(curl -s -o /dev/null -w "%{http_code}" "$endpoint")
    echo "Call to $endpoint, Status Code: $response"
}

while true; do
    endpoint1
    endpoint2
    sleep 1
done
