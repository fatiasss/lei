import os

def printDirFiles(d):
    lst = os.listdir(d)
    for fname in lst:
        path = os.path.join(d, fname)
        if os.path.isfile(path):
            ftype = "FILE"
        elif os.path.isdir(path):
            ftype = "DIR"
        else:
            ftype = "?"
        print(ftype, path)
    return


def findFiles(path, ext):
    retlst=[]
    dlst = os.listdir(path)
    for fname in dlst:
        newpath = os.path.join(path, fname)
        if os.path.isfile(newpath) and ext in newpath:
            retlst.append(newpath)
        elif os.path.isdir(newpath):
            [retlst.append(y) for y in findFiles(newpath, ext)]
        else:
            ftype = "?"
    return retlst
    




def main():
    print("Testing printDirFiles('..'):")
    printDirFiles("..")

    print("\nTesting findFiles('.', '.py'):")
    lst = findFiles(".", ".py")
    print(lst)
    assert isinstance(lst, list)

    print("\nTesting findFiles('..', '.csv'):")
    lst = findFiles("..", ".csv")
    print(lst)

if __name__ == "__main__":
    main()

