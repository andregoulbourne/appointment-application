# Appointment-application
Appointment application has a spring boot back end and angular front end.   
Containerized using docker and using jenkins pipline with sonar for CI/CD

# Technologies Used
* Java
* Spring Framework
* Maven
* H2 Database
* SQL Scripts
* TypeScript
* Angular
* CSS
* Docker
* Logging
* SonarQube
* Jenkins

# Features
* Login Page

## Running the app

### Run With Docker
* cd <"Project Directory">  
* docker build -t <"image name"> .  
* docker run -p8081:8081 <"image tag">   
  
### Run Locally
* create a application.properties file and change the server port 8081
* cd <"angular sub folder in project">  
* ng build  
* Copy everything in angular/dist/registerAndLoginFrontEnd Into the the src/main/webapp folder  
* mvn spring-boot:run  


![image](https://user-images.githubusercontent.com/84467369/162374973-50019cc2-4853-40ba-af23-78b6c641ab2b.png)
![image](https://user-images.githubusercontent.com/84467369/162374903-f70d7903-2963-4239-8013-bdeb0908e627.png)

