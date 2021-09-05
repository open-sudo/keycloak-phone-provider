FROM maven:3.8.2-jdk-11 as builder

RUN git clone https://github.com/open-sudo/keycloak-phone-provider.git
RUN mvn clean package -f keycloak-phone-provider/pom.xml 
RUN whoami

# Runner
FROM quay.io/keycloak/keycloak:11.0.3
WORKDIR /tmp

RUN  curl -fSL https://repo1.maven.org/maven2/com/twilio/sdk/twilio/7.53.0/twilio-7.53.0.jar -o twilio-7.53.0.jar \
    && curl -fSL https://repo1.maven.org/maven2/org/ini4j/ini4j/0.5.4/ini4j-0.5.4.jar -o ini4j-0.5.4.jar \
    && curl -fSL https://repo1.maven.org/maven2/org/jacoco/org.jacoco.agent/0.8.5/org.jacoco.agent-0.8.5-runtime.jar -o org.jacoco.agent-0.8.5-runtime.jar

COPY --from=builder  /keycloak-phone-provider/target/modules/keycloak-phone-provider/main/keycloak-phone-provider.jar .
COPY --from=builder  /keycloak-phone-provider/target/modules/keycloak-sms-provider-twilio/main/keycloak-sms-provider-twilio.jar .

USER root
RUN chown -R jboss:jboss .

USER jboss
COPY --from=builder  /keycloak-phone-provider/target/module-add.cli cli/
RUN $JBOSS_HOME/bin/jboss-cli.sh --file=cli/module-add.cli
RUN rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/current
RUN whoami

COPY --from=builder  /keycloak-phone-provider/target/standalone/deployments/keycloak-phone-provider.resources.jar $JBOSS_HOME/standalone/deployments/


