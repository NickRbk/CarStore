# CarStore
Demo Spring Boot app for sale foreign registration cars.

There are 2 main roles:
- buyers - anonymous users;
- sellers - users, that must be logged in to manipulate cars
information (only information they added).

_**There are the below routes for Sellers**_:
- `/auth/sign-up` (POST) - to sigh up seller

```json
{
	"email": "xxxxx",
	"firstName": "xxxxx",
	"lastName": "xxxxx",
	"password": "xxxxx",
	"phoneNumber": "xxxxx"
}
```

- `/login` (POST) - to login sellers

```json
{
	"email": "xxxxx",
	"password": "xxxxx"
}
```

- `/auth/cars` (POST) - to create new car board.
User can add a few cars by one request.
```json
[
	{
		"price": 2300,
		"year": 2018,
		"countryOfRegistration": "FRANCE",
		"description": "NEW BEST FRANCE CAR",
		"model": "Vuyer",
		"type": "leight",
		"mark": "tyugh"
	},
	{
		"price": 80,
		"year": 2008,
		"countryOfRegistration": "POLAND",
		"description": "NEWEST POLAND CAR",
		"model": "Lanor",
		"type": "leight+",
		"mark": "qwerty"
	}
]
```

- `/auth/cars` (GET) - to get all seller's cars. Return in the next format:
```json
[
    {
        "createdAt": "2018-06-21T23:16:25.432+0000",
        "updatedAt": "2018-06-21T23:16:25.432+0000",
        "id": 9,
        "price": 2300,
        "year": 2018,
        "countryOfRegistration": "FRANCE",
        "type": "leight",
        "model": "Vuyer",
        "mark": "tyugh",
        "description": "NEW BEST FRANCE CAR",
        "seller": {
            "createdAt": "2018-06-19T19:01:31.857+0000",
            "updatedAt": "2018-06-19T19:01:31.857+0000",
            "id": 3,
            "email": "xxxxx",
            "firstName": "xxxxx",
            "lastName": "xxxxx",
            "password": "$2a$10$fSB1fuj1CkWDaJRjMv0QR.GUsDCMYbKDABOf8B4Prvckie3buMt6i",
            "phoneNumber": "xxxxx"
        }
    }
]
```

- `/auth/cars/{carId}` (PATCH) - to update info about particular car.

```json
	{
		"price": 2300,
		"year": 2018,
		"countryOfRegistration": "FRANCE",
		"description": "NEW BEST FRANCE CAR",
		"model": "Vuyer",
		"type": "leight",
		"mark": "tyugh"
	}
``` 

- `/auth/cars/{carId}` (DELETE) - to delete info about particular car


_**There are the below routes for Buyers**_:
- `/store` (GET) - to get all cars in store.
- `/store/car?orderBy={{HERE_ORDER_CRITERIA}}` (GET) - to get cars sorted by criteria
(year, price, registration). So there are 3 options to order car:
   
   `/store/car?orderBy=price`
   
   `/store/car?orderBy=year`
   
   `/store/car?orderBy=registration`
   
#### Prerequisites !!!
- app should be run with "dev" profile
- start postgreSQL and write actual info into `application-dev.yml`.

### How to start app?
`mvn spring-boot:run -Dspring.profiles.active=dev`
