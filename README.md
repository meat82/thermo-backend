# thermo-backend
Base project

## Build Project ##
mvn clean install

## Build Docker ##
sudo docker build -t thermo-backend .

## Run Docker ##
sudo docker run --rm -t -d -p 8009:8009 --name thermo-backend thermo-backend:latest

## Stop Docker ##
sudo docker stop thermo-backend

## Start Docker ##
sudo docker stop thermo-backend

## Test using curl ##
curl -X POST localhost:8009/temperatures/add -H 'Content-type:application/json' -d '{"nametemperature_value": 10.3}'
curl -X GET localhost:8009/temperatures/all
