import java.awt.Image;

import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 *
 * @author oop
 */
public class SpaceShip {

    /* Constants */
    static private final int START_MAX_ENERGY = 210;

    static private final int START_ENERGY = 190;

    static private final int FULL_HEALTH = 22;

    static private final int DELAY = 7;

    static protected final int ENERGY_VALUE_SHOT = 19;

    static private final int ENERGY_VALUE_SHIELD = 3;

    static protected final int ENERGY_VALUE_TELEPORT = 140;
	
	static private final int ENERGY_DECREASE_ON_HIT = 10;
	
	static private final int ENERGY_INCREASE_ON_BASH = 18;

    /* Members */
    private SpaceShipPhysics shipPhysics;

    private int maxEnergy;

    private int currentEnergy;

    private int currentHealth;

    private int shotDelay;

    private boolean shieldIsOn;

    private char type;

    SpaceShip() {
        this.maxEnergy = SpaceShip.START_MAX_ENERGY;
        this.currentEnergy = SpaceShip.START_ENERGY;
        this.currentHealth = SpaceShip.FULL_HEALTH;
        this.resetDelay();
        this.shieldIsOn = false;
        this.shipPhysics = new SpaceShipPhysics();
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * <p>
     * for the template (or standard) ship it just decreases shot delay and increases energy.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        if (this.shotDelay > 0)
            this.shotDelay--;
        if (this.currentEnergy + 1 <= this.maxEnergy)
            this.currentEnergy++;
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip() {
        if (!this.shieldIsOn) {
			this.maxEnergy -= SpaceShip.ENERGY_DECREASE_ON_HIT;
			if (this.currentEnergy > this.maxEnergy)
				this.currentEnergy = this.maxEnergy;
            this.currentHealth--;
        }
		else{
			this.maxEnergy += SpaceShip.ENERGY_INCREASE_ON_BASH;
			this.currentEnergy += SpaceShip.ENERGY_INCREASE_ON_BASH;
		}
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        this.currentHealth = SpaceShip.FULL_HEALTH;
        this.currentEnergy = SpaceShip.START_ENERGY;
        this.maxEnergy = SpaceShip.START_MAX_ENERGY;
        this.resetDelay();
        this.shipPhysics = new SpaceShipPhysics();
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (this.currentHealth == 0)
            return true;
        return false;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.shipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!this.shieldIsOn){
			this.maxEnergy -= SpaceShip.ENERGY_DECREASE_ON_HIT;
			if (this.currentEnergy > this.maxEnergy)
				this.currentEnergy = this.maxEnergy;
            this.currentHealth--;
		}
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage() {
        if (this.type == 'h') {
            if (this.shieldIsOn)
                return GameGUI.SPACESHIP_IMAGE_SHIELD;
            else
                return GameGUI.SPACESHIP_IMAGE;
        } else {
            if (this.shieldIsOn)
                return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
            else
                return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (this.currentEnergy >= SpaceShip.ENERGY_VALUE_SHOT && this.shotDelay == 0) {
            this.currentEnergy -= SpaceShip.ENERGY_VALUE_SHOT;
            this.shotDelay = SpaceShip.DELAY;
            game.addShot(this.shipPhysics);
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (this.currentEnergy >= SpaceShip.ENERGY_VALUE_SHIELD) {
            this.currentEnergy -= SpaceShip.ENERGY_VALUE_SHIELD;
            this.shieldIsOn = true;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (this.currentEnergy >= SpaceShip.ENERGY_VALUE_TELEPORT) {
            this.currentEnergy -= SpaceShip.ENERGY_VALUE_TELEPORT;
            this.shipPhysics = new SpaceShipPhysics();
        }
    }

    /**
     * resets the delay for a specific ship.
     */
    protected void resetDelay() {
        this.shotDelay = 0;
    }

    /* Getters */
    protected int getCurrentHealth() {
        return this.currentHealth;
    }

    protected int getCurrentEnergy() {
        return this.currentEnergy;
    }

    protected int getMaxEnergy() {
        return this.maxEnergy;
    }

    protected char getType() {
        return this.type;
    }

    /* Setters */
    protected void changeCurrentEnergy(int newValue) {
        this.currentEnergy = newValue;
    }

    protected void addToCurrentEnergy(int newValue) {
        this.currentEnergy += newValue;
    }

    protected void setShieldIsOn(boolean isOn) {
        this.shieldIsOn = isOn;
    }

    protected void setType(char type) {
        this.type = type;
    }

    protected void addToHealth() {
        this.currentHealth++;
    }

}
