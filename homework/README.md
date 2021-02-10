helm install pg bitnami/postgresql -f postgres/values.yml


kubectl apply -f .


$ minikube service profile-app-service --url
http://192.168.49.2:30840

curl http://192.168.49.2:30840/health
{"status":"OK"}

$ curl http://arch.homework/otusapp/ushakov/health
{"status":"OK"}
