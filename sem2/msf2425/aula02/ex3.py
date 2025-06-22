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

def main():
    t = np.array([200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100])
    e = np.array([0.6950, 4.363, 15.53, 38.74, 75.08, 125.2, 257.9, 344.1, 557.4, 690.7])
    figure, axis = plt.subplots(2, 2)
    axis[0,0].plot(t, e, marker="o", linestyle="-") #not linear
    axis[0,0].set_xlabel("L")
    axis[0,0].set_ylabel("X")

    axis[0,1].semilogy(t, e, marker="o", linestyle="-")#still curved
    axis[0,1].set_xlabel("L")
    axis[0,1].set_ylabel("log(X)")

    axis[1,0].loglog(t, e, marker="o", linestyle="-")#linear, a relação é de potência
    axis[1,0].set_xlabel("log(L)")
    axis[1,0].set_ylabel("log(X)")
    logt= [math.log(temp) for temp in t]
    loge= [math.log(energy) for energy in e]
    m, b, r2, dm, db = linreg(logt, loge)
    axis[1,1].plot(t, np.exp(b)*(t**m), color="r", linestyle="-")
    axis[1,1].set_xlabel("L")
    axis[1,1].set_ylabel("X")

    axis[0,0].plot(t, np.exp(b)*(t**m), color="r", linestyle="-")
    figure.tight_layout(pad=2.0)
    plt.show() 
main()