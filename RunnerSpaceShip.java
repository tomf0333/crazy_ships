import oop.ex2.*;

/**
 * this class represents the runner type of ship in the Spacewars game,
 * it tries to run away from the closest ship to it and teleport if it gets to close to it.
 *
 * @author Tom
 */
public class RunnerSpaceShip extends SpaceShip {

    private final static boolean ACCELERATE = true;

    private final static double CLOSE_DISTANCE = 0.25;

    private final static double CLOSE_ANGLE = 0.23;

    RunnerSpaceShip() {
        super();
        this.setType('r');
    }

    /**
     * the action that this ship does each turn.
     *
     * @param game -   the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShipPhysics thisShipPhysics = this.getPhysics();
        SpaceShip closestShip = game.getClosestShipTo(this);
        SpaceShipPhysics closestShipPhysics = closestShip.getPhysics();
        /* determine the ships moving direction to be the opposite of the closest ship */
        int direction = 0;
        if (thisShipPhysics.angleTo(closestShipPhysics) < 0)
            direction++;
        if (thisShipPhysics.angleTo(closestShipPhysics) > 0)
            direction--;
        this.getPhysics().move(RunnerSpaceShip.ACCELERATE, direction);
        /* Checks with nearest ship if it needs to teleport or not */
        this.checkFleeOption(thisShipPhysics, closestShipPhysics);
        super.doAction(game);
    }

    /**
     * Checks the distance and angle to the closest ship to determine if this ship needs to try and teleport
     * away to safety.
     *
     * @param thisShip    -   this ships physics object.
     * @param closestShip -   closest ship to this ships physics object.
     */
    private void checkFleeOption(SpaceShipPhysics thisShip, SpaceShipPhysics closestShip) {
        if (thisShip.distanceFrom(closestShip) <= RunnerSpaceShip.CLOSE_DISTANCE) {
            if (thisShip.angleTo(closestShip) <= RunnerSpaceShip.CLOSE_ANGLE)
                this.teleport();
        }
    }
}
