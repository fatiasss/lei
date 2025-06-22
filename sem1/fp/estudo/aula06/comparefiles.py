def compareFiles(file1, file2):
    with open(file1, "rb") as fileobj1:
        with open(file2, "rb") as fileobj2:
            while True:
                linef1=fileobj1.read(1024)
                linef2=fileobj2.read(1024)
                if linef1==b"" or linef2==b"":
                    break
                elif linef1!=linef2:
                    return False
                    
                    
            return True