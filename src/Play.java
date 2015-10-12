import java.util.ArrayList;
import java.util.Scanner;

public class Play {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		ArrayList<Player> players = new ArrayList<>();
		
		String menuChoice = "";
		String name;
		int totPlayers = 0;
		
		BlackJack blackJack = new BlackJack();
		
		do{
			if(!menuChoice.equals("")){
				switch(menuChoice){
						case "1":
							if(!players.isEmpty()){
								players.removeAll(players);
							}
							
							System.out.println("\nWelcome to Black Jack!");
							System.out.println("\nThere are 5 seats at the table");
							System.out.println("How many players are participating?");
							
							do{
								System.out.print("\nTotal players: ");
								if(input.hasNextInt()){
									blackJack.setTotPlayers(totPlayers = Integer.parseInt(input.nextLine()));
									if(totPlayers <= 0 || totPlayers > 5)
										System.out.println("\nwrong input!");
								}else{
									System.out.println("\nwrong input!");
									String temp = input.nextLine();
								}
									
							}while(totPlayers <= 0 || totPlayers > 5);
							
							System.out.println("\nType your name");
							
							for(int i = 0; i < blackJack.getTotPlayers(); i++){
								System.out.print((i+1)+": ");
								name = input.nextLine();
								players.add(new Player(name));
							}
							blackJack.playBlackJack(players);
							break;		
						default:
							System.out.println("\nwrong input!");
							break;
				}
			}
			System.out.println("\n#################");
			System.out.println("Pick a Game");
			System.out.println("\n1. Black Jack");
			System.out.println("2. Exit");
			System.out.println("\n#################");
			System.out.print("Option: ");
			menuChoice = input.nextLine();
			
			
		}while(!menuChoice.equals("2"));
		
		System.out.println("\nBye!!");	
	}

}
