import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
	int totPlayers;
	int totDecks;
	ArrayList<Player> players;
	
	public void setTotPlayers(int totPlayers){
		this.totPlayers = totPlayers;
	}
	
	public int getTotPlayers(){
		return this.totPlayers;
	}
	
	public void setTotDecks(int totDecks){
		this.totDecks = totDecks;
	}
	
	public int getTotDecks(){
		return this.totDecks;
	}
	
	public void createAListOfPlayers(int totPlayers){
		Scanner input = new Scanner(System.in); 
		players = new ArrayList<>();
		String name;
		
		for(int i = 0; i < totPlayers; i++){
			System.out.print("name: ");
			name = input.nextLine();
			players.add(new Player(name));
		}
	}
	
}
