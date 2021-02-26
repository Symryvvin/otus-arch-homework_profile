Установка БД PostgreSQL из официального репозитория bitnami

```
helm install pg bitnami/postgresql -f postgres/values.yml
```
Первоначальные миграции осуществлены в values.yml в виде скрипта создания таблицы. К сожалению никакие другие варианты у меня не заработали
Намеренно пытался использовать запуск скриптов "из коробки" bitnami

Применение манифестов из папки homework
```
kubectl apply -f .
```
Проверка
```

$ minikube service profile-app-service --url
http://192.168.49.2:30840

$ curl http://192.168.49.2:30840/health
{"status":"OK"}

$ curl http://arch.homework/otusapp/ushakov/health
{"status":"OK"}
```

Коллекция Postman с запросами на /api/v1/

**otus-homework-profile.postman_collection.json**



Метрики 

Создамим namespace для служб мониторига 
```
kubectl create ns monitoring
namespace/monitoring created
kubectl config set-context --current --namespace=monitoring
Context "minikube" modified.

```
```
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
```

```
helm install prom prometheus-community/kube-prometheus-stack -f prometheus/values.yml
```

```
minikube addons disable ingress
helm install nginx ingress-nginx/ingress-nginx -f nginx/values.yaml
```
