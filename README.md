# Simple akka cluster on kubernetes cluster

This repository demonstrates how to deploy akka cluster application on kubernetes. 
To try it locally on minikube:
```bash
minikube start
eval $(minikube docker-env)
sbt docker:publishLocal
# create serviceAccount and role
kubectl create -f k8s/simple-akka-cluster-rbac.yml
# create deployment
kubectl create -f k8s/simple-akka-cluster-deployment.yml
# create service
kubectl create -f k8s/simple-akka-cluster-service.yml
KUBE_IP=$(minikube ip)
MANAGEMENT_PORT=$(kubectl get svc akka-simple-cluster -ojsonpath="{.spec.ports[?(@.name==\"management\")].nodePort}")
curl http://$KUBE_IP:$MANAGEMENT_PORT/cluster/members | jq
```