FROM sonarqube

ARG SONAR_C_PLUGIN_VERSION=1.3.0.1710
RUN curl --create-dirs -sSLo ${SONARQUBE_HOME}/extensions/plugins/sonar-c-plugin-${SONAR_C_PLUGIN_VERSION}.jar https://ci.appveyor.com/api/buildjobs/xhsn3l3maijkg4xg/artifacts/sonar-c-plugin%2Ftarget%2Fsonar-c-plugin-${SONAR_C_PLUGIN_VERSION}.jar