geth1 = @(n)(-0.5).^n.*(n >= 0)
n = -20:1:20
getu = @(n)(n >= 0)
u = getu(n)
h1 = geth1(n)
figure(1)
plot(conv(h1,u))