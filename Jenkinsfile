node('worker') {
    // -- checkout project repository
    stage('Checkout') {
        git url: "https://github.com/curl/curl.git"
    }
    // -- build
    stage('Build') {
        sh "./buildconf && ./configure && make"
    }
    // -- execute unit tests
    stage('Test') {
        sh "make test"
    }
    // -- execute sonar-scanner and push result into SonarQube
    stage('Sonar-scanner') {
        def scannerHome = tool 'sonarqube scanner';
        withSonarQubeEnv('sonarqube') {
          sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=curl -Dsonar.projectVersion=${currentBuild.number}"
        }
    }
    // -- prepare build artifact
    stage('Prepare build artifact') {
        sh "./maketgz ${currentBuild.number}"
    }
    // -- check quality gates in SonarQube; fail job if quality gates are not passed
    stage('SonarQube QG check') {
        timeout(time: 1, unit: 'HOURS') {
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
                error "Pipeline aborted due to quality gate failure: ${qg.status}"
            }
        }
    }
    // -- push the artifact into artifactory
    stage('Push to artifactory') {
        def server = Artifactory.server 'artifactory'
        def buildInfo = Artifactory.newBuildInfo()
            
        buildInfo.setName "curl"
        buildInfo.setNumber "${currentBuild.number}"
        def tarName = "curl-${currentBuild.number}.tar.gz"
        def uploadSpec = """{
            "files": [
                {
                "pattern": "${tarName}",
                "target": "example-repo-local/${tarName}"
                }
            ]
        }"""
            
        server.upload spec: uploadSpec, buildInfo: buildInfo
        server.publishBuildInfo buildInfo
    }
}