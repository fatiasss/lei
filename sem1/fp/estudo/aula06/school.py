# Complete o programa!

# a)
def loadFile(fname, lst):
    while True:
        with open(fname, "r") as fileobj:
            line= fileobj.readline().split("/t")
            if line!="":
                    lst.append(tuple(line[0], line[1], line[5], line[6], line[7]))
            else: break

    
# b) Crie a função notaFinal aqui...
def notaFinal(t):
     return (t[2]+t[3]+t[4])/3

# c) Crie a função printPauta aqui...
def printPauta(lst):
    print("Número    Nome      Nota")
    for val in lst:
          val.append(notaFinal(val))
          print("{}     {}      {}".format(val[0], val[1], val[-1]))

# d)
def main():
    lst = []
    # ler os ficheiros
    loadFile("school1.csv", lst)
    loadFile("school2.csv", lst)
    loadFile("school3.csv", lst)
    
    # ordenar a lista
    lst.sort(lst[0])
    
    # mostrar a pauta
    with open("schooltable.txt", "w") as fileobj:
         fileobj.write("Número    Nome      Nota")
         for val in lst:
            val.append(notaFinal(val))
            fileobj.write("{}     {}      {}".format(val[0], val[1], val[-1]))
              


# Call main function
if __name__ == "__main__":
    main()


