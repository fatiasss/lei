# Complete the code to make the HiLo game.

import random

def playHiLo():
    # Pick a random number between 1 and 100, inclusive
    secret = random.randrange(1, 101);
    i=0
    counter=0
    print("I picked a number between 1 and 100. Can you guess it?")
    while i!=secret:
        i= int(input("Guess"))
        if i> secret:
            print("Too high")
        else:
            print("Too low")
        counter+=1
    print("Thats right! It only took you {} guesses".format(counter))

    


playHiLo()

