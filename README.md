# 

## Model
www.msaez.io/#/123912988/storming/medicaltreatment

## frontend
node -v : 18.17.0
npm -v : 9.9.4


```
cd front
npm install
npm run dev
```


## Before Running Services
### Make sure there is a Kafka server running
```
cd infra
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- receiving
- hospital
- insurance


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- receiving
```
 http :8088/receivings id="id"userName="userName"symptoms="symptoms"reservationDate="reservationDate"pastHistory="pastHistory"ReservationType = "RESERVED"
```
- hospital
```
 http :8088/consultations id="id"patientName="patientName"patientSymptoms="patientSymptoms"pastHistory="pastHistory"consultationContent="consultationContent"videoId="videoId"ConsultationStatus = "RESERVED"
```
- insurance
```
 http :8088/insurances id="id"consultationDate="consultationDate"symptoms="symptoms"paymentId="paymentId"consultationExpense="consultationExpense"
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
