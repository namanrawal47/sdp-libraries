void call() {
  stage('Email Result') {
    env.ForEmailPlugin = env.WORKSPACE      
    emailext body: '''${SCRIPT, template="groovy_html.template"}''', 
    subject: currentBuild.currentResult + " : " + env.JOB_NAME, 
    to: 'joe@example.com'
  }
}
