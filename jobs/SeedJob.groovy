pipelineJob('PipelineJob') { 
  def repo = 'https://github.com/khvalIvan/ci-mvp.git'
  definition { 
    cpsScm { 
      scm { 
        git { 
          remote { url(repo) } 
          branches('master') 
          scriptPath('Jenkinsfile') 
          extensions { }
        } 
      } 
    } 
  }
}
queue('PipelineJob')