# A demo project with Spring Boot, JDBC, REST api and PostgreSQL
* Database created on **Postgres** with following config<br>
    * Url = "jdbc:postgres://localhost:6543/test2"
    * Table name =  student
* Sample table

    |   sid |  sname        | sclass    |
    |-------|---------------|-----------|
    |   1   |   Puneet      |   12      |
    |   2   |   Sarvesh     |   12      |
    |   3   |   Raj         |   10      |
    |   5   |   Himanshu    |   9       |  
* Params info -
    * sid - integer, serial, primary key of DB
    * sname - string
    * sclass - integer
## Setting up docker
* Run command "docker compose up" with the same directory of project.
* Select the docker container and open bash.
* Run "psql -d test2 -U test2" to connect with the Database.

**Note** : Username for database is "test2" and password for test2 is "root" ans table name is "student".<br> Port for psql db is "6543".
## API Info
* **Get API** - localhost:8080/student/(sid) to fetch by sid or localhost:8080/students to fetch all entries
* **Post API** - Can accept data in JSON, XML, and form format with API localhost:8080/student. No need to mention sid as sid is set to serial. May cause error if tried to pass an sid by own.
* **Delete API** - localhost:8080/student/(sid) to delete student by sid. Return deleted (student name) if entry found else return not found.
* **Put API** - localhost:8080/student, only updates the existing entry by sid. Return a not found object if data don't exist.