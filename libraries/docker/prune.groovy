void call() {
  stage("Docker: Prune Images") {
    def images = config.images_to_prune
    images.each{ img ->
    // remove local stale images if exist
      try {
        sh "docker rmi -f \$(docker images --filter=reference=${config.registry}/${config.repo}/${img}:* -q)"
        echo "removed ${img}"
      } catch (err) { echo err.getMessage() }
    } //end images loop
  }
}