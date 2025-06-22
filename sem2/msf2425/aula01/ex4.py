import numpy as np
import matplotlib.pyplot as plt
Nsave = []
Xaverage = []
def simulate(N, average, std):
    X = np.random.normal(average,std,size=N)
    Xavg = np.mean(X)
    Xerror = np.std(X)/np.sqrt(N)
    Nsave.append(N)
    Xaverage.append(Xavg)
    return


def main():
    average=4.5
    std = 0.5
    for i in np.logspace(1, 4, num=50, base=10):
        simulate(int(i), average, std)
    plt.plot(Nsave, Xaverage, marker="o", linestyle="--")
    plt.axhline(y=average, color='r', linestyle='-')
    plt.axhline(y=average-std, color='b', linestyle='-')
    plt.axhline(y=average+std, color='g', linestyle='-')
    plt.show()
main()
