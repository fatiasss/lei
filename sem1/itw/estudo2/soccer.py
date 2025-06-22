def fileFunc(filename):
    with open(filename, 'r') as fileobj:
        while True:
            line= fileobj.readline()
            if line=='':break
            linelst=line.split(',')
            linetup=(line[x] for x in range(line))
            print(linetup)



def main():
    fileFunc('Soccer_Football_Clubs_Ranking.csv')

main()