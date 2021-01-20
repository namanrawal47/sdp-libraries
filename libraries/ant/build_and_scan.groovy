void call(){
    stage("Ant: Build and Scan"){
        //navigate into subdirectory
        dir(config.source_dir){
          //define command string
          ant_command = "ant"

          if(config.build_command) {
            ant_command += " " + config.build_command
          }  else { error("missing build command") }
          if(config.build_file) {
            ant_command += " -f " + config.build_file
          } else { error("missing build file") }
          
          // check for SonarQube flags  
          if(config.sonar.flags && config.sonar.enabled==true) {
            //def sonar=config.sonar
            ant_command += " sonar " + config.sonar.flags
              if(config.sonar.properties_file){
                ant_command += " " + config.sonar.properties_file
            }
              if(config.sonar.creds) {
                  withCredentials([usernamePassword(credentialsId: config.sonar.creds, passwordVariable: 'sonar_pw', usernameVariable: 'sonar_user')]) {
                    ant_command += " -Dsonar.login=${sonar_user} -Dsonar.password=${sonar_pw}" 
              }
            }
              if(config.sonar.java_binaries){
                  ant_command += " -Dsonar.java.binaries=\"" + config.sonar.java_binaries + "\""
            }
              if(config.sonar.exclusions){
                  ant_command += " -Dsonar.exclusions=\"" + config.sonar.exclusions + "\""
            }
            // increase timeout to 5 minutes
            def sonar_timeout = config.sonar.timeout ?: 300
            ant_command += " -Dsonar.ws.timeout=${sonar_timeout}"
            //ant_command += " -Dsonar.ws.timeout=${300}"
          }

          echo "current ant command: " + ant_command
          
          //check Jenkins agent type and run command
          if(pipelineConfig.agent_type == "windows") {
            bat ant_command
          }
  	      else {
            sh ant_command
          } // end else block
        } // end dir block
    } // end stage block
}
