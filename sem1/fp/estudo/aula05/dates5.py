
def parseMDY(date):
    """Return (year, month, day) from date in MM/DD/YYYY format."""
    datelst=date.split("/")
    if len(datelst)==1:
        return (int(datelst[0]), 0, 0)
    else:
        return (int(datelst[2]), int(datelst[0]), int(datelst[1]))


def yearsBetween(date1, date2):
    """Return integer number of years between two (y, m, d) dates."""
    yearsfinal=abs(date1[0]-date2[0])
    if date2[0]>date1[0]:
        if date2[1]>=date1[1] and date2[2]>=date1[2]:
            return yearsfinal
        else:
            return (yearsfinal-1)
    elif date1[0]<date2[0]:
        if date2[1]<=date1[1] and date2[2]<=date1[2]:
            return yearsfinal
        else:
            return (yearsfinal-1)
    else:
        return 0


def main():
    # Test parseMDY
    print(f"{parseMDY('12/25/2024') = }")  # (2024, 12, 25)
    print(f"{parseMDY('4/25/1974') = }")   # (1974, 4, 25)
    print(f"{parseMDY('1755') = }")        # (1755, 0, 0)

    # Test yearsBetween
    print(f"{yearsBetween((1900, 6, 1), (1935, 5, 31)) = }")  # 34
    print(f"{yearsBetween((1900, 6, 1), (1935, 6, 1)) = }")   # 35
    print(f"{yearsBetween((1900, 6, 1), (1936, 5, 31)) = }")  # 35


# This program may be used as a module too
if __name__ == "__main__":
    main()

