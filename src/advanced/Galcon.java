package advanced;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Galcon {
	private int nbPlayers;
	private int nbPlanets;
	private int nbPPlanets;//nb de planeets par player
	private ArrayList<Player> players;
	private static ArrayList<Planet> planets;
	private ArrayList<Planet> Nplanets;
	private ArrayList<Planet> playerPlanets;
	private static CopyOnWriteArrayList<Item> ItemList;
	private Random random = new Random();
	private ArrayList<Color> colors;
	final int width = 1000;
	final int height = 800;
	final int planet_width = 100;
	final int spaceship_width = 10;
	final int distMin = 60;
	
	public Galcon(int nbPlayers, int nbPlanets, int nbPPlanets) {
		this.nbPlayers = nbPlayers;
		this.nbPlanets = nbPlanets;
		this.nbPPlanets = nbPPlanets;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public static ArrayList<Planet> getPlanets() {
		return planets;
	}

	public ArrayList<Planet> getNplanets() {
		return Nplanets;
	}
	
	public static CopyOnWriteArrayList<Item> getItemList() {
		return ItemList;
	}

	public void init(){
		ItemList = new CopyOnWriteArrayList<Item>();
		players = new ArrayList<Player>();
		planets = new ArrayList<Planet>();
		playerPlanets = new ArrayList<Planet>();
		Nplanets = new ArrayList<Planet>();
		colors = new ArrayList<Color>();
		colors.add(Color.magenta);
		colors.add(Color.black);
		Manager manager = new Manager();
		for(int i=0; i<nbPlayers; i++){
			Player myPlayer = new Player(i, colors.get(i));
			for(int j=0; j<nbPPlanets; j++){
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				if(x < planet_width || y < planet_width || x > width-planet_width || y > height-(2*planet_width)){
					j--;
				}
				else{
					Planet myPlanet = new Planet(x, y, planet_width, colors.get(i), i);
					boolean nointersect = true;
					for(Planet existingPlanet : planets){
						Shape rect = new Rectangle(((int) existingPlanet.getLocation().getX() - planet_width - distMin),
								((int) existingPlanet.getLocation().getY() - planet_width - distMin), planet_width + 2 * distMin, planet_width + 2 * distMin);
						if(manager.isContained(rect, myPlanet)){
							j--;
							nointersect = false;
							break;
						}
					}
					if(nointersect){
						myPlanet.init();
						planets.add(myPlanet);
						ItemList.add(myPlanet);
					}
					
				}
			}
			for(int j=i*nbPPlanets; j<(i+1)*nbPPlanets; j++){
				playerPlanets.add(planets.get(j));
			}
			myPlayer.init(playerPlanets);
			playerPlanets.clear();
			players.add(myPlayer);
		}
		for(int i=0; i<nbPlanets - (nbPlayers * nbPPlanets); i++){
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			if(x < planet_width || y < planet_width || x > width-planet_width || y > height-(2*planet_width)){
				i--;
			}
			else{
				Planet myPlanet = new Planet(x, y, planet_width, Color.pink, -1);
				boolean nointersect = true;
				for(Planet existingPlanet : planets){
					Shape rect = new Rectangle(((int) existingPlanet.getLocation().getX() - planet_width - distMin),
							((int) existingPlanet.getLocation().getY() - planet_width - distMin), planet_width + 2 * distMin, planet_width + 2 * distMin);
					if(manager.isContained(rect, myPlanet)){
						i--;
						nointersect = false;
						break;
					}
				}
				if(nointersect){
					myPlanet.init();
					planets.add(myPlanet);
					Nplanets.add(myPlanet);
					ItemList.add(myPlanet);
				}
			}
		}
	}
	
	public static void startGameAI(int id){
		for(Planet enemyPlanet : planets){
			if(enemyPlanet.getPlayerId() != id){
				for(Planet myPlanet : planets){
					if(myPlanet.getPlayerId() == id){
						for(SpaceShip mySpaceShip : myPlanet.getSpaceShips()){
							mySpaceShip.setObjective(enemyPlanet);
						}
					}
				}
			}
		}
	}
	
	public void playerLost(int id){
		System.out.println("Player " + id + " lost.");
	}
	
	public int winner(){
		return 0;
	}
}
