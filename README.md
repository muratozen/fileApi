# fileApi

"To make a request to the API, you need to obtain a token from the '/login/save' service. With the acquired token, you can make successful requests by adding it as a Bearer token in the request headers. 
If the token is invalid or if you are not using any token, you will receive a 403 error."

"/save": This function is used to save a new file.
"/getAll": This function is required to list information about the uploaded files.
"/update": This function can be used to replace any uploaded file with another file. It expects both the file and the UUID of the old file. You can access the UUID of the old file from the "/getAll" service.
"/delete": This service is used to delete an uploaded file. It expects the UUID of the file you want to delete. You can obtain the UUID information from the "/getAll" method.
"/getFileToByte": By sending the UUID of any uploaded file, this function returns the file content to you as a byte array.

External notes: H2 database is used in the API. Swagger connections have been provided. Additionally, a Postman collection has been added.
