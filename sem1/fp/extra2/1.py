def TextPrint():
    print("1) Registar chamada")
    print("2) Ler Ficheiro")
    print("3) Listar Clientes")
    print("4) Fatura")
    print("5) Terminar")
    option = input("Opção?")
    return option

def numberValidate(number):
    retval= True
    numericounter=0
    for char in number:
            if  (not char.isnumeric() and not char=="+"):
                retval= False
            elif char=="+" and char is not number[0]:
                retval= False
            elif char!="+":
                numericounter+=1
    if numericounter<3:
            retval=False
    return retval

def registerCall():
    retval = []
    origin = asknumber("Telefone de origem")
    retval.append(origin)
    destiny = asknumber("Telefone destino")
    retval.append(destiny)
    duration = input("Duração (s)")
    retval.append(duration)

    return retval
    
def asknumber(txt):
    number=input(txt)
    if not numberValidate(number):
        asknumber(txt)
    else:
        return number
def readCalls(filename):
    retlst=[]
    with open(filename, "r") as fileobj:
        while True:
            line = fileobj.readline()
            if line=="": break
            call = line.strip("")
            call = call.strip("\n")
            call = call.split("\t")
            retlst.append(call)
    return retlst

def readClients(filename):
    callst=readCalls(filename)
    callst.sort()
    clientlist=[]
    for i in range(len(callst)):
        if callst[i][0] not in clientlist:
            clientlist.append(callst[i][0])
    return clientlist
        
def bill(number):
    filename=input("Filename?")
    allcalls=readCalls(filename)
    clientcalls=[]
    total=0
    print("Destino", "\t" "Duração", "\t","Custo")
    for call in allcalls:
        if call[0]==number:
            if call[1][0]==2:
                mult=0.02/60
            elif call[1][0]=="+":
                mult=0.8/60
            elif call[1][0]==call[0][0] and call[1][1]==call[0][1]:
                mult=0.04/60
            else:
                mult=0.1/60
            print(call[1],"\t", call[2], "\t", int(call[2])*mult)
            total+=int(call[2])*mult
    print("Total=", total)
    
    


def main():
    option=TextPrint()
    if option=="1":
        registerCall()
        main()
    elif option=="2":
        filename= input("Filename?")
        print(readCalls(filename))
    elif option=="3":
        filename= input("Filename?")
        print(readClients(filename))
    elif option=="4":
        clientnumber=input("Client Number?")
        print(bill(clientnumber))


main()
