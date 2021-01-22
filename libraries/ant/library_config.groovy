fields{
    optional{
        sonar {
            flags = String
            enabled = Boolean
            properties_file = String
            creds = String
            java_binaries = String
            exclusions = String
            timeout = int
        }
    }
    required{
        source_dir = String
        build_command = String
        build_file = String
    }
}