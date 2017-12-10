package advanced;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Planet extends Item {
	
	final int nbShips = 7;
	final int maxShips = 20;
	private ArrayList<SpaceShip> spaceShips;
	private Color myColor;
	private int PlayerId;
	
	public Planet(double x, double y, int w, Color myColor, int PlayerId) {
		super(x, y, w);
		this.myColor = myColor;
		this.PlayerId = PlayerId;
	}
	
	public ArrayList<SpaceShip> getSpaceShips() {
		return spaceShips;
	}
	
	@Override
	public int getPlayerId() {
		return PlayerId;
	}

	public void setOwnerId(int ownerId) {
		this.PlayerId = ownerId;
	}

	public Color getMyColor() {
		return myColor;
	}

	public void setMyColor(Color myColor) {
		this.myColor = myColor;
	}


	@Override
	public boolean contains(Point2D p) {
        double w = getWidth() / 2;
        return (this.center.getX() - w <= p.getX() && p.getX() <= this.center.getX() + w)
                && (this.center.getY() - w <= p.getY() && p.getY() <= this.center.getY() + w);
    }
	
	@Override
	public void draw(Graphics2D arg0) {
		Point2D pos = this.center;
		int x = (int) pos.getX(), y = (int) pos.getY(), w = this.getWidth();
		arg0.setColor(myColor);
		arg0.fillRect(x - w / 2, y - w / 2, w, w);
		arg0.setColor(Color.WHITE);
		arg0.drawString(String.valueOf(this.spaceShips.size()),((int)x),((int)y));
	}
	
	public void init(){
		spaceShips = new ArrayList<SpaceShip>();
		for (int i = 0; i < nbShips; i++) {
			SpaceShip mySpaceShip = new SpaceShip(center.getX() + this.getWidth()/2 - (i+1)*12, center.getY() + this.getWidth()/2 + 6, 10, PlayerId, this);
			spaceShips.add(mySpaceShip);
			Galcon.getItemList().add(mySpaceShip);
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setObjective(Item o) {
		// TODO Auto-generated method stub
		
	}
	
	public void produceShips(){
		
		if(spaceShips.size() < maxShips){
			SpaceShip mySpaceShip = new SpaceShip(center.getX(), center.getY()+20, 10, PlayerId, Planet.this);
			spaceShips.add(mySpaceShip);
			
			try {
		        Galcon.getItemList().add(mySpaceShip);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
			
		}
		
	}

}
