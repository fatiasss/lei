def prec(s):
    if s<=60:
        return(0.12)
    else:
        return (0.002*s)

def main():
    s= int(input("segundos?"))
    print(prec(s))
main()