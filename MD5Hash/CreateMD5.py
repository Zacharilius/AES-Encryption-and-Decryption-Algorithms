"""
CreateMD5.py
"""
import hashlib
import os

class MD5Hash:
    def hashInput(self, input):
        return hashlib.md5(input).hexdigest()

    def createSalt(self, num):
        return os.urandom(num)



    
MD5 = MD5Hash()
print MD5.hashInput("tada")
print MD5.hashInput("Tada")
print MD5.hashInput("Poop")
print MD5.hashInput("poop")


#Create rainbow tables
    #Add each word from wordList
    #Then recursively add with Caps, numbers, etc.
#Find most common password names
#Test those first
#Start comparing each entry in dictionary  to hash
#If successful stop, otherwise keep going

#Lookup Tables - Search table for hash

#Rainbow Tables can crack any md5 hash up to 8 characters

#Add salt to prevent lookup table or rainbow table.
