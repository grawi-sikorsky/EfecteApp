# EfecteApp
1. To deploy application locally:
    - create .env file in /docker/dev/ catalog, basing on following template:<br><br>

        MYSQL_NAME=efectenotes<br>
        MYSQL_ROOT_USER=root<br>
        MYSQL_ROOT_PASSWORD=rootpass<br>
        MYSQL_DATABASE=efectenotes<br>
        MYSQL_USER=test<br>
        MYSQL_PASSWORD=test<br><br>

        change your credentials if you like to<br><br>

    - RUN command "docker-compose up" on /docker/dev/ path to create and run containers in docker
    - Frontend will be available on http://localhost, backend on http://localhost:8080.
    

2. If you like to open project in intellij or vscode you should add firstly created .env file in run configuration:
    - intellij: Enable envFiles (plugin) -> specify path to .env file
    - od specify those environment variables in run configuration
    - vscode -> create/modify "launch.json" and add line:
       "envFile": "${workspaceFolder}/docker/dev/.env"
