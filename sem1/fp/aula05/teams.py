def AllMatches(lst):
    gamelst=[]
    i=0
    b=0
    for i in range(len(lst)):
        while b<=(len(lst)-1):
            if lst[i]!=lst[b]:
                gamelst.append((lst[i],lst[b]))
            b+=1
        b=0
    return(gamelst)

def inputteamlist():
    lst=[]
    a=1
    while a!="":
        a = (input("Insert a team"))
        lst.append(a)
    lst.pop()
    return(lst)

def main():
    print(AllMatches(inputteamlist()))

main()