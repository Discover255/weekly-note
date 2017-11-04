getx = @(n)((n >= 0).*(n <= 9)*1000 + (n >= 10)*(-1500))
gethx = @(n)(1.08.^n)
n = 0:1:25
x = getx(n)
h = gethx(n)
%here the conv is wrong
figure(1)
plot(conv(x,h))