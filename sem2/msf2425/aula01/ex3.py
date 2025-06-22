import numpy as np
import matplotlib.pyplot as plt

def simulation(N):
    X = np.random.normal(4.5,0.5,size=N)
    Xavg = np.mean(X)
    Xerror = np.std(X)/np.sqrt(N)
    print("X")
    print(Xerror)

    Y = np.random.normal(10.0,1.0,size=N)
    Yavg = np.mean(Y)
    Yerror = np.std(Y)/np.sqrt(N)
    print("Y")
    print(Yerror)
    
    Z= X+Y
    Zavg = np.mean(Z)
    Zerror1 = np.std(Z)/np.sqrt(N)
    Zerror2 = Xerror + Yerror
    print("Z")
    print(Zerror1)
    print(Zerror2)

    W= X*Y
    Wavg = np.mean(W)
    Werror1 = (np.std(W)/np.sqrt(N))/Wavg
    Werror2 = (abs(Xerror/Xavg) + abs(Yerror/Yavg))
    print("W")
    print(Werror1)
    print(Werror2)


simulation(100000)
