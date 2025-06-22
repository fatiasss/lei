def div(n):
    assert n>=0
    d=1
    s=0
    print("Os divisores proprios de {:2d} sao")
    while d<n:
        if n%d==0:
            print(d)
            s+=d
            d+=1
        else:
            d+=1
    if s<n:
        print("n e deficiente")
    if s==n:
        print("n e perfeito")
    if s>n:
        print("n e abundante")



def main():
    n=int(input(("N?")))
    div(n)
main()
