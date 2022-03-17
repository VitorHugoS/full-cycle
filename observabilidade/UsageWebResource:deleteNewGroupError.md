"workingDirectories": [
    { "directory": "./src/main", "changeProcessCWD": true }
  ]


docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube

docker run --network src-meuvivoempresas-usage_sonarnet -v $(pwd):/usr/src -it sonarsource/sonar-scanner-cli:latest sonar-scanner \
>         -Dsonar.host.url=http://sonar-server:9000 \
>         -Dproject.settings=sonar-project-client.properties \
>         -Dsonar.login=admin \
>         -Dsonar.password=123456