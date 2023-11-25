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

<br> ![image](https://github.com/Nique1/DatabaseServerMetrics/assets/84642454/ce411541-9e68-4f57-8cc5-0b32280fb9c0)

You also need to provide the paths, where your Excel files with database server metrics will be stored.
In the FilePaths class provide your paths:
<br> ![image](https://github.com/Nique1/DatabaseServerMetrics/assets/84642454/e7fc3dee-ad26-465a-a00b-f992f0374e07)

### How to use it?
In the console simply type database server: local or remote.
<br> ![image](https://github.com/Nique1/DatabaseServerMetrics/assets/84642454/9cd07789-a9c8-4702-859e-5206e38edd25)

Then, enter your query.
<br> ![image](https://github.com/Nique1/DatabaseServerMetrics/assets/84642454/196bca42-0b8c-4443-9ffd-2b9db805e80b)

The app will print out to the console the output of your query.
Below you will find Database Operation Metrics:
- Response time of the database
- CPU usage
- Disk usage
- Memory usage

All of the metrics will be printed to the Excel file in the path you provided earlier.

### That's all! :) 
