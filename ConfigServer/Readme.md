# To change application.properties on the fly
1º-do the change you want on ConfigServer\src\main\resources\application.properties
2º-curl --location 'localhost:8082/users-ws/users/status/check' \
   --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4YzllMmFjNi1jOGUzLTQyYTctYjZlZS1mMWEyODc2OTdjMzAiLCJleHAiOjE2ODk1ODEzNTIsImlhdCI6MTY4OTU0NTM1MX0.ZUWf8iqxsWlzaiFXY5axkr-Z5zv1ITvs6L6kNusK5lQuibOiqXeKVhqVZQc6vwCr1J6kdTS3yc0_PlM7UCOKMA'


# run rabbitmq image
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management


# run mysql image
docker run --name mysql -p3306:3306 -e MYSQL_ROOT_PASSWORD=123 -d mysql


# assync 
go to D:\Program Files\Java17\bin
keytool.exe -genkeypair -alias apiEncryptionKey -keyalg RSA -dname "CN=joao,OU=ApiDevelopment,O=appsdeveloperblog.com,L=Ottawa,S=lisbon,C=PT" -keypass 123qwe123 -keystore apiEncryptionKey.jks -storepass 123qwe123
