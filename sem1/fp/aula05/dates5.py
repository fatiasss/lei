
def parseMDY(date):
    """Return (year, month, day) from date in MM/DD/YYYY format."""
    a = date.split("/")
    if len(a)>1:
        final = (a[2], a[0], a[1])
    else:
        final = (a[0], 0, 0)
    return final


def yearsBetween(date1, date2):
    """Return integer number of years between two (y, m, d) dates."""
    return abs(date1[0]-date2[0])


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

