import matplotlib.pyplot as plt
import sympy as sy
import numpy as np

a = 9.81
N = 100
finish = 4
step = 4/N
x = [0]
v = [0]
t = [0]

for i in range(N):
    x.append(x[i] + v[i]*step) 
    v.append(v[i] + a*step) 
    t.append(t[i]+step)

x = np.array(x)
v = np.array(v)
t = np.array(t)

figure, axis = plt.subplots(2, 2)

axis[0, 0].plot(t, x, linestyle="-")
axis[0, 1].plot(t, v, linestyle="-")

plt.show()

