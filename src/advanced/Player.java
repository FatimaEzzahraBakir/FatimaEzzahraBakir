package advanced;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	private int id;
	final int nbPlanets = 1;
	private ArrayList<Planet> planets;
	private Random random = new Random();
	private Color myColor;
	
	public Player(int id, Color myColor) {
		this.id = id;
		this.myColor = myColor;
	}
	
	public int getId() {
		return id;
	}
	
	public int getNbPlanets() {
		return nbPlanets;
	}
	
	public ArrayList<Planet> getPlanets() {
		return planets;
	}
	public void setPlanets(ArrayList<Planet> planets) {
		this.planets = planets;
	}
	
	public void init(ArrayList<Planet> myPlanets){
		planets = myPlanets;
	}
	
	public void planet_conquered(Planet planet){
		
	}
	
	public void planet_lost(Planet planet){
		
	}
}
