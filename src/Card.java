
public class Card {
	private int value;
	private String suit;
	
	public Card(int value, int suit){
		this.value = value;
		if(suit == 1){
			this.suit = "Heart";
		}else if(suit == 2){
			this.suit = "Spade";
		}else if(suit == 3){
			this.suit = "Dimond";
		}else if(suit == 4){
			this.suit = "Club";
		}
	}
	
	public Card(int value, String suit){
		this.value = value;
		this.suit = suit;
	}
	
	public void setCardSuit(String suit){
		this.suit = suit;
	}
	
	public String getCardSuit(){
		return this.suit;
	}
	
	public void setCardValue(int value){
		this.value = value;
	}
	
	public int getCardValue(){
		return this.value;
	}
	
	public String toString(){
		String displayCard;
		
		/*
		if(this.value == 11){
			displayCard = "\n"+this.suit+" of Jack";
		}else if(this.value == 12){
			displayCard = "\n"+this.suit+" of Queen";
		}else if(this.value == 13){
			displayCard = "\n"+this.suit+" of King";
		}else if( this.value == 1){
			displayCard = "\n"+this.suit+" of Ace";
		}*/
		
		switch (value){
			case 1:
				displayCard = this.suit+" of Ace";
				break;
			case 11: 
				displayCard = this.suit+" of Jack";
				break;
			case 12:	
				displayCard = this.suit+" of Queen";
				break;
			case 13:
				displayCard = this.suit+" of King";
				break;
			default:
				displayCard = this.suit+" of "+this.value;			
		}
		
		return displayCard;
	}
}
