def countdigits(a):
    counter=0
    for char in a:
        if char.isdigit():
            counter+=1
    return(counter)

def main():
    a= input("string")
    print(countdigits(a))


main()