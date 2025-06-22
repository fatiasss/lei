def validphone(number):
    retval=True
    if not number[0].isnumeric() and number[0]!="+":
        retval=False
    else:
        algcount=0
        for char in number:
            if char!="+": algcount+=1
        if algcount<3 or algcount>9:
            retval=False
    return retval
def registercall():
    origin="-1"
    while not validphone(origin):
        origin=str(input("Origin phone number?"))
    dest="-1"
    while not validphone(dest):
        dest=str(input("Destination phone number?"))
    dur=str(input("Duration of the call?(s)"))

def readfile(filename):
    callst=[]
    with open(filename, "r") as fileobj:
        while True:
            line=fileobj.readline()
            if line=="":break
            line=line.strip("\n")
            line=line.split("\t")
            print(line)
            callst.append(line)
    return callst
def readfilenoprint(filename):
    callst=[]
    with open(filename, "r") as fileobj:
        while True:
            line=fileobj.readline()
            if line=="":break
            line=line.strip("\n")
            line=line.split("\t")
            callst.append(line)
    return callst
def listclients():
    clientlst=[]
    with open("chamadas1.txt", "r") as fileobj:
        while True:
            line=fileobj.readline()
            if line=="":break
            linelst=line.split("\t")
            if linelst[0] not in clientlst:
                clientlst.append(linelst[0])

    with open("chamadas2.txt", "r") as fileobj:
        while True:
            line=fileobj.readline()
            if line=="":break
            line.split("\t")
            if linelst[0] not in clientlst:
                clientlst.append(linelst[0])
                
    for client in clientlst:
        print(client)
            
def cost(number):
    call1= readfilenoprint("chamadas1.txt")
    call2= readfilenoprint("chamadas2.txt")
    calltotal= call1+call2
    clientcalls=[]
    for call in calltotal:
        if call[0]==number:
            callobj= call
            callobj.append(callcost(call))
            clientcalls.append(callobj)
    print("Destino \t Duração \t Custo")
    for call in clientcalls:
        print("{} \t {} \t {:.2f}".format(call[1], call[2], call[-1]))



def callcost(call):
    if call[0][0]=="2":
        return int(call[2])*(0.02/60)
    elif call[0][0]=="+":
        return int(call[2])*(0.8/60)
    elif call[0][0]==call[1][0]:
        return int(call[2])*(0.04/60)
    else:
        return int(call[2])*(0.1/60)

def main():
    print("1) Registar chamada")
    print("2) Ler ficheiro")
    print("3) Listar clientes")
    print("4) Fatura")
    print("5) Terminar")
    choice = str(input("Opção?"))
    if choice=="1":
        registercall()
        main()
    elif choice=="2":
        filename=str(input("File name?"))
        readfile(filename)
        main()
    elif choice=="3":
        listclients()
        main()
    elif choice=="4":
        client=str(input("Client number?"))
        print(cost(client))
        main()
    elif choice=="5":
        quit()
    else:
        print("Invalid choice")
        main()


main()

""" print(validphone("9"), "False")
print(validphone("999"), "True")
print(validphone("+999"), "True")
print(validphone("-999"),  "False")
print(validphone("9999999999"),  "False")
print(validphone("99999999a"),  "False") """