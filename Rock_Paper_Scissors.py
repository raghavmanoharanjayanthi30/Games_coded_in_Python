#playing with the user
import random

def game_result(human, computer):
    if human == computer:
        print("Tie")
    else:
        print(whoWon(human, computer))

def whoWon(human, computer):
    if (human == "rock" and computer == "scissors") or (human == "paper" and computer == "rock") or (human == "scissors" and computer == "paper"):
        return "You win"
    else:
        return "Computer wins"

computer = random.choice(["Rock", "Paper", "Scissors"]).lower()
human = input("Enter your choice: Rock, Paper, Scissors: ").lower()
print(computer)
game_result(human, computer)