FROM jenkins/jenkins:lts-alpine
 
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY scripts/security.groovy scripts/docker.groovy scripts/artifactory.groovy scripts/sonar.groovy scripts/seedjob.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY scripts/seedjob_config.xml /usr/share/jenkins/ref/init.groovy.d/seedjob_config.xml

USER root