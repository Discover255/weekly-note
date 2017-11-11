getx = @(n)((n >= 0).*(n <= 9)*1000 + (n >= 10).*(n <= 16)*(-1500))
gethx = @(n)(1.08.^n)
n = 0:1:25
x = getx(n)
h = gethx(n)
% %here the conv is wrong
% getw = @(n,k)(1000.*(n >= 0).*(n <= k).*(1.08).^(n-k))
% gety = @(n)(sum(getw(n,n(1):1:n(length(n)))))
% y = gety(n)
figure(1)
y = conv(x,h)(1:length(n))
plot(y,'.')