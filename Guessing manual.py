#We have a number and we are telling the computer if it is correct
import random
def computer_guess(x):
    lower_bound = 1
    upper_bound = x
    user_response = ''
    while user_response != "YES":
        if lower_bound != upper_bound:
            guess = random.randint(lower_bound, upper_bound) #throws error if lower_bound equals upper_bound
        else:
            guess = upper_bound
        user_response = input(f"Did you think of the number {guess}? Answer with Yes if number is correct), Too Low, or Too High ").upper()
        if user_response == "YES":
            print(f"The computer guessed the number {guess} that you had in mind")
            break
        elif user_response == "TOO HIGH":
            upper_bound = guess - 1
        elif user_response == "TOO LOW":
            lower_bound = guess + 1

computer_guess(30)