fields{
    required{
        directory = String
        repository_creds = String // the name of a key-value credential pair in Jenkins used to access your artifact repository of choice (ie Nexus, Artifactory, etc.)
        file = String
        url = String
    }
}