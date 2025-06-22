def medin():
    total=0.0
    count=0
    while True:
        s = (input("Valor?"))
        if s!="":
            s = float(s)
            total+=s
            count+=1
        elif s=="": break
        
    return(total/(count))

def main():
    med = medin()
    print(med)

main()