def shorten(str):
    finalstr=""
    for char in str:
        if char.isupper():
            finalstr+=char
    return finalstr
            

def main():
    string=str(input("String?"))
    print(shorten(string))

main()