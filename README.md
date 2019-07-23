# DevelopmentBE
*Live Docs found here*
https://corporate-event-planner.herokuapp.com/swagger-ui.html#/user-controller

# Corporate Event Planner

### [Models](##models)

- [User](###User)
- [Event](###Event)
- [Task](###Task)

### [Endpoints](##endpoints)

##### [User Endpoints](###User-Endpoints)

- [Sign Up](#####Signup)
- [Login](#####Login)
- [Update User](#####Update-User)
- [Delete USer](#####Delete-User)

##### [Event Endpoints](###Event-Endpoints)

- [Get All Events](#####Get-All)
- [Get Event](#####Get-Event)
- [Add Event](#####Add-Event)
- [Update Event](#####Update-Event)
- [Delete Event](#####Delete-Event)

## Models

---

### User

```javascript
{
    "userid": 2,
    "username": "JakeTheDude",
    "email": "JakeTheDude@Email.com",
    "companyname": "DevelopersAnonymous",
    "role": "Backend B.A.",
    "userRoles": [],
    "image": null,
    "authority": []
}
```

### Event

```javascript
{
    "eventid": 9,
    "name": "Teambuilding Trip",
    "description": "Take the IT department on a teambuilding getaway in Hawaii",
    "date": "8-23-2019",
    "budget": "$10,000",
    "companyname": "Company A",
    "tasklist": [
        // tasks ...
    ],
    "userList": [
        // list of objects each with a user object nested inside at key "user"
        {
            "user": {
                // User object
            }
        }
    ]
}
```

### Task

```javascript
{
    "taskid": 17,
    "name": "Reservations",
    "description": "Make Hotel Reservations",
    "assigned": "John",
    "completed": false,
    "duedate": "8-1-2019",
    "category": "Service",
    "purchase": [
        {
            "purchaseid": 27,
            "description": "Reserve Hotel Rooms",
            "vendorname": "Mariott Hotel",
            "pointofcontact": "Judy",
            "email": "judyisawesome@email.com",
            "price": "$3,000",
            "qty": 0
        }
    ]
}
```

## Endpoints

### User-Endpoints

---

##### Signup

**Endpoint:** `/signup`
**Type:** `POST`
**Description:**

```
    Sign up a new user
```

**Expected Input**

```javascript
{
    // username and email must be unique

    "username": "testuser",
    "email": "JohnnyGuitar@Email.com",
    "password": "password",
    "role": "Air Guitar Instructor",
    "companyname": "test company",
}
```

---

##### Login

**Endpoint:** `/oauth/token`
**Type:** `POST`
**Description:**

```
    Gets authentication token for user with given credentials
```

**Expected Input**

```javascript
{
    "username": "SomeUser",
    "password": "TheirPassword"
}
```

_sample request_

```javascript
const body = `grant_type=password&username=${username}&password=${password}`;

axios.post(`${BASE_URL}/oauth/token`, body, {
  headers: {
    "Content-Type": "application/x-www-form-urlencoded",
    Authorization: `Basic ${window.btoa("lambda-client:lambda-secret")}`
  }
});
```

---

##### Logout

**Endpoint:** `/oauth/revoke-token`
**Type:** `GET`
**Description:**

```
    Back end will kill any active tokens for user
```

---

##### Update-User

**Endpoint:** `/user/{id}`
**Type:** `PUT`
**Description:**

```
    Update user with given id
```

**Expected Input**

```javascript
{
    "username": "testuser",
    "email": "JohnnyGuitar@Email.com",
    "password": "password",
    "role": "Air Guitar Instructor",
    "companyname": "test company"
}
```

---

##### Delete-User

**Endpoint:** `/user/{id}`
**Type:** `DELETE`
**Description:**

```
    Delete user with given id
```

---

### Event Endpoints

```

```

##### Get-All

**Endpoint:** `/events/all`
**Type:** `GET`
**Description:**

```
    Get a list of all event objects
```

---

##### Get-Event

**Endpoint:** `/events/{id}`
**Type:** `PUT`

_will only fetch and event if it belongs to active user_

**Description:**

```
    Update user with given id
```

---

##### Add-Event

**Endpoint:** `/events/new`
**Type:** `POST`

_keeps giving weird auth errors_

**Description:**

```
    Update user with given id
```

**Expected Input**

```javascript
{
        {
        "name": "Big ole Fun Time",
        "description": "We're gonna have a big ole funt ime",
        "date": "8-23-2019",
        "budget": "$10,000",
        "companyname": "Company A",
        "tasklist": [
            {
                "name": "Reservations",
                "description": "Make Hotel Reservations",
                "assigned": "John",
                "completed": false,
                "duedate": "8-1-2019",
                "category": "Service",
                "purchase": [
                    {
                        "description": "Reserve Hotel Rooms",
                        "vendorname": "Mariott Hotel",
                        "pointofcontact": "Judy",
                        "email": "judyisawesome@email.com",
                        "price": "$3,000",
                        "qty": 0
                    }
                ]
            },
            {
                "name": "RSVP",
                "description": "Have all employees either RSVP or opt out",
                "assigned": "Michelle",
                "completed": false,
                "duedate": "7-15-2019",
                "category": "Task",
                "purchase": []
            }
        ],
        // This should be empty, currently logged in user will be set added to list
        "userList": []
    }
}
```

---

##### Update-Event

**Endpoint:** `/events/edit/{id}`
**Type:** `PUT`

**Description:**

```
    Update event with given id.

    Use this to access and update and sub categories like tasklist or userlist

    if only given one field ex. "tasklist" it will read the data from that field and try to use it to update object
```

**Expected Input**

```javascript
{
        {
        "name": "Big ole Fun Time",
        "description": "We're gonna have a big ole funt ime",
        "date": "8-23-2019",
        "budget": "$10,000",
        "companyname": "Company A",
        "tasklist": [
            {
                "name": "Reservations",
                "description": "Make Hotel Reservations",
                "assigned": "John",
                "completed": false,
                "duedate": "8-1-2019",
                "category": "Service",
                "purchase": [
                    {
                        "description": "Reserve Hotel Rooms",
                        "vendorname": "Mariott Hotel",
                        "pointofcontact": "Judy",
                        "email": "judyisawesome@email.com",
                        "price": "$3,000",
                        "qty": 0
                    }
                ]
            },
            {
                "name": "RSVP",
                "description": "Have all employees either RSVP or opt out",
                "assigned": "Michelle",
                "completed": false,
                "duedate": "7-15-2019",
                "category": "Task",
                "purchase": []
            }
        ],
        "userList": [
            {
                "user" : {
                    // user object
                }
            }
        ]
    }
}
```

---

**Endpoint:** `/events/delete/{eventid}`
**Type:** `DELETE`

_will only delete an event if it belongs to active user_

**Description:**

```
    Deletes event with given ID
```

---

