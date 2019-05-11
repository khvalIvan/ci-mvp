import jenkins.model.*

// create seed job based on seedjob_config.xml file

def jobName = "SeedJob"
def configPath = "/usr/share/jenkins/ref/init.groovy.d/seedjob_config.xml"
def configXml = new File(configPath).text
def xmlStream = new ByteArrayInputStream(configXml.getBytes())

Jenkins.instance.createProjectFromXML(jobName, xmlStream)