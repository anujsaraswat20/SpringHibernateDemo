"# SpringHibernateDemo" 

step 1: check post in "application.properties" file and change if required

step 2:
  run command : mvn clean install

step 3:
  after successful compile, go to "target" folder and run "java -jar JAR_FILE_NAME""



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
