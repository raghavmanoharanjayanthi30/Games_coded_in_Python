# Computer has a secret number and we are trying to guess it in a few tries
import random

def guess_solver(number):
    random_integer = random.randint(0,number)
    guess_count = 1
    guess_limit = 3
    guess = int(input(f"Guess an integer between 0 and {number}: "))
    while (guess_count < guess_limit and guess != number) :
        if guess == random_integer:
            print(f"Congrats you have guessed correctly on try {guess_count}.")
            break
        elif guess < random_integer:
            guess = int(input("Your guess is too low. Guess a higher number: "))
            guess_count += 1
        else:
            guess = int(input("Your guess is too high. Guess a lower number: "))
            guess_count += 1

        if guess_count == guess_limit and guess != random_integer:
            print(f"You have had {guess_limit} guesses. The correct number was {random_integer}.")
        elif guess_count == guess_limit and guess == random_integer:
            print(f"Congrats you have guessed correctly on try {guess_count}.")

guess_solver(20)
