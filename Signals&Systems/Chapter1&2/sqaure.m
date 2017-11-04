x = @(t, f,duty, p)((mod(t,1/f) > (duty/f))*p + (mod(t,1/f) <= (duty/f))*(-1 * p))
% T = 1/f, threshold = T / 2 = 1 / (f * 2)
%if t mod T > threshold x = 1, if t mod T <= threshold x = 0
t = 0:0.0001:((1/20)*5)
y = x(t, 20, 0.6, 5)
plot(t, y)