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

        totalPaper += 2 * sides[0] + 2 * sides[1] + 2 * sides[2] + min(sides) #calculate total paper for this box

    print(totalPaper)

def challengePart2(input):
    
    totalRibbon = 0 #total feet of ribbon

    #add ribbon totals
    for line in input:

        dimensions = [int(dim) for dim in line.split("x")] #get dimensions
        dimensions.sort() #sort dimensions

        #calculate ribbon
        totalRibbon += 2 * dimensions[0] + 2 * dimensions[1] + (dimensions[0] * dimensions[1] * dimensions[2])

    print(totalRibbon)


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