# jmr 2024 o programa

import sys

# Add auxiliary functions here.
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


def load_lifetimes_file(file_name):
    """Load birth, death, name data from a file."""
    lst = []
    with open(file_name, 'r') as file:
        for line in file:
            # Strip spaces and newline from line
            line = line.strip()
            # Ignore empty lines and comments
            if line == "" or line[:1] == "#":
                continue  # skip to next line
            # Change this to split the line, parse dates
            # and store (date1, date2, name) tuple.
            line=line.split("\t")
            line[0]=parseMDY(line[0])
            line[1]=parseMDY(line[1])
            if line[0][0]<1800:
                lst.append(line)
    return lst


def main():
    file_name = 'composers.txt'  # Replace with your file name
    lifes = load_lifetimes_file(file_name)

    print("THE DEAD COMPOSERS SOCIETY")
    print("==========================")

    for info in lifes:
        # Change this to show Name, Age and Date-of-death in aligned columns.
        ...
        print(info)


if __name__ == "__main__":
    main()

