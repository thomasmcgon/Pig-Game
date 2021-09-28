import java.util.Scanner;
public class Pig {
	//main method that handles what type of game they want to play
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		//prints the options for game types available
		System.out.println("The Game of Pig\n----------------------------------------");
		System.out.println("1. Human vs. Human");
		System.out.println("2. Human vs. Computer");
		System.out.println("3. Computer vs. Computer\n");
		System.out.print("What kind of game do you want to play? ");
		int gamePlay = in.nextInt();
		System.out.println();
		//depending on which game they choose this will pass certain arguments to playGame
		if(gamePlay == 1) {
			playGame(true, true);
		}else if(gamePlay == 2) {
			playGame(true, false);
		}else if(gamePlay == 3) {
			playGame(false, false);
		}else {
			System.out.println("That was not a choice");
		}
	}
	//playGame method that will keep printing the players scores as long as no one has won yet
	//if statements decide what is passed into playTurn and added to totalScore#
	public static void playGame(boolean player1, boolean player2) {
		int totalScore1 = 0;
		int totalScore2 = 0;
		while(totalScore1 < 100 && totalScore2 < 100) {	
			System.out.println("Player 1 score: " + totalScore1);
			System.out.println("Player 2 score: " + totalScore2);	
			totalScore1 += playTurn(player1, 1, totalScore1);
			if(totalScore1 < 100) {
				System.out.println("Player 1 score: " + totalScore1);
				System.out.println("Player 2 score: " + totalScore2);
				totalScore2 += playTurn(player2, 2, totalScore2);
			}
		}
		System.out.println("Player 1 score: " + totalScore1);
		System.out.println("Player 2 score: " + totalScore2);
		if(totalScore1 >= 100) {
			System.out.println("Player 1 Wins!");
		}else if(totalScore2 >= 100) {
			System.out.println("Player 2 Wins!");
		}
	}
	//Goes through players turn and whatever is passed from playGame decides whether the player is human or computer
	public static int playTurn(boolean player, int number, int totalScore) {
			int dice;
			int turnScore = 0;
				//prints which player is going and whether they are a human or computer
				if(player) {
					System.out.println("\nPlayer " + number + "'s turn (Human)");
				}else {
					System.out.println("\nPlayer " + number + "'s turn (Computer)");
				}
				//rolls the die and will keep rolling as long as it is not a 1 and the player keeps rolling
				do {
					dice = (int)(Math.random() * 6 + 1);
					System.out.println("Rolled a " + dice);
					if (dice == 1) {
						turnScore = 0;
					} else {
						turnScore += dice;
					} 
				} while(dice != 1 && getDecision(player, turnScore, totalScore));
				System.out.println("Turn over\n");
				return turnScore;
		}
	//prints what the player rolled and the players score during that turn
	//this is where the player decides to roll or hold
	public static boolean getDecision(boolean player, int turnScore, int totalScore) {
		Scanner in = new Scanner(System.in);
		System.out.println("Turn Score: " + turnScore);
		//if statements for the player is human or not and whether the player is rolling or holding
		if (player == true) {
			System.out.print("Hold or Roll? (h or r) ");
			String input = in.next();
			if(input.equals("r")){
				System.out.println("Human player rolls");
				return true;
			}else {
				System.out.println("Human player holds");
				return false;
			}
		}else {
			if(turnScore >= 20){
				System.out.println("Computer player holds");
				return false;
			}else if(turnScore + totalScore >= 100) {
				System.out.println("Computer player holds");
				return false;
			}else {
				System.out.println("Computer player rolls");
				return true;
			}
		}
	}
}
