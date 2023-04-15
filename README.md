# thermo-backend

## Build Project ##
mvn clean install

## Build Docker ##
sudo docker build -t thermo-backend .

## Run Docker ##
sudo docker run -d -p 8009:8009 thermo-backend

## Stop Docker ##
sudo docker stop thermo-backend

## Start Docker ##
sudo docker stop thermo-backend
