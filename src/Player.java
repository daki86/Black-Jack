import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> handOfCards;
	private String name;
	private int points;
	private boolean gotBlackJack = false;
	private int totWins;
	
	public Player(){
		this.handOfCards = new ArrayList<>();
		this.points = 0;
		this.totWins = 0;
		this.name = "uknown";
	}
	
	public Player(String name){
		handOfCards = new ArrayList<>();
		this.points = 0;
		this.totWins = 0;
		this.name = name;
	}
	
	public void setName(String name){	
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setPoints(int value){
		this.points = value;
	}
	
	public int getPoints(){
		return this.points;
	}
	
	public void resetPoints(){
		this.points = 0;
	}
	
	public void setWins(int win){
		this.totWins += win;
	}
	
	public int getWins(){
		return this.totWins;
	}
	
	public void setPlayerHasBlackJack(boolean gotBlackJack){
		this.gotBlackJack = gotBlackJack;
	}
	
	public boolean getPlayerHasBlackJack(){
		return this.gotBlackJack;
	}
	
	public int getTotCards(){
		return this.handOfCards.size(); 
	}
	
	public int getCardValue(int cardPos){
		return this.handOfCards.get(cardPos).getCardValue();
	}
	
	public void addCardsToHand(Card card){
		
		this.handOfCards.add(card);
	}
	
	public boolean findACardInHand(int value){
		boolean foundCard = false;
		
		for(Card cards : handOfCards){
			if(cards.getCardValue() == value){
				foundCard = true;
			}
		}
		
		return foundCard;
	}
	
	public void showHandOfCards(){	
		System.out.println("\n"+this.name+" card's:");
		System.out.println("================");
		for(Card card : handOfCards){
			System.out.println("  "+card.toString());
		}
		System.out.println("----------------");
	}
	
	public void returnCardsToDeck(DeckOfCards deckOfCards){
		
		for(Card cards : handOfCards){
			deckOfCards.returnCardToDeck(cards);
		}
		
		handOfCards.removeAll(handOfCards);
	}
	
	public String toString(){
		
		return this.handOfCards.toString();
	}
}
