pipeline {
	agent any
	
	tools {
		maven 'maven_3_8_1'
		jdk 'jdk8'
	}
	
	stages {
		stage ('Compile Stage') {	
			
			steps {
					bat "mvn clean install"
			}
			
		}
		
		stage ('Testing Stage') {	
			
			steps {
					bat "mvn test"
			}
			
		}
		
		stage ('Sonar Analysis Stage') {	
			
			steps {
					bat "mvn sonar:sonar -Dsonar.projectKey=appointments-sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=6a3162bad98e5c027c0e128ec39a7a2ef7aaab87"
			}
			
		}
		
	}
	
}