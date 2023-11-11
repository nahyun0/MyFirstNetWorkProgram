# MyFirstNetWorkProgram

# Calculator in the Cloud (계산기) 
###Develop an Internet calculator program using Java

- Create both Client and Server programs using Java socket API

- Get an (arithmetic) expression from the client side, and
Server solves the expression and sends the result back to the
client
- Define an “application-layer” protocol (communication
message formats) for this application

#과정 설명 
1. 4가지 + , - , * ,/ 사용 
The four basic arithmetic operations - addition, subtraction,
multiplication and division – should be supported
¨ The response from the server may be either answer for the

2.에러 case 별 메세지 작성 
expression or error message (and error type)
¨ The server can handle multiple clients at a time
(ex, 4가지 operation 외 작성시, 세가지 정수 입력시 등) 
   
3. Use ThreadPool & Runable interface
   
4. Server information at a client is stored with a configuration
text file (e.g., server_info.dat) <텍스트 파일써서
ServerAddress=localhost
ServerPort=7899 이런식으로 저장) 

 So, the client program read Server IP and port # from the file
 파일이 존재하지 않을 경우, default 정보 지정하여 처리
(예-localhost, port 1234)
 You may create a separate Class for configuration.

 ![server](https://github.com/nahyun0/MyFirstNetWorkProgram/assets/106727030/68003659-49c6-49e8-84d9-57916ff2c683)
 ![스크린샷 2023-11-12 010003](https://github.com/nahyun0/MyFirstNetWorkProgram/assets/106727030/4c3b1006-5d2f-4311-8335-ac4d4efc8241)


