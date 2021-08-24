FROM coopersoft/keycloak-callback:11.0.3

WORKDIR /tmp

  # yuntongxin sdk
  # totalvoice sdk
  # twilio sdk
  # tencent sdk
  # aliyun sdk
RUN  curl -fSL https://repo1.maven.org/maven2/com/twilio/sdk/twilio/7.53.0/twilio-7.53.0.jar -o twilio-7.53.0.jar \
    && curl -fSL https://repo1.maven.org/maven2/org/ini4j/ini4j/0.5.4/ini4j-0.5.4.jar -o ini4j-0.5.4.jar \
    && curl -fSL https://repo1.maven.org/maven2/org/jacoco/org.jacoco.agent/0.8.5/org.jacoco.agent-0.8.5-runtime.jar -o org.jacoco.agent-0.8.5-runtime.jar
# yuntongxin provider need gson 2.8.6, keycloak 10.0.2 gson version is low need higher
#RUN curl -fSL https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.6/gson-2.8.6.jar -o gson-2.8.6.jar

# main provider
COPY modules/keycloak-phone-provider/main/keycloak-phone-provider.jar .



#twilio provider
COPY modules/keycloak-sms-provider-twilio/main/keycloak-sms-provider-twilio.jar .


COPY module-add.cli cli/
RUN $JBOSS_HOME/bin/jboss-cli.sh --file=cli/module-add.cli
RUN rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/current

COPY standalone/deployments/keycloak-phone-provider.resources.jar $JBOSS_HOME/standalone/deployments/



