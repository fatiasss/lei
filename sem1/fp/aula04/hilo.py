# Complete the code to make the HiLo game.

import random

def playHiLo():
    # Pick a random number between 1 and 100, inclusive
    secret = random.randrange(1, 101);
    g = int(input("I picked a number between 1 and 100. Can you guess it?"))
    counter = 1
    while g!= secret:
        if g>secret:
            print("Too High!")
            counter += 1
        elif g<secret:
            print("Too Low!")
            counter += 1
        g = int(input("Guess again!"))
    print("You got it right! And it only took you {:2d} tries!".format(counter))


playHiLo()

