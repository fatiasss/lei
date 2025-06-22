ctp = float(input("Insira a sua nota da CTP"))
cp = float(input("Insira a sua nota da CP"))

if ctp <6.5 or cp< 6.5:
    nf = 66
else:
         nf = (0.3*ctp + 0.7*cp)
if nf<10:
        ATPR = float(input("Insira a sua nota da ATPR"))
        APR = float(input("Insira a sua nota da APR"))
        nf = 0.3*max(ctp, ATPR)+0.7*max(cp, APR)
else:
    nf = nf
print("A sua nota final e", nf)



           