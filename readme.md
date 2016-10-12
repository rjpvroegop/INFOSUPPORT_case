## Welcome to the CASE1 API!

---

### Methods

There are currently three different ways to access this API:

**htp://localhost:8080/api**

* **/registration**
    * for course enrollment
* **/courseinfo**
    * for course information
* **/quotation**
    * for quotation information

---

#### Registration

---

* @Get **api/registration**

Returns: List of Course objects

Status: 200 OK
Status: 412 Error

<details>
<summary>Example returned data:</summary>

    [
      {
        "id": 0,
        "student": {
          "email": "test@test.nl",
          "name": "Josh",
          "lastName": "Vietti",
          "type": "COMPANY",
          "sd": null,
          "cd": {
            "quotation": "123abc"
          }
        },
        "courseInfo": {
          "id": 15,
          "titel": "C# Programmeren",
          "cursuscode": "CNETIN",
          "startdatum": {
            "year": 2013,
            "month": "OCTOBER",
            "dayOfMonth": 21,
            "monthValue": 10,
            "dayOfWeek": "MONDAY",
            "era": "CE",
            "dayOfYear": 294,
            "leapYear": false,
            "chronology": {
              "id": "ISO",
              "calendarType": "iso8601"
            }
          },
          "duur": "5 dagen"
        }
      }
    ]
    
</details>

---

* @Post **api/registration** 

Expects: Course object in JSON
Action: saves the registration

Status: 200 OK
Status: 412 Unable to save

<details>
<summary>Example registration object:</summary>

    {
        "student": {
            "email": "test@test.nl"
        },
        "courseInfo": {
            "id": 15
        }
    }
    
</details>

---

* @Options **api/registration**

Methods: GET, POST

#### Course information

---

* @Get **api/courseinfo**

Returns: List of CourseInfo objects

Status: 200 OK
Status: 412 Error

<details>
<summary>Example returned data:</summary>

    [
      {
        "id": 31,
        "titel": "Advanced C#",
        "cursuscode": "ADCSB",
        "startdatum": {
          "year": 2013,
          "month": "OCTOBER",
          "dayOfMonth": 14,
          "monthValue": 10,
          "dayOfWeek": "MONDAY",
          "era": "CE",
          "dayOfYear": 287,
          "leapYear": false,
          "chronology": {
            "id": "ISO",
            "calendarType": "iso8601"
          }
        },
        "duur": "2 dagen"
      }
    ]
    
</details>

---

* @Get **api/courseinfo/{id}** 

@PathParam: int {id}

Returns: CourseInfo object

Status: 200 OK
Status: 412 Error

<details>
<summary>Example registration object:</summary>

      {
        "id": 31,
        "titel": "Advanced C#",
        "cursuscode": "ADCSB",
        "startdatum": {
          "year": 2013,
          "month": "OCTOBER",
          "dayOfMonth": 14,
          "monthValue": 10,
          "dayOfWeek": "MONDAY",
          "era": "CE",
          "dayOfYear": 287,
          "leapYear": false,
          "chronology": {
            "id": "ISO",
            "calendarType": "iso8601"
          }
        },
        "duur": "2 dagen"
      }
    
</details>

---

* @Get **api/courseinfo/week/{week}** 

@PathParam: int {week}

Returns: List of CourseInfo objects in that week of current year

Status: 200 OK
Status: 412 Error

<details>
<summary>Example returned data:</summary>

    [
      {
        "id": 31,
        "titel": "Advanced C#",
        "cursuscode": "ADCSB",
        "startdatum": {
          "year": 2016,
          "month": "OCTOBER",
          "dayOfMonth": 14,
          "monthValue": 10,
          "dayOfWeek": "MONDAY",
          "era": "CE",
          "dayOfYear": 287,
          "leapYear": false,
          "chronology": {
            "id": "ISO",
            "calendarType": "iso8601"
          }
        },
        "duur": "2 dagen"
      }
    ]
    
</details>

---

* @Get **api/courseinfo/week/{week}/{year}** 

@PathParam: int {week}
@PathParam: int {year}

Returns: List of CourseInfo objects in that week of chosen year

Status: 200 OK
Status: 412 Error

<details>
<summary>Example returned data:</summary>

    [
      {
        "id": 31,
        "titel": "Advanced C#",
        "cursuscode": "ADCSB",
        "startdatum": {
          "year": 1992,
          "month": "OCTOBER",
          "dayOfMonth": 14,
          "monthValue": 10,
          "dayOfWeek": "MONDAY",
          "era": "CE",
          "dayOfYear": 287,
          "leapYear": false,
          "chronology": {
            "id": "ISO",
            "calendarType": "iso8601"
          }
        },
        "duur": "2 dagen"
      }
    ]
    
</details>

---

* @Post **api/courseinfo** 

Expects: CourseInfo object in TEXT_PLAIN
Action: Saves the courseinfo objects in example format
Rule: Cannot save the same course twice in one week, will ignore new entries

Status: 200 OK
Status: 412 Unable to save

<details>
<summary>Example courseinfo object:</summary>

    Titel: C# Programmeren
    Cursuscode: CNETIN
    Duur: 5 dagen
    Startdatum: 14/10/2013
    
    Titel: C# Programmeren
    Cursuscode: CNETIN
    Duur: 5 dagen
    Startdatum: 21/10/2013
    
</details>

---

* @Options **api/registration**

Methods: GET, POST

#### Quotation

---

* @Get **api/quotation/{week}**

@PathParam: int {week}

Returns: List of course objects of a certain week (CourseInfo & Student)

Status: 200 OK
Status: 412 Error

<details>
<summary>Example returned data:</summary>

    [
      {
        "id": 0,
        "student": {
          "email": "test@test.nl",
          "name": "Josh",
          "lastName": "Vietti",
          "type": "COMPANY",
          "sd": null,
          "cd": {
            "quotation": "123abc"
          }
        },
        "courseInfo": {
          "id": 15,
          "titel": "C# Programmeren",
          "cursuscode": "CNETIN",
          "startdatum": {
            "year": 2016,
            "month": "OCTOBER",
            "dayOfMonth": 21,
            "monthValue": 10,
            "dayOfWeek": "MONDAY",
            "era": "CE",
            "dayOfYear": 294,
            "leapYear": false,
            "chronology": {
              "id": "ISO",
              "calendarType": "iso8601"
            }
          },
          "duur": "5 dagen"
        }
      }
    ]
    
</details>

---

* @Get **api/quotation/{week}/{year}**

@PathParam: int {week}
@PathParam: int {year}

Returns: List of course objects of a certain week and year (CourseInfo & Student)

Status: 200 OK
Status: 412 Error

<details>
<summary>Example returned data:</summary>

    [
      {
        "id": 0,
        "student": {
          "email": "test@test.nl",
          "name": "Josh",
          "lastName": "Vietti",
          "type": "COMPANY",
          "sd": null,
          "cd": {
            "quotation": "123abc"
          }
        },
        "courseInfo": {
          "id": 15,
          "titel": "C# Programmeren",
          "cursuscode": "CNETIN",
          "startdatum": {
            "year": 1992,
            "month": "OCTOBER",
            "dayOfMonth": 21,
            "monthValue": 10,
            "dayOfWeek": "MONDAY",
            "era": "CE",
            "dayOfYear": 294,
            "leapYear": false,
            "chronology": {
              "id": "ISO",
              "calendarType": "iso8601"
            }
          },
          "duur": "5 dagen"
        }
      }
    ]
    
</details>

---

* @Options **api/quotation**

Methods: GET
