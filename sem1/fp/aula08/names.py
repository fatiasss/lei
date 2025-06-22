def makedict(filename):
    fullnames=[]
    with open(filename, "r") as fileobj:
        while True:
            line = fileobj.readline()
            if line== "": break
            line = line.split(" ")
            fullnames.append(line)
    keylist= [name[-1] for name in fullnames]
    names={}
    for key in keylist:
        names[key] = [name[:(len(name)-1)] for name in fullnames if name[-1]==key]
    return names
def main():
    print(makedict("names.txt"))

main()