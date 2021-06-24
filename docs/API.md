# API details

A [Postman](https://www.postman.com) collection and the relevant documentation can be found [here](https://documenter.getpostman.com/view/97008/TzecE69S) so that you can play with the API anytime before implementing the changes on the app. Make sure to create a new environment with the `BASE_URL` parameter set to `https://asx-auth-test.herokuapp.com/api/v1`.

In any case you can find all the details about the available endpoints below.
All the services below can be accessed at the following base URL: `https://asx-auth-test.herokuapp.com/api/v1/`.

## Genders list
Method: `GET`
Endpoint: `genders`
Parameters: none
Authentication: not required
Response: it returns a list of genders, each with a human readable `name` to be displayed in the app and one `id` to be used for subsequent API calls
Example response: it include the human 
```
{
    "data": {
        "genders": [
            {
                "id": "male",
                "name": "Male"
            },
            {
                "id": "female",
                "name": "Female"
            },
            ...
        ]
    }
}
```

## Signup
Method: `POST`
Endpoint: `users`
Parameters: 
	- `email`
	- `firstName`
	- `lastName`
	- `gender`
Authentication: not required
Notes: 
	- any validation errors will be returned in the response and will be associated to the input parameters
Response: it returns the `token` that can be used for subsequent API calls that require authentication, `refreshToken` can be ignored
Example response:
```
{
    "data": {
        "token": "exxxxxiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2MjQ1NDIzNjcsImV4cCI6ODY0MDAwMTYyNDU0MjM2NywiYXVkIjoiVEVTVCIsImlzcyI6ImFjY291bnRzLmFzeC50ZXN0Iiwic3ViIjoiNjBkNDhjOWZmODZhYTkwMDIxNmM5MTdmIn0.MGKpwh8xZWfPRLcXtWTZ2i-d5Rt0S_OBxxxxxpsU",
        "refreshToken": "870ef290-xxxx-11eb-xxxx-d922fe688855"
    }
}
```

## Fetch user data
Method: `GET`
Endpoint: `users/me`
Parameters: none
Authentication: required, `Authorization` header with value `Bearer YOUR_TOKEN_RETURNED_BY_SIGNUP`
Response: it returns all the relevant data associated to the authenticated user
Example response:
```
{
    "data": {
        "user": {
            "roles": [
                "user"
            ],
            "_id": "60d48c9ff86aa900216c917f",
            "email": "matteo@domain.com",
            "gender": "male",
            "firstName": "Matteo",
            "lastName": "Innocenti",
            "avatarURL": "https://www.gravatar.com/avatar/2b187a476641ace9dc6fdb4cb31f6701",
            "provider": "usernamePassword",
            "username": "matteo@domain.com",
            "createdAt": "2021-06-24T13:46:07.794Z",
            "updatedAt": "2021-06-24T13:46:07.794Z",
            "__v": 0
        }
    }
}
```

## Notes
- make sure to add a large timeout for network requests since the server may take some time to boot after some period of inactivity