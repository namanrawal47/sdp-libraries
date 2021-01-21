void call() {
  stage('Email Result') {
    env.ForEmailPlugin = env.WORKSPACE      
    emailext body: '''${SCRIPT, template="groovy_html.template"}''', 
    subject: currentBuild.currentResult + " : " + env.JOB_NAME, 
    to: 'rawal_naman@bah.com, oh_jeffrey@bah.com'
  }
}