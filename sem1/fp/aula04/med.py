
def input1():
    n=0
    med=0
    counter=0
    while n!="":
        n = (input("numero?"))
        if n!="":
            med+=int(n)
            counter+=1
    print(med/counter)
    return med/counter

        
input1()