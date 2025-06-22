def countdown(n):
    if(n)>0:
        print(n)
        countdown(n-1)
    else:
        print("")
    return

def main():
    n1 = int(input("Number?"))
    countdown(n1)


main()