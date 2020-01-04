import oop.ex2.*;

/**
 * this class represents the basher ship in the game Space Wars,
 * it tries to *bash* enemies, which means run towards them and activate its shield before impact.
 */
public class BasherSpaceShip extends SpaceShip {

    private final static boolean ACCELERATE = true;

    private final static double CLOSE_DISTANCE = 0.19;

    BasherSpaceShip() {
        super();
        this.setType('b');
    }

    /**
     * the action that this ship does each turn.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShipPhysics thisShipPhysics = this.getPhysics();
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipPhysics = closestShip.getPhysics();
        /* determine the ships moving direction to be the same as the closest ship */
        int direction = 0;
        if (thisShipPhysics.angleTo(closestShipPhysics) < 0)
            direction--;
        if (thisShipPhysics.angleTo(closestShipPhysics) > 0)
            direction++;
        this.getPhysics().move(BasherSpaceShip.ACCELERATE, direction);
        /* Checks with nearest ship if it needs to fire at it */
        this.checkBashOption(thisShipPhysics, closestShipPhysics);
        super.doAction(game);
    }

    /**
     * Checks the distance to the closest ship to determine if this ship needs to try and bash it.
     *
     * @param thisShip    -   this ships physics object.
     * @param closestShip -   closest ship to this ships physics object.
     */
    private void checkBashOption(SpaceShipPhysics thisShip, SpaceShipPhysics closestShip) {
        if (thisShip.distanceFrom(closestShip) <= BasherSpaceShip.CLOSE_DISTANCE) {
            this.shieldOn();
        }
    }
}
