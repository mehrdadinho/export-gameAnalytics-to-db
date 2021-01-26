# export-gameAnalytics-to-db
by this project, you can export Game Analytics data and persist into a common DataBase.

build with:
**_Java_**, **_Spring boot_**, **_JPA_**, **_Gson_**

# How it works
This project look a specific path for a new link files every 60 second. 

when a new file exist in that path start downloading links,unzip files, read json files and persist them into DataBase.

Then move the downloaded zip file into a folder with name **saved** and delete unzip file.

then configuration DataBase info and paths in project.

# Getting started...
**Step 1. Download Links File Path:**

Create a path to put Game Analytics download links in it. then write the path in _`Config.properties`_ file:

`downloadFilePath=/home/debian/javaProgram/exportGA/downloadLink/`

Now download GameAnalytics file links and put it in `.txt` file and save it in that path.

**Step 2. Listen File Path:**

Create another path for downloaded zip files. Program saves zip files into this path. now write this path in _`Config.properties`_ file too:

`filepath=/home/debian/javaProgram/exportGA/`

**Step 3. Config DataBase Information**

set your database information into _`application.properties`_ :

`spring.datasource.url=jdbc:mysql://localhost:3306/schema?useSSL=false`

`spring.datasource.username=username`

`spring.datasource.password=password`

**Step 4. Run the Program**

Run the Main method from class _`GameAnalyticsExportData.java`_. Spring boot will started and logs can help you to inform
about what happened in background.




