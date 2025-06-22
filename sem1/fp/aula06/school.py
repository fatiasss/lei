# Complete o programa!

# a)
def loadFile(fname, lst):
    with open(fname, "r") as fileobj:
        fileobj.readline()
        while True:
            line = fileobj.readline()
            if line=="":break
            data = line.strip("\n").split("\t")
            lst.append((int(data[0]), data[1], float(data[5]), float(data[6]), float(data[7])))

    
# b) Crie a função notaFinal aqui...
def notaFinal(reg):
    return (reg[2]+reg[3]+reg[4])/3

# c) Crie a função printPauta aqui...
def printPauta(lst):
    print(f"{'Numero' : <30}{'Nome' : ^15}{'Nota Final' : >30}")
    for val in lst:
        print(f"{val[0] : <30}{val[1] : ^}{notaFinal(val) : >30.2f}")
        


# d)
def main():
    lst = []
    # ler os ficheiros
    loadFile("school1.csv", lst)
    loadFile("school2.csv", lst)
    loadFile("school3.csv", lst)
    
    # ordenar a lista
    
    # mostrar a pauta
    printPauta(lst)


# Call main function
if __name__ == "__main__":
    main()


