def getChallengeInput(year: int, day: int) -> list[str]:
    """
    Reads the input file for the specified challenge and gets the lines of the file.
    Parameters:
        `year`: The challenge year.
        `day`: The challenge day.
    Returns:
        A list of strings containing every line from the input file. Trailing newlines are removed.
    """

    filePath = "../../inputs/" + str(year) + "/day" + str(day) + ".txt" #input file path

    #read file
    with open(filePath, 'r') as inputFile:
        input = inputFile.readlines()
        input = [line.rstrip() for line in input] #strip trailing newlines

    return input