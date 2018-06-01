# Simple akka cluster on kubernetes cluster

This repository demonstrates how to deploy akka cluster application on kubernetes. 
To try it locally on minikube:
```bash
minikube start
eval $(minikube docker-env)
sbt docker:publishLocal
kubectl create -f k8s/simple-cluster-deployment.yml
kubectl create -f k8s/simple-cluster-service.yml
KUBE_IP=$(minikube ip)
MANAGEMENT_PORT=$(kubectl get svc akka-simple-cluster -ojsonpath="{.spec.ports[?(@.name==\"management\")].nodePort}")
curl http://$KUBE_IP:$MANAGEMENT_PORT/cluster/members | jq
```