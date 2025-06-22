import numpy as np
import matplotlib.pyplot as plt
import math
def linreg(x,y):
    assert len(x) == len(y)
    n= len(x)
    x= np.array(x)
    y= np.array(y)
    intprod=np.sum(x*y)
    xsum=np.sum(x)
    ysum=np.sum(y)
    x2sum=np.sum(x**2)
    y2sum=np.sum(y**2)
    m= (n*intprod-xsum*ysum)/(n*x2sum-(xsum**2))
    b= (x2sum*ysum-xsum*intprod)/(n*x2sum-(xsum**2))
    r2=((n*intprod-xsum*ysum)**2)/((n*x2sum-(xsum**2))*(n*y2sum-(ysum**2)))
    dm= abs(m)*math.sqrt(((1/r2)-1)/(n-2))
    db= dm*math.sqrt(x2sum/n)
    
    print("m="+str(m))
    print("b="+str(b))
    print("r^2="+str(r2))
    print("delta m="+str(dm))
    print("delta b="+str(db))
    return m, b, r2, dm, db
def revinterpolation(y, m, b):
    x = (y-b)/m
    return x
def main():
    t=np.array([0, 5, 10, 15, 20, 25, 30, 35, 40, 45])
    r=np.array([9.676 , 6.355, 4.261, 2.729, 1.862, 1.184, 0.7680, 0.4883, 0.3461, 0.2119])
    figure, axis = plt.subplots(2, 2)
    axis[0,0].plot(t,r, marker="o", linestyle="-")#not linear 
    axis[0,0].set_xlabel("T")
    axis[0,0].set_ylabel("R")
    axis[0,1].semilogy(t,r, marker="o", linestyle="-") #linear, relação é exponencial
    axis[0,1].set_xlabel("T")
    axis[0,1].set_ylabel("log(R)")
    r = np.log(r)
    m, b, r2, dm, db = linreg(t, r)
    axis[0,0].plot(t,np.exp(b)*np.exp(m*t), color="r", linestyle="-")
    axis[1,0].plot(t,np.exp(b)*np.exp(m*t), color="r", linestyle="-")
    axis[1,0].set_xlabel("T")
    axis[1,0].set_ylabel("R")
    figure.tight_layout(pad=2.0)
    plt.show()
    halfx=revinterpolation(b/2, m,b)
    print(halfx)
    print(np.exp(b)*np.exp(m*halfx))
main()
