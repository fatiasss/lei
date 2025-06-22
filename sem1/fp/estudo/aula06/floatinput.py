import math

def floatInput(prompt, mini, maxi):
    try:
        promptint = float(input(prompt))
        mini = float(input(mini))
        maxi = float(input(maxi))
    except ValueError:
        floatInput(input("Value error, use a numeric value"))
    else:
        if promptint<mini or promptint>maxi:
            print("Value isn't in the interval")   
            return floatInput("Value? ", "Minimum?", "Maximum")
             
        else:
            return prompt



def main():
    print("a) Try entering invalid values such as 1/2 or 3,1416.")
    v = floatInput("Value? ", "Minimum?", "Maximum")
    print("v:", v)

    print("b) Try entering invalid values such as 15%, 110 or -1.")
    h = floatInput("Humidity (%)? ", 0, 100)
    print("h:", h)

    print("c) Try entering invalid values such as 23C or -274.")
    t = floatInput("Temperature (Celsius)? ", min=-273.15)
    print("t:", t)

    # d) What happens if you uncomment this?
    # impossible = floatInput("Value in [3, 0]? ", min=3, max=0)

    return

if __name__ == "__main__":
    main()
