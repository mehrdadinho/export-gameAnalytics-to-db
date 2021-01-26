# export-gameAnalytics-to-db
By this project, you can export Game Analytics data and persist into a common DataBase.

Build with:
**_Java_**, **_Spring boot_**, **_JPA_**, **_Gson_**, **_MySql_**

## How it works
This project looks to a specific path for new link files every **60 seconds**. 

when a new file is created in that path, the Program starts downloading links, unzipping files, reading JSON files, and persists them into DataBase.

Then moves the downloaded zip file into a folder with the name `saved` and delete the unzipped file.

then configuration DataBase info and paths in the project.

## Getting started...
### Step 1. Download Links File Path:

Create a path to put Game Analytics download links in it. then write the path in _`Config.properties`_ file:

```
downloadFilePath=/home/debian/javaProgram/exportGA/downloadLink/
```

Now download GameAnalytics file links and put them in `.txt` file and save it in that path. every link should be in one line. for example:

```
https://go-export.gameanalytics.com/export-data/95065-2018-12-07-3,353.json.gz?e=1605104&s=vsfyprbKBA9iDSb5BoWzQ%3D
https://go-export.gameanalytics.com/export-data/95065-2018-12-07-3,354.json.gz?e=1609055104&s=rlPEY753RUjRukffH3%3D
https://go-export.gameanalytics.com/export-data/95065-2018-12-07-3,355.json.gz?e=1609055104&s=MgArSaUisr6cT4ya4Q%3D
https://go-export.gameanalytics.com/export-data/95065-2018-12-07-3,356.json.gz?e=1609055104&s=QHLuuxZvDvm0ZcGTQg%3D
https://go-export.gameanalytics.com/export-data/95065-2018-12-07-3,357.json.gz?e=1609055104&s=kJAlObalZlE0gzT4J2%3D
https://go-export.gameanalytics.com/export-data/95065-2018-12-07-3,358.json.gz?e=1609055104&s=v3d47Yyo7WZmq7Fwsi%3D
```

### Step 2. Listening File Path:

Create another path for downloaded zip files. The program saves zip files into this path. now write this path in _`Config.properties`_ file too:

```
filepath=/home/debian/javaProgram/exportGA/
```

### Step 3. Config DataBase Information

set your database information into _`application.properties`_. this sample work with MySql DataBase:
```
spring.datasource.url=jdbc:mysql://localhost:3306/schema?useSSL=false
spring.datasource.username=username
spring.datasource.password=password
```

### Step 4. Run the Program

Run the **Main method** from class _`GameAnalyticsExportData.java`_. Spring boot will be started and logs can help you to inform
about what happened in the background.