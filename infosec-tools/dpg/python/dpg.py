#!/usr/bin/env python2

""" Python 2.7 script that implements DPG """

# Standard Imports
import binascii
import getpass
import hashlib
import Queue
import os
import subprocess


# Local Imports
import jars

def fill_q():
    """ Fill the queue """
    que = Queue.Queue()
    for char in PBKDF2:
        que.put(ord(char))
        #print ord(char)
    #print "q Size: ", q.qsize()
    return que

def get_big():
    """ Get a big password """
    que = fill_q()
    result = ""
    while len(result) < 24-3:
        result += jars.lower().get(que.get())

    #Add special, upper and number
    result += jars.special().get(que.get())
    result += jars.upper().get(que.get())
    result += jars.numbers().get(que.get())
    return result

def get_small():
    """ Get a small password """
    que = fill_q()
    result = ""
    while len(result) < 12-3:
        result += jars.lower().get(que.get())

    #Add special, upper and number
    result += jars.special().get(que.get())
    result += jars.upper().get(que.get())
    result += jars.numbers().get(que.get())
    return result

def get_passphrase():
    """ Get a seven word passphrase """
    que = fill_q()
    result = ""
    i = 0
    while i < 7:
        result += jars.words().get(que.get())
        i = i + 1

    #Add special, upper and number
    result += jars.special().get(que.get())
    result += jars.upper().get(que.get())
    result += jars.numbers().get(que.get())
    return result

def readable( password ):
    x = 0
    human = ""
    while x < len(password):
        human += password[x:x+3] + " "
        x += 3
    print human.strip()

# Main

DEBUG = False

SENTENCE = getpass.getpass(b"Enter the sentence: ")
SENTENCE2 = getpass.getpass(b"Confirm the sentence: ")
if SENTENCE != SENTENCE2:
    exit("The sentences are not the same")
WORD = raw_input(b"Enter the word: ")

PBKDF2 = hashlib.pbkdf2_hmac('sha1', SENTENCE, WORD, 32768, dklen=64)
PBKDF2_HEX = binascii.hexlify(PBKDF2)

if DEBUG:
    print "Size of PBKDF2: ", len(PBKDF2)
    print "Size of PBKDF2_HEX: ", len(PBKDF2_HEX)
    print "PBKDF2: ", PBKDF2_HEX

    print "Numbers size: ", len(jars.numbers())
    print "Special size: ", len(jars.special())
    print "Lower size: ", len(jars.lower())
    print "Upper size: ", len(jars.upper())
    print "Words size: ", len(jars.words())

    print jars.numbers().values()
    print jars.special().values()
    print jars.lower().values()
    print jars.upper().values()
    print jars.words().values()

read, write = os.pipe()
os.write(write, get_big())
os.close(write)

subprocess.check_call(['xclip',  '-selection',  'clipboard'], stdin=read)
    
#pyperclip.copy(get_big())
#print pyperclip.paste()
#print get_small()
#print get_passphrase()

#print "---------------------------"
#print "---- Human Readable -------"
#print "---------------------------"
#readable( get_big() )
#readable( get_small() )
#readable( get_passphrase() )
#print "------END DPG MESSAGE------\n"

