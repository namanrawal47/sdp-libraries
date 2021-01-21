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
            ant_command += " " + config.build_file
          } else { error("missing build file") }
          
          // do not run if SonarQube flag is set to 'true'
          if(config.sonar.flags && config.sonar.enabled==true) { error("please use build_and_scan() step instead") }
          else { echo "current ant command: " + ant_command }
          
          //check Jenkins agent type and run command
          if(pipelineConfig.agent_type == "windows") {
            bat ant_command
          } else { sh ant_command }
        } // end dir block
    } // end stage block
}