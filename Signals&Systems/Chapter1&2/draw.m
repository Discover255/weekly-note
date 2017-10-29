x1 = @(t)(10*exp(-2*t)-5*exp(-0.5*t))
x2 = @(t)(10*exp(-2*t)+5*exp(-0.5*t))
t = [0:0.01:5]
y = x2(t)
plot(t,y)