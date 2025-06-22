def countdown(n):
    while n!=0:
        print(n)
        n-=1

def main():
    n = int(input("N?"))
    countdown(n)
main()