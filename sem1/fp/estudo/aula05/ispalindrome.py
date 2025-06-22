def ispalindrome(strg):
    normal = []
    for char in strg:
        normal.append(char)
    normalsave=normal.copy()
    normal.reverse()
    if normal == normalsave:
        return "Palindrome"
    else:
        return "Not a Palindrome"
    
    
def main():
    string=str(input("String?"))
    print(ispalindrome(string))

main()