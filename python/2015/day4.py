"""
Day 4 Challenge: The Ideal Stocking Stuffer
https://adventofcode.com/2015/day/4
"""

from hashlib import md5
from util.inputUtil import getChallengeInput

###################
#    Variables    #
###################


#################
#    Methods    #
#################


###########################
#    Challenge Methods    #
###########################

def challengePart1(input):
    
    key = input[0] #hash key
    keyNum = -1 #number appended to key
    hashResult = "" #result of hash

    #find matching hash
    while(hashResult[:5] != "00000"):

        keyNum += 1
        hashResult = md5(bytes(key + str(keyNum), encoding="utf-8")).hexdigest() #get hash for current number

    print(keyNum)

def challengePart2(input):
    
    key = input[0] #hash key
    keyNum = -1 #number appended to key
    hashResult = "" #result of hash

    #find matching hash
    while(hashResult[:6] != "000000"):

        keyNum += 1
        hashResult = md5(bytes(key + str(keyNum), encoding="utf-8")).hexdigest() #get hash for current number

    print(keyNum)


##############
#    Main    #
##############

if __name__ == '__main__':

    input = getChallengeInput(2015, 4)

    print()
    print("========== PART 1 ==========")
    print()

    challengePart1(input)

    print()
    print("========== PART 2 ==========")
    print()

    challengePart2(input)