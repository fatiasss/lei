import bisect
def bin(filename):
    wordlst=[]
    with open(filename, "r") as fileobj:
        while True:
            line=fileobj.readline()
            if line=="":break
            linelst=line.split(" ")
            wordlst+=[char for char in linelst]
    return bisect.bisect_left(wordlst, "eb")-bisect.bisect_right(wordlst, "ea")

def bin2(filename):
    wordlst=[]
    with open(filename, "r") as fileobj:
        while True:
            line=fileobj.readline()
            if line=="":break
            linelst=line.split(" ")
            wordlst+=[char for char in linelst]
    return wordlst[bisect.bisect_right(wordlst, "truo")]

def predict(filename, pref):
    wordlst=[]
    with open(filename, "r") as fileobj:
        while True:
            line=fileobj.readline()
            if line=="":break
            linelst=line.split(" ")
            wordlst+=[char for char in linelst if (linelst.index(char)>=bisect.bisect_right(linelst, pref) and linelst.index(char)<=bisect.bisect_left(linelst, pref+"z" ))]
    print(wordlst)

def main():
    print(bin("wordlist.txt"))
    print(bin2("wordlist.txt"))
    pref="a"
    while pref!="":
        pref=str(input("Pref?"))
        predict("wordlist.txt", pref)
main()
    
