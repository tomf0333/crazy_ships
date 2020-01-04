import oop.ex2.*;

/**
 * this class represents the aggressive type of ship in the Spacewars game,
 * it basically tries to run after the closest ship to it and fire when possible.
 *
 * @author Tom
 */
public class AggressiveSpaceShip extends SpaceShip {

    private final static boolean ACCELERATE = true;

    private final static double CLOSE_ANGLE = 0.21;

    AggressiveSpaceShip() {
        super();
        this.setType('a');
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
        this.getPhysics().move(AggressiveSpaceShip.ACCELERATE, direction);
        /* Checks with nearest ship if it needs to fire at it */
        this.checkFireOption(game, thisShipPhysics, closestShipPhysics);
        super.doAction(game);
    }

    /**
     * Checks the angle to the closest ship to determine if this ship needs to try and shoot it.
     *
     * @param game        -   the SpaceWars game object.
     * @param thisShip    -   this ships physics object.
     * @param closestShip -   closest ship to this ships physics object.
     */
    private void checkFireOption(SpaceWars game, SpaceShipPhysics thisShip, SpaceShipPhysics closestShip) {
        if (thisShip.angleTo(closestShip) <= AggressiveSpaceShip.CLOSE_ANGLE)
            this.fire(game);
    }
}
