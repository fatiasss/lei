def fib(n):
    fib0 = 0
    fib1 = 1
    if n==0:
        return(fib0)
    elif n==1:
        return(fib1)
    else:
        for i in range(0,n+1):
            if i == 0:
                fibn = fib(0)
            elif i==1:
                fibnaa= 0
                fibna= fibn
                fibn= fib1
            else:
                fibnaa=fibna
                fibna=fibn
                fibn= fibna + fibnaa
    return(fibn)

def main():
    n = int(input("N?"))
    print(fib(n))

main()
