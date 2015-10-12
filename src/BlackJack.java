import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack extends CardGame{
	DeckOfCards deck;
	Player dealer = new Player("DEALER");
	Scanner input = new Scanner(System.in);
	String choice;
	
	boolean playAgain = false;
	boolean gameEnd = false;
	boolean correctInput = false;
	int totSum = 0;
	int totSum2 = 0;
	
	public void playBlackJack(ArrayList<Player> players){
		
		int nrOfDecks = 0;
		
		System.out.println("\nHow many decks are we using?");
		System.out.print("total decks: ");
		
		do{
			if(input.hasNextInt()){
				nrOfDecks = Integer.parseInt(input.nextLine());
				if(nrOfDecks == 0){
					System.out.println("\nYou need atleast 1 deck to play");
				}
			}else{
				System.out.println("\nwrong input");
				String temp = input.nextLine();
			}
		}while(nrOfDecks <= 0);
		
		deck = new DeckOfCards(nrOfDecks);
		
		do{
			//deal the cards at start
			for(int a = 0; a < 2; a++){
				for(int i = 0; i < players.size(); i++){
					players.get(i).addCardsToHand(deck.dealOneCard());
					setPlayersTotSumOfCards(players, i);
					if(players.get(i).getPoints() == 21){
						players.get(i).setPlayerHasBlackJack(true);
					}
				}
				if(a == 0){
					dealer.addCardsToHand(deck.dealOneCard());
				}
			}
			System.out.println("\nBlack Jack Table");
			System.out.println("##############################");
			
			showPlayersCards(players, dealer);
			
			System.out.println("\n##############################");
			
			for(int i = 0; i<players.size(); i++){
				hitPlayerWithACard(deck, players, i, dealer);
			}
			System.out.println();
			showFinalResult(players, dealer);
			
			for(Player player : players){
				player.returnCardsToDeck(deck);
				player.setPlayerHasBlackJack(false);
			}	
			
			dealer.returnCardsToDeck(deck);
			resetAllPlayersPoints(dealer, players);
			
			System.out.println("\nDeal another game?");
			do{	
				System.out.print("yes or no: ");
				choice = input.nextLine();
				
				if(choice.equalsIgnoreCase("yes")){
					playAgain = true;				
					correctInput = true;
				}else if(choice.equalsIgnoreCase("no")){
					playAgain = false;					
					correctInput = true;
				}else{
					System.out.println("\nwrong input!");
					correctInput = false;
				}
			}while(!correctInput);
			
		}while(playAgain);
	}
	
	public void showFinalResult(ArrayList<Player> players, Player dealer){
		
		for(Player player : players){
			if(player.getPlayerHasBlackJack() == true){
				player.setWins(1);
				System.out.println(player.getName()+" got BLACK JACK WINNER");
			}else if(player.getPoints() > dealer.getPoints() && player.getPoints() <= 21 || player.getPoints() <= 21 && dealer.getPoints() > 21){
				player.setWins(1);
				System.out.println(player.getName()+" got "+player.getPoints()+" WINNER");
			}else if(player.getPoints() == dealer.getPoints() && player.getPoints() <= 21){
				System.out.println(player.getName()+" got "+player.getPoints()+" PUSH");
			}else if(player.getPoints() > 21 || player.getPoints() < dealer.getPoints() && dealer.getPoints() <= 21){
				System.out.println(player.getName()+" got "+player.getPoints()+" LOST");
			}
		}
		System.out.println("\n"+dealer.getName()+" got "+dealer.getPoints());
	}
	
	public void showPlayersCards(ArrayList<Player> players, Player dealer){
		
		for(int i = 0; i < players.size(); i++){		
			setPlayersTotSumOfCards(players, i);
			players.get(i).showHandOfCards();
			
			if(players.get(i).getPlayerHasBlackJack() == true){
				System.out.println("sum: "+players.get(i).getPoints()+" BLACK JACK");
			}else if(players.get(i).getPoints() > 21){
				System.out.println("sum: "+players.get(i).getPoints()+" BUSTED");
			}else if(totSum2 <= 21 && totSum2 != totSum){
				System.out.println("sum: "+totSum+" / "+totSum2);
			}else{
				System.out.println("sum: "+players.get(i).getPoints());
			}
		}
		setDealersTotSumOfCards(dealer);
		dealer.showHandOfCards();
		if(dealer.getPoints() > 21){
			System.out.println("sum: "+dealer.getPoints()+" BUSTED");
		}else{
			System.out.println("sum: "+dealer.getPoints());
		}
		
	}
	
	public void setPlayersTotSumOfCards(ArrayList<Player> players, int playerIndex){
		totSum = 0;
		totSum2 = 0;
		
		for(int i = 0; i < players.get(playerIndex).getTotCards(); i++){
			
			if(players.get(playerIndex).getCardValue(i) > 10){
				totSum += 10;
				totSum2 += 10;
			//Ace has two values
			}else if(players.get(playerIndex).getCardValue(i) == 1){
				totSum += 1;
				totSum2 += 11;
			}else{
				totSum += players.get(playerIndex).getCardValue(i);
				totSum2 += players.get(playerIndex).getCardValue(i);
			}
		}

		if(totSum2 > totSum && totSum2 <= 21){
			players.get(playerIndex).setPoints(totSum2);
		}else{
			players.get(playerIndex).setPoints(totSum);
		}
	}
	
	public void setDealersTotSumOfCards(Player dealer){
	
		int totSum = 0;
		int totSum2 = 0;
		
		for(int i = 0; i < dealer.getTotCards(); i++){
			if(dealer.getCardValue(i) > 10){
				totSum += 10;
				totSum2 += 10;
			}else if(dealer.getCardValue(i) == 1){
				totSum += 1;
				totSum2 += 11;
			}else{
				totSum += dealer.getCardValue(i);
				totSum2 += dealer.getCardValue(i);
			}
		}
		
		if(totSum2 > totSum && totSum2 <= 21){
			dealer.setPoints(totSum2);
		}else{
			dealer.setPoints(totSum);
		}
	}
	
	public void resetAllPlayersPoints(Player dealer, ArrayList<Player> players){
		for(Player player : players){
			player.resetPoints();
		}
		dealer.resetPoints();
	}
	
	public void hitPlayerWithACard(DeckOfCards deck, ArrayList<Player> players, int playerIndex, Player dealer){
		
		boolean hitMe = true;
		String option;
		
		if(players.get(playerIndex).getPlayerHasBlackJack() != true){
			do{
				
				System.out.println("\n"+players.get(playerIndex).getName()+" sum: "+players.get(playerIndex).getPoints());
				
				System.out.print("Hit or Stand: ");
				option = input.nextLine();
				
				if(option.equalsIgnoreCase("hit")){
					players.get(playerIndex).addCardsToHand(deck.dealOneCard());
					setPlayersTotSumOfCards(players, playerIndex);
					
					System.out.println("\nBlack Jack Table");
					System.out.println("##############################");
					
					showPlayersCards(players, dealer);
					
					System.out.println("\n##############################");
				
					if(players.get(playerIndex).getPoints() > 21){
						System.out.println("\nBlack Jack Table");
						System.out.println("##############################");
						
						showPlayersCards(players, dealer);
						
						System.out.println("\n##############################");
						hitMe = false;
					}
					
				}else if(option.equalsIgnoreCase("Stand")){
					System.out.println("\nBlack Jack Table");
					System.out.println("##############################");
					
					showPlayersCards(players, dealer);
					
					System.out.println("\n##############################");
					hitMe = false;
				}else{
					System.out.println("\nwrong input!");
				}
				
			}while(hitMe);
		}
		
		//Dealers turn
		hitMe = true;
		if(playerIndex+1 == players.size()){
			do{
				
				dealer.addCardsToHand(deck.dealOneCard());
				setDealersTotSumOfCards(dealer);
				
				if(dealer.getPoints() >=17 && dealer.getPoints() <= 21 || dealer.getPoints() > 21){
					hitMe = false;
				}
					
			}while(hitMe);		
			
			System.out.println("\nBlack Jack Table");
			System.out.println("##############################");
			
			showPlayersCards(players, dealer);
			
			System.out.println("\n##############################");
		}
	}
}
