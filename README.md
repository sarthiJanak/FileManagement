# FileManagement

Hello,

This microservice is for upload the excel file and store the data in DB as well as in file system. So MySql DB along with Spring boot framework has been used.
Please find below all the end-points details to access this service.

## Register Admin user:

This end point is used to create the admin user for the first time. once admin user vreated by using this end-point after that we can create other admin as well as user in system. This API is usefull for single time only.

##### URL: http://localhost:8080/user/registerAdminUser

##### Request type : POST

##### Request payload : 

{
    "firstName":"Admin",
    "lastName":"Test",
    "username":"admin.user",
    "password":"Test@123"
}

##### Response Payload

{
    "firstName":"Admin",
    "lastName":"Test",
    "username":"admin.user",
}

## Authnticate User:

This end point is used to authenticate the user based on username and password.

##### URL: http://localhost:8080/user/authenticate

##### Request type : POST

##### Request : 

{
    "username":"admin.user",
    "password":"Test@123"
}

##### Response :

{
  "token": ""
}

## Add User:

This end point is used to create the admin user as well as normal user. This end point is secured. So we have to pass bearer token in authorization header  along with request payload. Only Admin user can access this end point.
##### URL: http://localhost:8080/user/api/v1/addUser

##### Request Type : POST

##### Headers : Authorization = Beareer token

##### Request : 

{
    "firstName":"Normal",
    "lastName":"User",
    "userName":"normal.user1",
    "password":"Test@456"

}

##### Response : 

{
    "firstName":"Normal",
    "lastName":"User",
    "userName":"normal.user1"
}

## Add Role

This end point is used to creat the roles in system. This end point is secured. So we have to pass bearer token in authorization header  along with request payload. Only Admin user can access this end point.

##### URL : http://localhost:8080/user/api/v1/addRole

##### Request Type : POST

##### Headers : Authorization = Beareer token

##### Request : 

{
    "roleName" : "User"
}

##### Response :

{
  "roleId": 0,
  "roleName": "string"
}

## Add Role Permission

This end point is used to assigne permission to each role. This end point is secured. So we have to pass bearer token in authorization header  along with request payload. Only Admin user can access this end point.

##### URL : http://localhost:8080/user/api/v1/addRolePermission

##### Request Type : POST

##### Headers : Authorization = Beareer token

##### Request : 

{
    "roleId":2,
    "isAdmin":false,
    "viewPermission":true,
    "addEditPermission":false,
    "deletePermission":false
}

##### Response : 

{
  "addEditPermission": true,
  "admin": true,
  "deletePermission": true,
  "isAdmin": true,
  "permissionId": 0,
  "roleId": 0,
  "viewPermission": true
}

## Assigne role to user :

This end point is used to assigne role to user. This end point is secured. So we have to pass bearer token in authorization header  along with request payload. Only Admin user can access this end point.

##### URL : http://localhost:8080/user/api/v1/addUserRole

##### Request Type : POST

##### Headers : Authorization = Beareer token

##### Request : 

{
  "roleId": 0,
  "userId": 0
}

##### Response :

Role assigne successfully.

## File upload :

This end point is used to upload the excel file.  This end point is secured. So we have to pass bearer token in authorization header  along with request payload. Only Admin user can access this end point.

##### URL : http://localhost:8080/records/api/v1/file/upload

##### Request Type : POST

##### Headers : Authorization = Beareer token

##### Parameter typa: Formdata

##### Response :

{
  "fileId": 0,
  "fileName": "string",
  "message": "string",
  "uploadedDate": "yyyy-MM-dd HH:mm:ss"
}

## List all the file.

This end point is used to list all the excel file.  This end point is secured. So we have to pass bearer token in authorization header. All user can access this end point.

##### URL : http://localhost:8080/records/api/v1/file/getAllFiles

##### Request Type : GET

##### Headers : Authorization = Beareer token

##### Response :
[
  {
    "fileId": 0,
    "fileName": "string",
    "uploadedDate": "yyyy-MM-dd HH:mm:ss"
  }
]

## List the file record.

This end point is used to list all the records of excel file. This end point is secured. So we have to pass bearer token in authorization header. All user can access this end point.

##### URL : http://localhost:8080/records/api/v1/file/getFileRecords?fileId=1

##### Request Type : GET

##### Headers : Authorization = Beareer token

##### Response :

{
  "fileId": 0,
  "fileName": "string",
  "message": "string",
  "records": [
    {
      "empty": true
    }
  ],
  "uploadedDate": "yyyy-MM-dd HH:mm:ss"
}

## List all the records from all the files:

This end point is used to list all the records of all the excel file. This end point is secured. So we have to pass bearer token in authorization header. All user can access this end point.

##### URL : http://localhost:8080/records/api/v1/file/getAllFileRecords

##### Request Type : GET

##### Headers : Authorization = Beareer token

##### Response :

[
  {
    "fileId": 0,
    "fileName": "string",
    "message": "string",
    "records": [
      {
        "empty": true
      }
    ],
    "uploadedDate": "yyyy-MM-dd HH:mm:ss"
  }
 ]

