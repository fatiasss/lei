def fib(n):
    n = int(n)
    if n==0:
        return 0
    elif n==1:
        return 1
    else:
        return fib(n-1)+fib(n-2)

def main():
    n=input("N?")
    print(fib(n))

main()