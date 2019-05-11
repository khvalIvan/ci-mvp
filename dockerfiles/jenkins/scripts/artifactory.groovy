import jenkins.model.*
import org.jfrog.*
import org.jfrog.hudson.*
import org.jfrog.hudson.util.Credentials;

// configure Artifactory server

def artifactoryServerId = "artifactory"
def artifactoryServerUrl = "http://artifactory:8081/artifactory"
def deployerCredentialsConfig = new CredentialsConfig("admin", "password", "")
def resolverCredentialsConfig = null
def timeout = 300
def bypassProxy = false
def connectionRetry = 3
def deploymentThreads = 3

def artifactoryServer = [new ArtifactoryServer(
  artifactoryServerId,
  artifactoryServerUrl,
  deployerCredentialsConfig,
  resolverCredentialsConfig,
  timeout,
  bypassProxy,
  connectionRetry,
  deploymentThreads
)]

def artifactoryDesc = Jenkins.instance.getDescriptor("org.jfrog.hudson.ArtifactoryBuilder")
artifactoryDesc.setArtifactoryServers(artifactoryServer)
artifactoryDesc.save()