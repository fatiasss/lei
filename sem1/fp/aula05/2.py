def inputfloatlist():
    lst=[]
    a=1
    while a!="":
        a = (input("Insert a float"))
        lst.append(a)
    lst.pop()
    return(lst)
def countlowerlst(lst, v):
    counter=0
    for a in lst:
        if float(a)<v:
          counter+=1
    print(counter)
    return(counter)

def minmax(lst):
    a=float(min(lst))
    b=float(max(lst))
    return[a,b]


def main():
    lst=inputfloatlist()
    a=minmax(lst)[0]
    b=minmax(lst)[1]
    v=(a+b)/2
    countlowerlst(lst,v)

main()