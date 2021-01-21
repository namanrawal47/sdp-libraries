void call(){
    stage("Curl: Upload to Artifact Repository"){
        withCredentials(
            [usernamePassword(
            credentialsId: pipelineConfig.nexus_creds, 
            usernameVariable: 'nexus_username',
            passwordVariable: 'nexus_password')]) {
            dir(config.directory){
                nexus_password=nexus_password.replace('%', '%%')
                try {
                    // upload files to artifact repository; Docker pushes handled by Docker library preferably
                    sh "curl -v --user \"${nexus_username}:${nexus_password}\" --upload-file ${config.file} ${config.url}/${config.file}"
                } catch (err){ echo "could not upload ${config.file}. continuing..." }
            }
        }
    }
}