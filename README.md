# Тестовая задача ВебРайз
[Задание](https://disk.yandex.ru/d/ebEt5pUu9exEgw.)

# Endpoints

Получить информацию о пользователе
```
GET http://localhost:8080/users/{id}
```

Создать пользователя
```
POST http://localhost:8080/users
{
    "id": 1,
    "username": "test_user"
}
```

Обновить пользователя
```
PUT http://localhost:8080/users{id}
{
    "username": "test_user_2"
}
```

Удалить пользователя
```
DELETE http://localhost:8080/users/{id}
```

Добавить подписку
```
POST http://localhost:8080/users/{id}/subscriptions
{
    "type": "YANDEX_PLUS"
}
```

Получить подписки пользователя
```
GET http://localhost:8080/users/{id}/subscriptions
```

Удалить подписку
```
DELETE http://localhost:8080/users/{id}/subscriptions/{sub_id}
```

Получить ТОП-3 популярных подписок
```
GET http://localhost:8080/subscriptions/top
```
