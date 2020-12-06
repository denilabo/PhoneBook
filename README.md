# PhoneBook
Spring Boot App with mssql server

create database and database user according to the database connection properties in application.properties file

A common problem that trips up many Java developers trying to connect to SQL Server is this error:

com.microsoft.sqlserver.jdbc.SQLServerException: The TCP/IP connection to the host localhost, port 1433 has failed. Error: “Connection refused: connect. Verify the connection properties, check that an instance of SQL Server is running on the host and accepting TCP/IP connections at the port, and that no firewall is blocking TCP connections to the port.”.

Solution: From the Start menu, open SQL Server 2014 Configuration Manager. Click Protocol for SQLEXPRESS under SQL Server Network Configuration on the left pane. On the right pane, right- click TCP/IP, and select Properties. On the TCP/IP Properties dialog box that appears, click the IP Addresses tab. Scroll down to locate the IPALL node. Remove any value, if present for TCP Dynamic Ports and specify 1433 for TCP Port. Click OK. Again right-click TCP/IP on the right pane, and select Enable. On the SQL Server Services node, right-click SQL Server (SQLEXPRESS), and select Restart.
