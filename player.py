import math
import random

class Player():
    def __init__(self, letter):
        self.letter = letter

    def get_move(self, game):
        pass

#inheritance
class HumanPlayer(Player):
    def __init__(self, letter):
        super().__init__(letter)

    def get_move(self,game): #there were errors in this method now fixed, try block inside while loop
        validSquare = False
        value = None
        while not validSquare:
            square = input(self.letter + '\'s turn. Input move (0-8): ')
            try:
                value = int(square)
                if value not in game.available_moves():
                    raise ValueError
                validSquare = True
            except ValueError:
                print("The square value is invalid. Try again")

        return value

    
class RandomComputerPlayer(Player):
    def __init__(self, letter):
        super().__init__(letter)
    
    def get_move(self,game):
        return random.choice(game.available_moves())
        
#using minimax and utility functions to create an unbeatable AI player
class GeniusComputerPlayer(Player):
    def __init__(self,letter):
        super().__init__(letter)
    
    def get_move(self,game):
        if len(game.available_moves()) == 9: #if all spaces are available
            square = random.choice(game.available_moves()) #grab a random spot
        else: 
            square = self.minimax(game, self.letter)['position']
        return square
    
    def minimax(self,state, player): #state because we are passing in different states of the games
        max_player = self.letter
        other_player = 'O' if player == "X" else "X"
        #current_state = state
        
        #check whether there is a winner as it stands
        if state.current_winner == other_player: #because other player just played
            return{'position': None, 'score': 1* (state.num_empty_squares()+ 1) if other_player == max_player else -1* (state.num_empty_squares()+ 1)}
        
        #check if there are any empty spaces
        elif not state.empty_squares():
            return {'position': None, 'score': 0} #position is none because we didn't move anywhere
            #no empty squares means nobody won

        if player == max_player:
            best = {'position': None, 'score': -math.inf} #each score should maximize
        else:
            best = {'position': None, 'score': math.inf} #each score should minimize
        
        for move in state.available_moves():
            #make a move
            current_state = state
            state.make_move(move, player)
            # simulate a game at that spot
            sim_score = self.minimax(state, other_player)
            # undo the move to go back to same state
            state.board[move] = ' '
            state.current_winner = None
            sim_score['position'] = move
            #update best dictionary if necessary
            
            if player == max_player:
                if sim_score['score'] > best['score']: #max player
                    best = sim_score
            else:
                if sim_score['score'] < best['score']: #min player
                    best = sim_score
 
        return best