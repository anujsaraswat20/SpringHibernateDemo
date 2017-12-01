"# SpringHibernateDemo" 
-----------------------------------------------------------------------
To get all customer
URL : localhost:8080/customer/getall
RequestType = GET

Response:
[
    {
        "id": 1,
        "version": 0,
        "name": "test1",
        "age": "10",
        "designation": "manager"
    }
]

-----------------------------------------------------------------------
To create customer
URL : localhost:8080/customer/
RequestType = POST

JSON to create Customer:
{
	"name" : "test1",
	"age" : "10",
	"designation" : "manager"
}
-----------------------------------------------------------------------
