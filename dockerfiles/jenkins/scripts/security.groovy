import jenkins.model.*
import javaposse.jobdsl.plugin.GlobalJobDslSecurityConfiguration

// disable security

Jenkins.instance.disableSecurity()

// disable Job DSL script approval

GlobalConfiguration.all().get(GlobalJobDslSecurityConfiguration.class).useScriptSecurity=false
GlobalConfiguration.all().get(GlobalJobDslSecurityConfiguration.class).save()