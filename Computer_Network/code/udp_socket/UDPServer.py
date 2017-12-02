from socket import *
serverPort = 12000
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', serverPort))
print("The server is ready to recieve")
##这里的改进就可以参考CSAPP里的并发部分，还有动态链接
##为什么每个循环要创建一个套接字，CSAPP里也有讲
while True:
    message, clientAddress = serverSocket.recvfrom(2048)
    modifiedMessage = message.upper()
    serverSocket.sendto(modifiedMessage, clientAddress)