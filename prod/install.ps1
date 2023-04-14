# Download jdk17
$zip = "$pwd\openjdk-17.0.2_windows-x64_bin.zip"
Invoke-WebRequest -Uri "https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_windows-x64_bin.zip" -OutFile $zip
Expand-Archive -Path $zip -DestinationPath "$pwd"
Remove-Item $zip

# Download application
$applicationProperties="$pwd\application.properties"

Invoke-WebRequest -Uri "https://github.com/ppolushkin/report-gen/raw/master/prod/report-generator-1.1.jar" -OutFile "$pwd\report-generator-1.1.jar"
Invoke-WebRequest -Uri "https://github.com/ppolushkin/report-gen/raw/master/prod/application.properties" -OutFile $applicationProperties
Invoke-WebRequest -Uri "https://github.com/ppolushkin/report-gen/raw/master/prod/generate.bat" -OutFile "$pwd\generate.bat"
Invoke-WebRequest -Uri "https://github.com/ppolushkin/report-gen/raw/master/prod/README.md" -OutFile "$pwd\README.md"
Invoke-WebRequest -Uri "https://github.com/ppolushkin/report-gen/raw/master/prod/img.png" -OutFile "$pwd\img.png"

# Update application.properties
(Get-Content $applicationProperties).Replace("@PWD@", $pwd) | Set-Content $applicationProperties
(Get-Content $applicationProperties).Replace("\", "/") | Set-Content $applicationProperties