s = """v in giccm by lyvj uvbg myd byqim vj ugib uvhh ty qyuj vj gvwbypm iw bgz
tpzibzwb qznyjwbpibvyj kyp kpzzqyn vj bgz gvwbypm yk ydp jibvyj kvrz weypz mzipw ity 
i tpzib lnzpveij vj ugywz wmnoyhve wgiqyu uz wbijq byqim wvtijzq bgz znijevcibvyj
cpyehinibvyj. bgvw nynzjbydw qzepzz einz iw i tpzib ozieyj hvtgb yk  gycz by 
nvhhvyjw yk jztpy whirzw ugy giq ozzj wzipzq vj bgz khinzw yk uvbgzpvjt vjldwbvez. vb 
einz iw i lymydw qimopzix by zjq bgz hyjt jvtgb yk bgzvp eicbvrvbm."""


def switchLetters(ciphertext, plaintext):
    global s
    l = list(s)

    x = 0
    while x < len(l):
        if l[x] is ciphertext:
            l[x] = plaintext
        x += 1
    s = "".join(l) 
    return s

#Counts the number of letters in the specified string
def prepCountLetters():
    #global s
    s = """v in giccm by lyvj uvbg myd byqim vj ugib uvhh ty qyuj vj gvwbypm iw bgz
tpzibzwb qznyjwbpibvyj kyp kpzzqyn vj bgz gvwbypm yk ydp jibvyj kvrz weypz mzipw ity 
i tpzib lnzpveij vj ugywz wmnoyhve wgiqyu uz wbijq byqim wvtijzq bgz znijevcibvyj
cpyehinibvyj. bgvw nynzjbydw qzepzz einz iw i tpzib ozieyj hvtgb yk  gycz by 
nvhhvyjw yk jztpy whirzw ugy giq ozzj wzipzq vj bgz khinzw yk uvbgzpvjt vjldwbvez. vb 
einz iw i lymydw qimopzix by zjq bgz hyjt jvtgb yk bgzvp eicbvrvbm."""
    l = list(s)

    #Creates empty list with 26 blank 0s
    numLetters = []
    i = 0
    while i < 26:
        numLetters.append(0)
        i += 1
    
    #Counts number of each element
    x = 0
    while x < len(l):
        #at position x in list l, convert to charNumber
        charNum = ord(l[x].upper())
        if charNum >= 65 and charNum <= 90:
            numLetters[charNum-65] = numLetters[charNum-65] + 1
        x += 1
    return numLetters


#Accepts array from countLetters() and returns a formatted string displaying letter count
def analyzeCountLetters(numLetters):
    i = 0
    j = 0
    returnString = ""
    totalLetters = 0.0

    returnString += "The individual count of each letter\n"
    while i < len(numLetters):
        returnString += chr(i + 65) + " " + str(numLetters[i]) + "\t"
        totalLetters += numLetters[i]
        if (i + 1) % 5 is 0:
            returnString += "\n"
        i += 1

    largestPct = 0;
    secLargestPct = 0;
    thirdLargestPct = 0;
    
    
    returnString += "\n\nThe percentage of each letter's occurance\n"
    while j < len(numLetters):
        letterPrct = round(100 *(numLetters[j]/totalLetters),1)
        if largestPct < letterPrct:
            thirdLargestPct = secLargestPct
            secLargestPct = largestPct
            largestPct = letterPrct
        returnString += chr(j + 65) + " " + str(letterPrct) + "%\t"
        if (j + 1) % 5 is 0:
            returnString += "\n"
        j += 1
        
    returnString += "\nThe highest 3 occurring numbers\n"
    returnString += "Highest percent: " + str(largestPct) + "\n"
    returnString += "Second highest percent: " + str(secLargestPct) + "\n"
    returnString += "third highest percent: " + str(thirdLargestPct) + "\n"

    
    return returnString


def main():
    numLetters = prepCountLetters()
    print analyzeCountLetters(numLetters)    
    
    
main()
