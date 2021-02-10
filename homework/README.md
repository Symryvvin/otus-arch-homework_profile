Установка БД PostgreSQL из официального репозитория bitnami

```
helm install pg bitnami/postgresql -f postgres/values.yml
```

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
