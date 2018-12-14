import the_words

def numbers():
    """ The jar of numbers, returns dict """
    mp = {}
    while len(mp) < 256:
        mp[len(mp)] = "2"
        mp[len(mp)] = "3"
        mp[len(mp)] = "4"
        mp[len(mp)] = "5"
        mp[len(mp)] = "6"
        mp[len(mp)] = "7"
        mp[len(mp)] = "8"
        mp[len(mp)] = "9"
    return mp

def special():
    """ The jar of special chars, returns dict """
    mp = {}
    while len(mp) < 256:
        mp[len(mp)] = "."
        mp[len(mp)] = ","
        mp[len(mp)] = "!"
        mp[len(mp)] = "*"
        mp[len(mp)] = "#"
        mp[len(mp)] = "&"
        mp[len(mp)] = "^"
        mp[len(mp)] = "~"
    return mp

def lower():
    """ The jar of lower chars, returns dict """
    mp = {}
    while len(mp) < 256:
        mp[len(mp)] = "a"
        mp[len(mp)] = "b"
        mp[len(mp)] = "c"
        mp[len(mp)] = "d"
        mp[len(mp)] = "e"
        mp[len(mp)] = "f"
        mp[len(mp)] = "g"
        mp[len(mp)] = "h"
        mp[len(mp)] = "j"
        mp[len(mp)] = "k"
        mp[len(mp)] = "m"
        mp[len(mp)] = "n"
        mp[len(mp)] = "p"
        mp[len(mp)] = "r"
        mp[len(mp)] = "s"
        mp[len(mp)] = "t"
    return mp

def upper():
    """ The jar of upper chars, returns dict """
    mp = {}
    while len(mp) < 256:
        mp[len(mp)] = "A"
        mp[len(mp)] = "B"
        mp[len(mp)] = "C"
        mp[len(mp)] = "D"
        mp[len(mp)] = "E"
        mp[len(mp)] = "F"
        mp[len(mp)] = "G"
        mp[len(mp)] = "H"
        mp[len(mp)] = "J"
        mp[len(mp)] = "K"
        mp[len(mp)] = "M"
        mp[len(mp)] = "N"
        mp[len(mp)] = "P"
        mp[len(mp)] = "R"
        mp[len(mp)] = "S"
        mp[len(mp)] = "T"
    return mp

def words():
    """ The jar of words, returns dict """
    mp = {}

    for word in the_words.words:
        if(len(word.strip()) == 3):
            mp[len(mp)] = word.strip()

    return mp

