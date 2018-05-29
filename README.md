# CarStore
Demo Spring Boot app for sale foreign registration cars.

There are 2 main roles:
- buyers - anonymous users;
- sellers - users, that must be logged in to manipulate
information about own cars.

There is the below routes:
- `/sellers/sign-up` (POST) - to sigh up seller

```json
{
	"email": "xxxxx@gmail.com",
	"firstName": "xxxxx",
	"lastName": "xxxxx",
	"password": "xxxxx",
	"phoneNumber": "xxxxx"
}
```

- `/login` (POST) - to login sellers

- `/sellers/{sellerId}/cars` (POST) - to create new car board.
```json
{
	"price": 12000,
	"year": 2018,
	"countryOfRegistration": "xxxxx",
	"description": "xxxxx"
}
```

- `/sellers/{sellerId}/cars` (GET) - to get all seller's cars. Return in the next format:
```json
{
    "content": [
        {
            "createdAt": "2018-05-26T15:51:01.053+0000",
            "updatedAt": "2018-05-26T15:51:01.053+0000",
            "id": 2,
            "price": 12000,
            "year": 2018,
            "countryOfRegistration": "Germany",
            "description": "NEW",
            "seller": {
                "createdAt": "2018-05-26T15:31:51.833+0000",
                "updatedAt": "2018-05-26T15:31:51.833+0000",
                "id": 1,
                "email": "xxxxx@gmail.com",
                "firstName": "xxxxx",
                "lastName": "xxxxx",
                "password": "$2a$10$KF5//oqpHf6TlxODayTxcOdQBsS3FAUwxZ8hT.WWLxCnzDNqKkNBG",
                "phoneNumber": "xxxxx"
            }
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true
        },
        "pageSize": 20,
        "pageNumber": 0,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "first": true,
    "sort": {
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 1,
    "size": 20,
    "number": 0
}
```

- `/sellers/{sellerId}/cars/{carId}` (PUT) - to update info about particular car

- `/sellers/{sellerId}/cars/{carId}` (DELETE) - to delete info about particular car

- `/logout` (POST) - to logout seller
