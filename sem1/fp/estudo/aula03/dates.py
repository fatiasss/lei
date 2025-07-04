
# This function checks if year is a leap year.
# It is wrong: 1900 was a common year!
# (See: https://en.wikipedia.org/wiki/Leap_year)
def isLeapYear(year):
    if year%100==0:
        if year%400==0:
            return True
        else:
            return False
    elif year%4==0:
        return True
    else:
        return False


# This function has a semantic error: February in a leap year should return 29!
# Correct it.
def monthDays(year, month):
    MONTHDAYS = (0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    days = MONTHDAYS[month]
    if isLeapYear(year) and month==2:
        days=29
    return days


# This is wrong, too.
def nextDay(year, month, day):
    MONTHDAYS = (0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    MONTHDAYSLEAP = (0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    if  not isLeapYear(year):
        if (day+1)<=MONTHDAYS[month]:
            day+=1
        else:
            if (month+1)<=12:
                month+=1
                day=1
            
            else:
                year+=1
                month=1
                day=1
    else:
            if (day+1)<=MONTHDAYS[month]:
                day+=1
            else:
                if (month+1)<=12:
                    month+=1
                    day=1
            
                else:
                    year+=1
                    month=1
                    day=1
    return year, month, day
    


# This is the main function
def main():
    print("Was", 2017, "a leap year?", isLeapYear(2017))    # False?
    print("Was", 2016, "a leap year?", isLeapYear(2016))    # True?
    print("Was", 2000, "a leap year?", isLeapYear(2000))    # True?
    print("Was", 1900, "a leap year?", isLeapYear(1900))    # False?
    
    print("January 2017 had", monthDays(2017, 1), "days")   # 31?
    print("February 2017 had", monthDays(2017, 2), "days")  # 28?
    print("February 2016 had", monthDays(2016, 2), "days")  # 29?
    print("February 2000 had", monthDays(2000, 2), "days")  # 29?
    print("February 1900 had", monthDays(1900, 2), "days")  # 28?
    
    y, m, d = nextDay(2017, 1, 30)
    print(y, m, d)    # 2017 1 31 ?
    y, m, d = nextDay(2017, 1, 31)
    print(y, m, d)    # 2017 2 1 ?
    y, m, d = nextDay(2017, 2, 28)
    print(y, m, d)    # 2017 3 1 ?
    y, m, d = nextDay(2016, 2, 29)
    print(y, m, d)    # 2016 3 1 ?
    y, m, d = nextDay(2017, 12, 31)
    print(y, m, d)    # 2018 1 1 ?


# call the main function
if __name__ == "__main__":
    main()

