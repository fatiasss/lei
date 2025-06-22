from curses.ascii import isupper


def abv(a):
    abrev=""
    for char in a:
        if char.isupper():
            abrev+=char
    return(abrev)

def main():
    a=input(("str"))
    print(abv(a))

main()