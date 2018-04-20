# run once for local dev. used in service-2.
[Environment]::SetEnvironmentVariable("DATASOURCE_URL", "jdbc:mysql://svc-mysql:3306/testdb", "User")
[Environment]::SetEnvironmentVariable("DATASOURCE_USERNAME", "user", "User")
[Environment]::SetEnvironmentVariable("DATASOURCE_PASSWORD", "user", "User")
Write-Host "Done!"
