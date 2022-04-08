pipeline {
	agent any
	
	tools {
		maven 'maven_3_8_1'
		jdk 'jdk8'
	}
	
	stages {
		stage ('Compile Stage') {	
			
			steps {
					sh '''
						mvn clean install
					'''
			}
			
		}
		
		stage ('Testing Stage') {	
			
			steps {
					sh 'mvn test'
			}
			
		}
		
	}
	
}