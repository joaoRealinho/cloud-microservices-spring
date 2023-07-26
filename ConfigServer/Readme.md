# To change application.properties on the fly
1º-do the change you want on ConfigServer\src\main\resources\application.properties
2º-curl --location --request POST 'localhost:8012/actuator/busrefresh'

# run rabbitmq image
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management

# run mysql image

docker run --name mysql -p3306:3306 -e MYSQL_ROOT_PASSWORD=123 -d mysql

# Async Encryption

go to D:\Program Files\Java17\bin
keytool.exe -genkeypair -alias apiEncryptionKey -keyalg RSA -dname "
CN=joao,OU=ApiDevelopment,O=appsdeveloperblog.com,L=Ottawa,S=lisbon,C=PT" -keypass 123qwe123 -keystore
apiEncryptionKey.jks -storepass 123qwe123

# run Zipkin image
docker run -d -p 9411:9411 openzipkin/zipkin

