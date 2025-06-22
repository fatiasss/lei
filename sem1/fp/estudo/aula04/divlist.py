def divisors(n):
    divlist=[]
    divsum=0
    for i in range(1,n):
        if n%i==0:
            divlist.append(i)
            divsum+=i
    if divsum<n:
        nclass="deficiente"
    elif divsum==n:
        nclass="perfeito"
    else:
        nclass="abundante"
    
    print("Os divisores de", n, "são", divlist, "e n é um número", nclass)


def main():
    n=int(input("número"))
    divisors(n)
main()