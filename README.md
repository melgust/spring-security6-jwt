# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.2/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.2/gradle-plugin/packaging-oci-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#using.devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#io.validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Initial data
In TcUserController use @PreAuthorize keyword, you need to insert a register in tc_role with code A001 or change if you want, for this example the SQL is

INSERT INTO tc_role
(role_id, code, created_at, created_by, role_desc, role_type, status_id, updated_at, updated_by)
VALUES(1, 'UMG001', '2024-08-31 09:04:29.000', 0, 'Administrator', 1, 1, NULL, 0);

### Steps
1. Run project
2. Add the public path in WebSecuritConfig.java (securityFilterChain function below login path) to create a user
.requestMatchers("/user/register").permitAll()
3. Comment @PreAuthorize("hasRole('UMG001')") in TcUserController, function register
4. Create a user with method post and URL http://localhost:8080/user/register (for localhost)
```json
{
    "userId": 0,
    "fullname": "Melvin Cali",
    "username": "mcalic1@miumg.edu.gt",
    "email": "mcalic1@miumg.edu.gt",
    "documentNumber": 12345678900987,
    "birthday": "1994-08-31",
    "tcIdentificationDocument": null,
    "tcRole": {
        "roleId": 1,
        "roleDesc": "Administrator",
        "code": "UMG001",
        "roleType": 1,
        "statusId": 1,
        "createdAt": "2024-08-31T09:04:29.000-06:00",
        "createdBy": 0,
        "updatedAt": null,
        "updatedBy": 0
    },
    "password": "fail",
    "statusId": 1,
    "createdAt": "2024-08-31T09:07:43.000-06:00",
    "createdBy": 0,
    "updatedAt": null,
    "updatedBy": 0,
    "retypePassword": null,
    "photo": null,
    "phone": "345676545"
}
```
If user created you can see the password in data property, don't forget the method is POST
5. Try to login using this json, (first encode base64 your password)
```json
{
    "username": "mcalic1@miumg.edu.gt",
    "password": "Encode base64 password"
}
```
6. Disable all public endpoints