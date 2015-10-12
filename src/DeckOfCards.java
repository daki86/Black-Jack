import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {
	
	private ArrayList<Card> cards;
	private Random selectOneCard;
	private int totDecks;
	
	public DeckOfCards(){
		cards = new ArrayList<Card>();
		this.totDecks = 0;
		
		for(int i = 1; i <= 4; i++){
			for(int a = 1; a <= 13; a++){
				cards.add( new Card(a, i));
			}
		}
	}
	
	public DeckOfCards(int totDecks){
		cards = new ArrayList<Card>();
		this.totDecks = totDecks;
		
		for(int x = 0; x < totDecks; x++){
			for(int i = 1; i <= 4; i++){
				for(int a = 1; a <= 13; a++){
					cards.add( new Card(a, i));
				}
			}
		}
	}
	
	public int getTotDecks(){
		return this.totDecks;
	}
	
	public void setTotDecks(int totDecks){
		this.totDecks = totDecks;
	}
	
	public void removeCardFromDeck(int cardPos){
		cards.remove(cardPos);
	}
	
	public Card dealOneCard(){
		selectOneCard = new Random();
		int cardPosition = selectOneCard.nextInt(cards.size());
		
		Card giveCard = cards.get(cardPosition);
		cards.remove(cardPosition);
		
		return giveCard;
	}
	
	public void showDeck(){	
		int nr = 1;
		
		for(Card card : cards){
			System.out.println(nr+": "+card.toString());
			nr++;
		}
	}
	
	public boolean checkIfDeckIsEmpty(){
		boolean empty = false;
		
		if(cards.isEmpty()){
			empty = true;
		}
		
		return empty;
	}
	
	public void returnCardToDeck(Card card){
		this.cards.add(card);
	}
	
	public String toString(){
		return cards.toString();
	}
	
	
}
