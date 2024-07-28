docker image remove eurekaserver-mseureka --force
docker image remove mscards-mscards --force
docker image remove mscreditavaluator-mscreditevaluator --force
docker image remove mscloudgateway-msgateway --force
docker image remove msclients-msclients --force

docker compose up -d

cd ./eurekaserver

docker compose up -d

cd ../mscreditavaluator

docker compose up -d

cd ../mscards

docker compose up -d

cd ../msclient

docker compose up -d

cd ../mscloudgateway

docker compose up -d