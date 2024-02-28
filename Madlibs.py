# Madlibs

# Game where you are prompted to substitute for  blanks in a story before reading it out loud

#blank1 = input("Today, every student has a computer small enough to fit into enough his __________ . Input a noun to fill in this blank: ")



def madlib_solver():
    blank1 = input("Today, every student has a computer small enough to fit into enough his __________ . Input a noun to fill in this blank: ")
    
    blank2 = input("Computers can add, multiply, divide, and ________ . Input a verb to fill in this blank: ")

    blank3 = input("They can also ______ better than a human. Input a verb to fill in this blank: ")    
    print("\n")
    madlib = f"Today, every student has a computer small enough to fit into enough his {blank1}. Computers can add, multiply, \
divide, and {blank2}. They can also {blank3} better than a human."
    
    print(madlib)

madlib_solver()