# Download jdk17
Invoke-WebRequest -Uri "https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_windows-x64_bin.zip" -OutFile "$pwd\openjdk-17.0.2_windows-x64_bin.zip"
Expand-Archive -Path $output -DestinationPath "$pwd"
Remove-Item "$pwd\openjdk-17.0.2_windows-x64_bin.zip"

# Download application
Invoke-WebRequest -Uri "https://github.com/ppolushkin/report-gen/blob/ee83156564cfbf2503354425d44e465355b9753c/prod/report-generator-1.1.jar?raw=true" -OutFile "$pwd\report-generator-1.1.jar"
Invoke-WebRequest -Uri "https://github.com/ppolushkin/report-gen/blob/ee83156564cfbf2503354425d44e465355b9753c/prod/application.properties?raw=true" -OutFile "$pwd\application.properties"
Invoke-WebRequest -Uri "https://github.com/ppolushkin/report-gen/blob/ee83156564cfbf2503354425d44e465355b9753c/prod/generate.bat?raw=true" -OutFile "$pwd\generate.bat"