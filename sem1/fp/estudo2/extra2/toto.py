
def prize(wins):
    if wins==9:
        return("ALL BETS CORRECT: 1ST PRIZE")
    elif wins==8:
        return("8 bets correct: 2nd Prize")
    elif wins==7:
        return("7 bets correct: 3rd Prize")
    else:
        return("{} bets correct: No Prize".format(wins))



def bet():
    rightcounter=0
    allgames=[]
    with open("Jornadas.csv", "r") as fileobj:
        while True:
            line=fileobj.readline()
            if line=="":break
            line=line.strip("\n")
            line=line.split(",")
            allgames.append(line)
    jornada = -1
    while jornada not in [game[0] for game in allgames]:
        jornada = str(input("Jornada?"))
    cgames = [game for game in allgames if game[0]==jornada]
    with open("jornada" + str(jornada) + ".txt", "w") as savefile:  
        for game in cgames:
            bet = -1
            while bet!="1" and bet!="2" and bet!="X":
                bet=str(input("{} {} vs {}: ".format(cgames.index(game)+1, game[1], game[2])))
            savefile.write("{},{}\n".format(cgames.index(game), bet))
    with open("Jogos.csv", "r") as fileobj:
        gameresults= []
        counter=1
        counter2=0
        while True:
            line=fileobj.readline()
            if line=="":break
            counter+=1
            if counter in range(9*int(jornada), 9*(int(jornada)+1)):
                counter2+=1
                line=line.strip("\n")
                line=line.split(",")
                line.append(counter2)
                gameresults.append(line)
    for game in gameresults:
        if game[3]>game[4]:
            game.append("1")
        elif game[3]<game[4]:
            game.append("2")
        else:
            game.append("X")
    with open("jornada" + str(jornada) + ".txt", "r") as savefile:
        while True:
            line=savefile.readline()
            if line=="":break
            line=line.strip("\n")
            line=line.split(",")
            if gameresults[int(line[0])][-1]==str(line[1]):
                gameresults[int(line[0])].append("Certo")
                rightcounter+=1
            else:
                gameresults[int(line[0])].append("Errado")
    print(gameresults)
    for game in gameresults:
        print("{:<10} {:<5} {:<2}-{:>2} {:>10} : {:>10} {}".format(game[-3],game[1],game[3],game[4],game[2],game[-2],game[-1]))
    print(prize(rightcounter))
    

            

bet()