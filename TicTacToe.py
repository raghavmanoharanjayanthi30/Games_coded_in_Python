#This file represents a game of tictactoe played between two human players

# To do:
# display game board
# keep track of turns and when to stop the game
# 2 players: X and O
# used positions

#Initializing the empty 3x3 array for the board
def initialize_board():
    rows = 3
    cols = 3
    arr = [[" " for i in range(cols)] for j in range(rows)]
    return arr

#Provides the game instructions for the players to follow
def game_instructions():
    print("Please read these instructions carefully.")
    print("This is the Tic Tac Toe game. There will be two players.") 
    print("The Player 1 will use the symbol X while the Player 2 will use the symbol O.")
    print("A 3x3 board will be provided to both players. Both the players will have alternating turns.")
    print("A 3x3 board has 9 spots. So Player 1 is allowed have a maximum of 5 turns. Player 2 is allowed a maximum 4 turns.")
    print("The game is won by a player if their symbol (X or O) is positioned three times on the 3x3 board along any row, column, diagonal.")
    print("The game will stop if a player has won or if both players have used all their maximum allowed turns.")
    print("Row 0 is the first row, Row 1 is the second row. Row 2 is the third row.")
    print("Column 0 is the first column, Column 1 is the second column. Column 2 is the third column.")

# method that has functionality representing how the Tic-Tac-Toe game work
def play_TicTacToe():
    game_instructions()
    arr = initialize_board()
    for row in arr:
        print(row)
    print("This is your board to play on.")
    player1_name = input("Player 1, what is your name? ")
    player2_name = input("Player 2, what is your name? ")
    player1_turns = 0
    player2_turns = 0
    used_positions = list()
    game_result = " "

    while (player1_turns < 5) and (player2_turns < 5):
        player1_row = int(input(f"{player1_name}. It is your turn. Which row (0, 1, or 2) do you want to place X in ? "))
        player1_col = int(input(f"{player1_name}. It is your turn. Which column (0, 1, or 2) do you want to place X in ? "))
        if (arr[player1_row][player1_col] == " "):
            arr[player1_row][player1_col] = "X"
        else:
            while (arr[player1_row][player1_col] != " "):
                print(f"Player1 row: {player1_row} Player1 column: {player1_col}")
                if (arr[player1_row][player1_col] == "X"):
                    print("You have already filled that position with X in a previous turn. Try another position.")
                    player1_row = int(input(f"{player1_name}. It is your turn again. Which row (0, 1, or 2) do you want to place X in ? "))
                    player1_col = int(input(f"{player1_name}. It is your turn again. Which column (0, 1, or 2) do you want to place X in ? "))
                elif (arr[player1_row][player1_col] == "O"):
                    print(f"Your opponent {player2_name} has filled that position with O in a previous turn. Try another position")
                    player1_row = int(input(f"{player1_name}. It is your turn again. Which row (0, 1, or 2) do you want to place X in ? "))
                    player1_col = int(input(f"{player1_name}. It is your turn again. Which column (0, 1, or 2) do you want to place X in ? "))
            arr[player1_row][player1_col] = "X"
        
        player1_turns += 1
        for row in arr:
            print(row)
        print("This is the updated game board.")

        if player1_turns == 3 or player1_turns == 4 or player1_turns == 5:
            game_result = gameResult(player1_turns, player2_turns, arr, player1_name, player2_name)
            if game_result != " ":
                print(game_result)
                break
            if player1_turns == 5 and player2_turns == 4 and game_result == " ":
                print(f"The game is over. Congrats both of you {player1_name} and {player2_name} fought well. There is no winner. It is a tie.")

        if player2_turns < 4:
            player2_row = int(input(f"{player2_name}. It is your turn. Which row (0, 1, or 2) do you want to place O in ? "))
            player2_col = int(input(f"{player2_name}. It is your turn. Which column (0, 1, or 2) do you want to place O in ? "))
            if (arr[player2_row][player2_col] == " "):
                arr[player2_row][player2_col] = "O"
            else:
                while (arr[player2_row][player2_col] != " "):
                    print(f"Player2 row: {player2_row} Player2 column: {player2_col}")
                    if (arr[player2_row][player2_col] == "O"):
                        print("You have already filled that position with O in a previous turn. Try another position.")
                        player2_row = int(input(f"{player2_name}. It is your turn again. Which row (0, 1, or 2) do you want to place O in ? "))
                        player2_col = int(input(f"{player2_name}. It is your turn again. Which column (0, 1, or 2) do you want to place O in ? "))
                    elif (arr[player2_row][player2_col] == "X"):
                        print(f"Your opponent {player1_name} has filled that position with X in a previous turn. Try another position")
                        player2_row = int(input(f"{player2_name}. It is your turn again. Which row (0, 1, or 2) do you want to place O in ? "))
                        player2_col = int(input(f"{player2_name}. It is your turn again. Which column (0, 1, or 2) do you want to place O in ? "))
                arr[player2_row][player2_col] = "O"
            
            player2_turns += 1
            for row in arr:
                print(row)
            print("This is the updated game board.")
        
        if player2_turns == 3 or player2_turns == 4:
            game_result = gameResult(player1_turns, player2_turns, arr, player1_name, player2_name)
            if game_result != " ":
                print(game_result)
                break

#calculates the game result to see if any player has already won or if it is a tie
def gameResult(turns1, turns2, arr, player1, player2):
    # Set of conditions for Player 1 to win
    if arr[0][0] == "X" and arr[0][1] == "X" and arr[0][2] == "X":
        return f"Congrats {player1}! You have won"
    elif arr[1][0] == "X" and arr[1][1] == "X" and arr[1][2] == "X":
        return f"Congrats {player1}! You have won"
    elif arr[2][0] == "X" and arr[2][1] == "X" and arr[2][2] == "X":
        return f"Congrats {player1}! You have won"
    elif arr[0][0] == "X" and arr[1][0] == "X" and arr[2][0] == "X":
        return f"Congrats {player1}! You have won"
    elif arr[0][1] == "X" and arr[1][1] == "X" and arr[2][1] == "X":
        return f"Congrats {player1}! You have won"
    elif arr[0][2] == "X" and arr[1][2] == "X" and arr[2][2] == "X":
        return f"Congrats {player1}! You have won"
    elif arr[0][0] == "X" and arr[1][1] == "X" and arr[2][2] == "X":
        return f"Congrats {player1}! You have won"
    elif arr[0][2] == "X" and arr[1][1] == "X" and arr[2][0] == "X":
        return f"Congrats {player1}! You have won"
    
    # Set of conditions for Player 2 to win
    elif arr[0][0] == "O" and arr[0][1] == "O" and arr[0][2] == "O":
        return f"Congrats {player2}! You have won"
    elif arr[1][0] == "O" and arr[1][1] == "O" and arr[1][2] == "O":
        return f"Congrats {player2}! You have won"
    elif arr[2][0] == "O" and arr[2][1] == "O" and arr[2][2] == "O":
        return f"Congrats {player2}! You have won"
    elif arr[0][0] == "O" and arr[1][0] == "O" and arr[2][0] == "O":
        return f"Congrats {player2}! You have won"
    elif arr[0][1] == "O" and arr[1][1] == "O" and arr[2][1] == "O":
        return f"Congrats {player2}! You have won"
    elif arr[0][2] == "O" and arr[1][2] == "O" and arr[2][2] == "O":
        return f"Congrats {player2}! You have won"
    elif arr[0][0] == "O" and arr[1][1] == "O" and arr[2][2] == "O":
        return f"Congrats {player2}! You have won"
    elif arr[0][2] == "O" and arr[1][1] == "O" and arr[2][0] == "O":
        return f"Congrats {player2}! You have won"
    
    # If none of the players have won and all squares on the board have been filled in, 
    # then the game ends in a tie.
    else:
        return " "
    

play_TicTacToe()
