"""
Day 2 Challenge: I Was Told There Would Be No Math
https://adventofcode.com/2015/day/2
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
    
    totalPaper = 0 #total sq feet of paper

    #add paper totals
    for line in input:

        length, width, height = [int(dim) for dim in line.split("x")] #get dimensions
        sides = [length * width, length * height, width * height] #side areas

        totalPaper += 2 * sides[0] + 2 * sides[1] + 2 * sides[2] + min(sides) #calculate total paper for thsi box

    print(totalPaper)

def challengePart2(input):
    pass


##############
#    Main    #
##############

if __name__ == '__main__':

    input = getChallengeInput(2015, 2)

    print()
    print("========== PART 1 ==========")
    print()

    challengePart1(input)

    print()
    print("========== PART 2 ==========")
    print()

    challengePart2(input)