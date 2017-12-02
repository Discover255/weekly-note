from socket import *
serverName = '127.0.0.1'//这里可以改进
serverPort = 12000
clientSocket = socket(AF_INET, SOCK_DGRAM)
message = input('Input lowercase sentence:')
clientSocket.sendto(bytes(message.encode('utf-8')), (serverName, serverPort))//不应该强制使用UTF-8，有没有智能识别的方法
modifiedMessage, serverAddress = clientSocket.recvfrom(2048)
print(modifiedMessage.decode('utf-8'))
clientSocket.close()
