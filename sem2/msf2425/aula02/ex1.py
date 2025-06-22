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

def interpolation(x, m, b):
    y= m*x+b
    return y

def main():
    x = np.array([2.3, 2.2, 2.0, 1.8, 1.6, 1.4, 1.2, 1.0])
    xexp = np.array([2.3, 2.2, 2.0, 1.12, 1.6, 1.4, 1.2, 1.0])
    l = np.array([222.0, 207.5, 194.0, 171.5, 153.0, 133.0, 113.0, 92.0])
    m, b, r2, dm, db= linreg(l, x)
    mexp, bexp, r2exp, dmexp, dbexp= linreg(l, xexp)
    figure, axis = plt.subplots(2, 2)
    axis[0,0].plot(l, x, marker="o", linestyle="-")
    axis[0,0].plot(l, m*l+b, color='r', linestyle='-')
    axis[0,1].plot(l, xexp, marker="o", linestyle="-")
    axis[0,1].plot(l, mexp*l+bexp, color='b', linestyle='-')
    plt.show()
    print(interpolation(165,m,b))
    
    
main()
