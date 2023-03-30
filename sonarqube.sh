./gradlew clean \
  build \
  jacocoTestReport \
  codeCoverageReport \
  sonarqube \
  -Dsonar.projectKey=assembleia \
  -Dsonar.host.url=http://0.0.0.0:9000 \
  -Dsonar.login=33e83d95657a944015870cc22eac8f50fbc78d17

#  Rode sonarqube localmente, a imagem pode ser esta docker run -p 9000:9000 sonarqube
#  Crie um projeto e altere os valores deste aquivos para os novos valores
