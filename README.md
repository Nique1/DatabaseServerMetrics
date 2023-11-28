# DatabaseServerMetrics
This console application measures the efficiency of the MS SQL database server and prints out the output to an Excel file.
The main goal of this app is to measure the efficiency of the local and remote database servers.
Note that you need to have created your database servers locally and remotely to run this application.
You can use Google Cloud for remote database server efficiency measurements but the choice of database server provider totally depends on you.
This application uses the Northwind database but you can switch to any other database you want.
### First things first
To run this application on your machine, you must provide:
- your local and remote database server name
- database name
- username and password
In the ConnectionString class provide your data:

<br> ![image](https://github.com/Nique1/DatabaseServerMetrics/assets/84642454/7dc23757-6782-44f6-80b7-86875fc1176d)


You also need to provide the paths, where your Excel files with database server metrics will be stored.
In the FilePaths class provide your paths:
<br> ![image](https://github.com/Nique1/DatabaseServerMetrics/assets/84642454/0d5bf1bf-166d-467b-b3f1-9004de6f6a92)


### How to use it?
In the console simply type database server: local or remote.
<br> ![image](https://github.com/Nique1/DatabaseServerMetrics/assets/84642454/1386dd00-80c9-4f21-8956-f510483cced5)

Then, enter your query.
<br> ![image](https://github.com/Nique1/DatabaseServerMetrics/assets/84642454/6c9658a4-6507-47f5-8412-c0a25b7faa23)

The app will print out to the console the output of your query.
Below you will find Database Operation Metrics:
- Response time of the database
- CPU usage
- Disk usage
- Memory usage

All metrics will be printed to the Excel file in the path you provided earlier. If the path already exists, the new file will be appended with 1,2,3, etc.

### That's all! :) 
