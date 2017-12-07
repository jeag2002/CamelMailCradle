Java project created in Eclipse Mars 2 Release (4.5.2) Windows 10

Java jdk1.8.0_101 (64 bits)
Maven 3.3
JUnit 4.9
Spring Boot 1.2.2
Apache ActiveMQ 5.15.2
Apache Camel 2.15.0
stomp.js 2.3.3

PREREQUESITES:

1)Install Apache ActiveMQ 5.15.2 (when it launch the management console can be accessible by http://127.0.0.1:8161)

2)
Create a queue named rtmessages 
(Camel identify it as activemq:queue:rtmessages. Necessary for the STOMP project)
Create a topic named rtmessages 
(Camel identify it as activemq:topic:rtmessages. Necessary for the Websocket project)

3)Unblock Gmail policy of deny access from third parties apps without 2 level authentication
https://support.google.com/accounts/answer/6010255?hl=es-419

Or generate two-pair keys from Gmail, and use the 2 level of authentication
https://mpashworth.wordpress.com/2017/05/03/sending-email-to-gmail-in-apache-camel/

4)Put your credentials (GMAIL username, password) in the file application.properties of activeMQCamel project

ARCHITECHTURE:
Send mail using data from http page using Websockets + ActiveMQ/STOMP in the client part and 
ActiveMQ/Apache Camel in the server part. 

Two projects:

activeMQStomp ==> client part. launch two http pages.

http://localhost:8080/index.html ==> Websockets + ActiveMQ  (using topic rtmessages)
http://localhost:8080/indexStomp.html ==> Stomp + ActiveMQ  (using queue rtmessages)

activeMQCamel ==> server part. Accept and redirect queries from topìc rtmessages/queue rtmessage 
and redirect information to a mta (google gmail) by SMTP(s) protocol

