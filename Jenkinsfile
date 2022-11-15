#!/usr/bin/env groovy
node {
 agent {
    docker {
        image 'maven:3-alpine'
        args '-v $HOME/.m2:/root/.m2'
    }
 }
 properties([disableConcurrentBuilds()])
 try {
   namespace = "default"
   projectId = "c-kzd9h:p-vd76s"
   deployment = "kafka-consumer-processor"
   dockerFile = "Dockerfile"
   imageName = "kafka-consumer-processor"
   registry = "chjplove"
   version = "latest"
   stage('Checkout Branch') {
       checkout scm
       sh "git checkout ${env.BRANCH_NAME} && git reset --hard origin/${env.BRANCH_NAME}"
   }
   stage('Build Image') {
       sh "docker build -t ${registry}/${imageName}:${version} -f ${dockerFile} ."
   }
   stage('Push Image') {
       sh "docker tag ${registry}/${imageName}:${version} ${registry}/${imageName}:${version}"
       sh "docker login -u ${env.DOCKER_USERNAME} -p ${env.DOCKER_PASSWORD} docker.io"
       sh "docker push ${registry}/${imageName}:${version}"
   }
   switch(env.BRANCH_NAME) {
       case 'develop':
            stage("Deploy") {
               sh "echo ${env.BRANCH_NAME}"
//                def oldContainerID = sh(script: "docker ps -a -q  --filter ancestor=${registry}/${imageName}:${version}", returnStdout: true)
//                def status = true
//                if ("${oldContainerID}" != '') {
//                    echo "Deleting image id: ${oldContainerID}..."
//                    echo status
//                    sh "docker stop ${oldContainerID}"
//                    sh "docker container rm ${oldContainerID}"
//                }
                // check old image if exists and delete old image
//                def oldImageID = sh(script: "docker images -qf reference=${registry}/${imageName}:${version}",returnStdout: true)
//                if ("${oldImageID}" != '') {
//                    echo "Deleting image id: ${oldImageID}..."
//                    sh "docker rmi -f ${oldImageID}"
//                    sh "docker rmi \$(docker images -qf reference=${registry}/${imageName} -q)"
//                }
//                 sh """curl -k --location --request POST '${env.RANCHER_API_URL}/project/${projectId}/workloads/deployment:${namespace}:${deployment}?action=redeploy' \
//                         --header 'Authorization: Bearer ${env.RANCHER_API_TOKEN}'"""
            }
            break;

       case 'uat':
            stage("Deploy") {
                sh "echo ${env.BRANCH_NAME}"
            }
            break;

       case 'main':
           stage("Deploy") {
//                 sh """curl -k --location --request POST '${env.RANCHER_API_URL}/project/${clusterId}:${projectId}/workloads/deployment:${namespace}:${deployment}?action=redeploy' \
//                         --header 'Authorization: Bearer ${env.RANCHER_API_TOKEN}'"""
           }
           break;
   }
 } catch (e) {
   currentBuild.result = "FAILED"
   throw e
 }
}