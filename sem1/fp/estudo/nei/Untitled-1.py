def isPrime(n):
    retval="Is Prime"
    for i in range (2,n):
        if n%i==0:
            retval="Not Prime"
    return retval

def main():
    for i in range(1, 101):
        print(i, isPrime(i))

main()
