def fileFunc(filename):
    counter=0
    clublst=[]
    with open(filename, 'r', encoding="utf8") as fileobj:
        while True:
            line= fileobj.readline()
            if line=='':break
            if counter!=0:
                linelst=line.split(',')
                linetup=tuple(linelst[x] for x in range(len(linelst)))
                clublst.append(linetup)
            counter+=1
    return clublst
def countrylist(country, clublst):
    retclub=list(club for club in clublst if club[2]==country)
    print([(club[1],club[0], club[3]) for club in retclub])

def countrylistfile(country, clublst, filename):
    retclub=list(club for club in clublst if club[2]==country)
    with open(filename, "w") as fileobj:
        for club in retclub:
            wstr= '{}, {}, {} \n'.format(club[1],club[0],club[3])
            fileobj.write(wstr)

def clubdict(clublst):
    retdict = {}
    for club in clublst:
        if club[2] in retdict.keys():
            retdict[club[2]].append(club[1])
        else:
            retdict[club[2]]= [(club[1])]
    return retdict
def clubdictfull(clublst):
    retdict = {}
    for club in clublst:
        if club[2] in retdict.keys():
            retdict[club[2]].append(club)
        else:
            retdict[club[2]]= [(club)]
    return retdict
def rise(clublst):
    newclublst=[club for club in clublst if club[-1]=='+\n']
    sortlst = sorted(newclublst, key=lambda club: int(club[4]), reverse=True)
    return sortlst[0][1]

def clubfind(clubname, clublst):
    clubnamelst= [str(club[1]) for club in clublst]
    if str(clubname).lstrip() in clubnamelst:
        print(clublst[clubnamelst.index(str(clubname).lstrip())])
    else:
        print("Club doesn't exist!")
        newclub= str(input("Try another one"))
        clubfind(newclub, clublst)

def averagerank(clubdictionaryfull):
    rankslst= []
    for country in clubdictionaryfull.keys():
        totalrank=0
        clubcount=0
        for club in clubdictionaryfull[country]:
                totalrank+=int(club[0])
                clubcount+=1
        rankslst.append([country, totalrank//clubcount])
    return sorted(rankslst, key=lambda club : club[1], reverse=False)
def main():
    print("L- List All Clubs")
    print("C- Print a country's clubs")
    print("W- Write a country's clubs into a specified file")
    print("D- Show a dictionary of all clubs by country")
    print("R- Show the Highest rank riser")
    print("F- Find a club's info")
    print("A- Show countries by average rank")
    print("0- Quit")
    choice = str(input("Choose an operation"))
    clublst=fileFunc('Soccer_Football_Clubs_Ranking.csv')
    clubdictionary= clubdict(clublst)
    clubdictionaryfull= clubdictfull(clublst)
    if choice.lower()=="l":
        print(clublst)
        main()
    elif choice.lower()=="c":
         country= str(input("Country?"))
         countrylist(country, clublst)
         main()
    elif choice.lower()=='w':
         country= str(input("Country?"))
         filename= str(input("Output?"))
         countrylistfile(country, clublst, filename)
         main()
    elif choice.lower()=="d":
         print(clubdictionary)
         main()
    elif choice.lower()=="r":
         
         print("The highest riser is:", rise(clublst))
         main()
    elif choice.lower()=="f":
         newclub= str(input("Find a club"))
         clubfind(newclub, clublst)
         main()
    elif choice.lower()=="a":
        print(averagerank(clubdictionaryfull))
        main()
    elif choice=="0":
        exit()
    else:
        print("That's not a valid option")
        main()


         
    
    




main()