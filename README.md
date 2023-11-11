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

(클라이언트)

![클라이언트 ex1](https://github.com/nahyun0/MyFirstNetWorkProgram/assets/106727030/0d0318dc-ddee-4adb-82eb-7d320d5b0fb7)


![클라이언트 ex2](https://github.com/nahyun0/MyFirstNetWorkProgram/assets/106727030/a6e86430-ab7a-483c-ae68-c1f32c5bcf83)

![클라이언트 ex3](https://github.com/nahyun0/MyFirstNetWorkProgram/assets/106727030/a7957859-4404-455c-ba37-9874e83140b8)

(서버 결과)

![server](https://github.com/nahyun0/MyFirstNetWorkProgram/assets/106727030/23b50787-da68-4460-b04e-3f895eafd31a)


 ###(참조 개념 공부)
 Socket 통신 이란 ? : 서버-클라이언트 간 데이터를 주고 받는 양방향 연결 지향성 통신 , 실시간 데이터 송수신 
 필요: ip 주소  와 port number 
 서버(server) : 데이터 제공 쪽 
 클라이언트(client) : 데이터 요청 받는 쪽 
 ![참조](https://github.com/nahyun0/MyFirstNetWorkProgram/assets/106727030/b504049a-2cae-4758-8416-4430b2129f2b)


