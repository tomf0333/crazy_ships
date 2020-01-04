import oop.ex2.*;

/**
 * this class represents the special space ship in the SpaceWars game,
 * a vampiric alien took hold on this now with every shi[ that comes in contact with it,
 * this ship will heal itself.
 * regardless of this ability it will chase down its enemies when it has a lot of energy and flee otherwise.
 */
public class SpecialSpaceShip extends SpaceShip {

    private static final boolean ACCELERATE = true;

    private static final double CLOSE_ANGLE = 0.18;

    SpecialSpaceShip() {
        super();
        this.setType('s');
    }

    public void doAction(SpaceWars game) {
        SpaceShipPhysics thisShipPhysics = this.getPhysics();
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipPhysics = closestShip.getPhysics();
        super.doAction(game);
        if (this.getCurrentEnergy() <= this.getMaxEnergy() / 3) {
            this.runAway(game, thisShipPhysics, closestShipPhysics);
        } else {
            if (this.getCurrentEnergy() <= 2 * (this.getMaxEnergy()) / 3) {
                this.charge(game, thisShipPhysics, closestShipPhysics);
            } else {
                this.charge(game, thisShipPhysics, closestShipPhysics);
                this.checkFireOption(game, thisShipPhysics, closestShipPhysics);
            }
        }
    }

    /**
     * runs away from the closest ship, based on the runner ship.
     *
     * @param game -   the game object to which this ship belongs.
     */
    private void runAway(SpaceWars game, SpaceShipPhysics thisShip, SpaceShipPhysics closestShip) {
        /* determine the ships moving direction to be the opposite of the closest ship */
        int direction = 0;
        if (thisShip.angleTo(closestShip) < 0)
            direction++;
        if (thisShip.angleTo(closestShip) > 0)
            direction--;
        this.getPhysics().move(SpecialSpaceShip.ACCELERATE, direction);
    }

    /**
     * runs towards the nearest ship to collide with it, based on the basher and aggressive ships.
     *
     * @param game -   the game object to which this ship belongs.
     */
    private void charge(SpaceWars game, SpaceShipPhysics thisShip, SpaceShipPhysics closestShip) {
        /* determine the ships moving direction to be the same as the closest ship */
        int direction = 0;
        if (thisShip.angleTo(closestShip) < 0)
            direction--;
        if (thisShip.angleTo(closestShip) > 0)
            direction++;
        this.getPhysics().move(SpecialSpaceShip.ACCELERATE, direction);
    }

    /**
     * checks if the ship is close enough to fire at someone, based on the aggressive ship.
     *
     * @param game        -   the SpaceWars game object.
     * @param thisShip    -   this ships physics object.
     * @param closestShip -   closest ship to this ships physics object.
     */
    private void checkFireOption(SpaceWars game, SpaceShipPhysics thisShip, SpaceShipPhysics closestShip) {
        if (thisShip.angleTo(closestShip) <= SpecialSpaceShip.CLOSE_ANGLE)
            this.fire(game);
    }

    /**
     * this ships health will go up by one for each contact regardless of shield.
     */
    public void collidedWithAnotherShip() {
        this.addToHealth();
    }
}
