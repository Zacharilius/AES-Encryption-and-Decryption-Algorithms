s = """Qwrn pn twr Ahkvgr hd wkjfn rzrntg, pt mrahjrg nrarggfvo dhv hnr xrhxyr
th upgghyzr twr xhyptpafy mfnug qwpaw wfzr ahnnratru twrj qptw fnhtwrv, fnu
th fggkjr fjhnb twr xhqrvg hd twr rfvtw, twr grxfvftr fnu rlkfy gtftphn th
qwpaw twr Yfqg hd Nftkvr fnu hd Nftkvr'g Bhu rntptyr twrj, f urarnt vrgxrat
th twr hxpnphng hd jfncpnu vrlkpvrg twft twro gwhkyu urayfvr twr afkgrg qwpaw
pjxry twrj th twr grxfvftphn.
   
Qr whyu twrgr tvktwg th mr gryd-rzpurnt, twft fyy jrn fvr avrftru rlkfy, twft
twro fvr rnuhqru mo twrpv Avrfthv qptw arvtfpn knfyprnfmyr Vpbwtg, twft fjhnb
twrgr fvr Ypdr, Ypmrvto fnu twr xkvgkpt hd Wfxxpnrgg.--Twft th grakvr twrgr
vpbwtg, Bhzrvnjrntg fvr pngtptktru fjhnb Jrn, urvpzpnb twrpv skgt xhqrvg dvhj
twr ahngrnt hd twr bhzrvnru, --Twft qwrnrzrv fno Dhvj hd Bhzrvnjrnt mrahjrg
urgtvkatpzr hd twrgr rnug, pt pg twr Vpbwt hd twr Xrhxyr th fytrv hv th
fmhypgw pt, fnu th pngtptktr nrq Bhzrvnjrnt, yfopnb ptg dhknuftphn hn gkaw
xvpnapxyrg fnu hvbfnpipnb ptg xhqrvg pn gkaw dhvj, fg th twrj gwfyy grrj jhgt
ypcryo th rddrat twrpv Gfdrto fnu Wfxxpnrgg. Xvkurnar, pnurru, qpyy upatftr
twft Bhzrvnjrntg yhnb rgtfmypgwru gwhkyu nht mr awfnbru dhv ypbwt fnu
tvfngprnt afkgrg; fnu faahvupnbyo fyy rexrvprnar wftw gwrqn, twft jfncpnu fvr
jhvr upgxhgru th gkddrv, qwpyr rzpyg fvr gkddrvfmyr, twfn th vpbwt twrjgryzrg
mo fmhypgwpnb twr dhvjg th qwpaw twro fvr faakgthjru. Mkt qwrn f yhnb tvfpn hd
fmkgrg fnu kgkvxftphng, xkvgkpnb pnzfvpfmyo twr gfjr Hmsrat rzpnarg f urgpbn
th vrukar twrj knurv fmghyktr Urgxhtpgj, pt pg twrpv vpbwt, pt pg twrpv ukto,
th twvhq hdd gkaw Bhzrvnjrnt, fnu th xvhzpur nrq Bkfvug dhv twrpv dktkvr
grakvpto.--Gkaw wfg mrrn twr xftprnt gkddrvfnar hd twrgr Ahyhnprg; fnu gkaw
pg nhq twr nrarggpto qwpaw ahngtvfpng twrj th fytrv twrpv dhvjrv Gogtrjg hd
Bhzrvnjrnt. Twr wpgthvo hd twr xvrgrnt Cpnb hd Bvrft Mvptfpn pg f wpgthvo hd
vrxrftru pnskvprg fnu kgkvxftphng, fyy wfzpnb pn upvrat hmsrat twr
rgtfmypgwjrnt hd fn fmghyktr Tovfnno hzrv twrgr Gtftrg. Th xvhzr twpg, yrt
Dfatg mr gkmjpttru th f afnupu qhvyu."""


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
    global s

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

    switchLetters('t','t')
    switchLetters('q','w')
    switchLetters('u','d')
    switchLetters('k','u')
    switchLetters('a','f')
    switchLetters('z','v')
    switchLetters('p','i')

    switchLetters('h','o')
    switchLetters('r','e')
    switchLetters('n','m')

    switchLetters('j','n')
    switchLetters('d','f')
    switchLetters('g','s')
    switchLetters('y','l')
    switchLetters('v','r')
    switchLetters('f','a')


    print"\n" + switchLetters('w','h')

    
    
main()
