import jenkins.model.*
import hudson.security.*
import hudson.plugins.sonar.*
import hudson.plugins.sonar.model.TriggersConfig
import hudson.tools.*

def instance = Jenkins.getInstance()

// configure SonarQube servers 

def sonarName = "sonarqube"
def sonarRunnerName = "sonarqube scanner"
def sonarServerUrl = "http://sonarqube:9000"
def sonarAuthToken = ""
def sonarMojoVersion = ""
def sonarAdditionalProperties = ""
def sonarTriggers = new TriggersConfig()
def sonarAdditionalAnalysisProperties = ""
def sonarRunnerVersion = "3.3.0.1492"

def SonarGlobalConfiguration sonarGlobalConf = instance.getDescriptor(SonarGlobalConfiguration.class)
def sonarInst = new SonarInstallation(
    sonarName,
    sonarServerUrl,
    sonarAuthToken,
    sonarMojoVersion,
    sonarAdditionalProperties,
    sonarTriggers,
    sonarAdditionalAnalysisProperties
)

def sonarInstallations = sonarGlobalConf.getInstallations()
sonarInstallations += sonarInst
sonarGlobalConf.setInstallations((SonarInstallation[]) sonarInstallations)
sonarGlobalConf.save()

// configure SonarQube scanner

def sonarRunnerInstDesc = instance.getDescriptor("hudson.plugins.sonar.SonarRunnerInstallation")
def sonarRunnerInstaller = new SonarRunnerInstaller(sonarRunnerVersion)
def installSourceProperty = new InstallSourceProperty([sonarRunnerInstaller])
def sonarRunnerInst = new SonarRunnerInstallation(sonarRunnerName, "", [installSourceProperty])

def sonarRunnerInstallations = sonarRunnerInstDesc.getInstallations()
sonarRunnerInstallations += sonarRunnerInst
sonarRunnerInstDesc.setInstallations((SonarRunnerInstallation[]) sonarRunnerInstallations)
sonarRunnerInstDesc.save()