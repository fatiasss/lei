def ispalindrome(word):
    lst=[]
    for char in word:
        lst.append(char)
    lstsave=lst[:]
    lst.reverse()
    if lstsave==lst:
        return True
    else:
        return False

def main():
    word=input("str")
    print(ispalindrome(word))

main()