import sympy as sy

x,y,m,b = sy.symbols("x, y, m, b")

y = m*x**2 + b

y2 = y.subs([(m,0.01),(b,0.0)])

y_em_1 = y2.evalf(subs={x:1})

y_lam = sy.lambdify(x,y2,"numpy")

y_lam(x)




