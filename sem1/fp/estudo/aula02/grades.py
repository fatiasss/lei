ctp = float(input("Nota CTP?"))
cp = float(input("Nota CP?"))

if ctp>6.5 and cp>6.5:
    nf = ctp*0.3 + cp*0.7
    if nf>=10:
        print("NF=", nf)
    else:
        atpr= float(input("Nota ATPR?"))
        apr= float(input("Nota APR?"))
        if atpr>=6.5 and apr>=6.5:
            nr = 0.3*max(ctp,atpr) + 0.7*max(cp, apr)
            print("NF=", nf)
            print("NR=", nr)
        else:
            print("NF=", 66)
            print("NR=", 66)

else:
        atpr= float(input("Nota ATPR?"))
        apr= float(input("Nota APR?"))
        if atpr>6.5 and apr>6.5:
            nr = 0.3*max(ctp,atpr) + 0.7*max(cp, apr)
            print("NF=", 66)
            print("NR=", nr)
        else:
            print("NF=", 66)
            print("NR=", 66)