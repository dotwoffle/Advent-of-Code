"""
Day 1 Challenge: Perfectly Spherical Houses in a Vacuum
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
    
    pos = [0, 0] #current position
    visited = {(pos[0], pos[1])} #positions already visited

    #do moves
    for move in input[0]:

        #update position
        if move == '^':
            pos[1] += 1
        elif move == 'v':
            pos[1] -= 1
        elif move == '>':
            pos[0] += 1
        elif move == '<':
            pos[0] -= 1

        visited.add((pos[0], pos[1])) #mark current position as visited

    print(len(visited))

def challengePart2(input):
    
    santaPos = [0, 0] #santa's current position
    roboPos = [0, 0] #robo santa's current position
    visited = {(santaPos[0], santaPos[1])} #positions already visited

    #do moves
    for x in range(len(input[0])):

        #update santa position
        if x % 2 == 0:

            if input[0][x] == '^':
                santaPos[1] += 1
            elif input[0][x] == 'v':
                santaPos[1] -= 1
            elif input[0][x] == '>':
                santaPos[0] += 1
            elif input[0][x] == '<':
                santaPos[0] -= 1

            visited.add((santaPos[0], santaPos[1])) #mark current position as visited
        
        #update robot position
        else:
            
            if input[0][x] == '^':
                roboPos[1] += 1
            elif input[0][x] == 'v':
                roboPos[1] -= 1
            elif input[0][x] == '>':
                roboPos[0] += 1
            elif input[0][x] == '<':
                roboPos[0] -= 1

            visited.add((roboPos[0], roboPos[1])) #mark current position as visited

    print(len(visited))


##############
#    Main    #
##############

if __name__ == '__main__':

    input = getChallengeInput(2015, 3)

    print()
    print("========== PART 1 ==========")
    print()

    challengePart1(input)

    print()
    print("========== PART 2 ==========")
    print()

    challengePart2(input)