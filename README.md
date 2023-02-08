# EfecteApp
1. To deploy application locally:
    - create .env file in /docker/dev/ catalog, basing on following template:

        MYSQL_NAME=efectenotes
        MYSQL_ROOT_USER=root
        MYSQL_ROOT_PASSWORD=rootpass
        MYSQL_DATABASE=efectenotes
        MYSQL_USER=test
        MYSQL_PASSWORD=test

        change your credentials if you like to

    - RUN command "docker-compose up" on /docker/dev/ path to create and run containers in docker
    - Frontend will be available on http://localhost, backend on http://localhost:8080.
    

2. If you like to open project in intellij or vscode you should add firstly created .env file in run configuration:
    - intellij: Enable envFiles (plugin) -> specify path to .env file
    - od specify those environment variables in run configuration
    - vscode -> create/modify "launch.json" and add line:
       "envFile": "${workspaceFolder}/docker/dev/.env"
