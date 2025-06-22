def isPrime(n):
    retval= "Prime"
    for i in range(2, n):
        if n%i==0:
            retval="NotPrime"

    return retval

def checknumbers():
    for i in range (1,100):
        print(i, isPrime(i))

checknumbers()