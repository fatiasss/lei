def InputFloatList():
    lst=[]
    n=0
    while n!="":
            n=(input("Insira um número"))
            if n!="":
                lst.append(int(n))
    return lst

def countLower(lst, v):
    counter=0
    for val in lst:
        if val<v:
            counter+=1
    return counter

def minmax(lst):
    max=lst[0]
    min=lst[0]
    for val in lst:
        if val>max:
            max=val
        if val<min:
            min=val
    return [min,max]

def main():
    flist=InputFloatList()
    minv = minmax(flist)[0]
    maxv = minmax(flist)[1]
    medv=(minv+maxv)/2
    lower=countLower(flist,medv)
    print("O valor médio entre o min e max da lista é {} e a lista tem {} valores inferiores a esse".format(medv,lower))


main()