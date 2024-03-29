"""
Day 1 Challenge: Not Quite Lisp
https://adventofcode.com/2015/day/1
"""

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
    
    floor = 0 #current floor

    for char in input[0]:
        floor += 1 if char == '(' else -1

    print(floor)

def challengePart2(input):
    
    floor = 0 #current floor

    for x in range(len(input[0])):

        floor += 1 if input[0][x] == '(' else -1

        #check for basement
        if floor == -1:
            print(x+1)
            break


##############
#    Main    #
##############

if __name__ == '__main__':

    input = getChallengeInput(2015, 1)

    print()
    print("========== PART 1 ==========")
    print()

    challengePart1(input)

    print()
    print("========== PART 2 ==========")
    print()

    challengePart2(input)