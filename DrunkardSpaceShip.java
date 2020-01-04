import oop.ex2.*;

import java.util.Random;

/**
 * this class represents the drunk space ship of the Space Wars game,
 * he drank too much and it caused him to not exactly work the way you expect a qualified and dignified
 * space ship pilot to work.
 *
 * @author Tom
 */
public class DrunkardSpaceShip extends SpaceShip {

    private static final int ADD_ENERGY_AFTER_TELEPORT = 10;

    private static final int ADD_ENERGY_AFTER_SHOT = 10;

    private static int BOUND_FOR_ORDER = 3;

    private static int BOUND_FOR_DELAY = 300;

    private static int MINIMAL_DELAY = 100;

    private int randomnessDelay = 0;

    private int order = 0;

    private static boolean FIRST_SINCE_CHANGE = true;

    DrunkardSpaceShip() {
        super();
        this.setType('d');
    }

    public void doAction(SpaceWars game) {
        super.doAction(game);
        /* making the random order for the ship and the number that decides how long it will last */
        if (this.randomnessDelay == 0) {
            DrunkardSpaceShip.FIRST_SINCE_CHANGE = true;
            this.order = randomizeParameter(true);
            this.randomnessDelay = DrunkardSpaceShip.MINIMAL_DELAY + randomizeParameter(false);

        } else {
            /* do the order that was given */
            this.randomnessDelay--;
            switch (this.order) {
                case 0:
                    this.spinEraticaly();
                    break;
                case 1:
                    if (DrunkardSpaceShip.FIRST_SINCE_CHANGE)
                        this.changeCurrentEnergy(SpaceShip.ENERGY_VALUE_TELEPORT);
                    this.superTeleport();
                    break;
                case 2:
                    if (DrunkardSpaceShip.FIRST_SINCE_CHANGE)
                        this.changeCurrentEnergy(SpaceShip.ENERGY_VALUE_SHOT);
                    this.chargeForGlory(game);
                    break;
                default:
                    break;
            }
            DrunkardSpaceShip.FIRST_SINCE_CHANGE = false;
        }

    }

    /* All the drunk methods */

    /**
     * the first drunk action,
     * spins in place clockwise.
     */
    private void spinEraticaly() {
        this.getPhysics().move(true, 1);
    }

    /**
     * the second drunk action,
     * somehow the drunkenness got to the teleport engine and it supercharged.
     * so now the ship can teleport many more times at a given time.
     */
    private void superTeleport() {
        this.teleport();
        this.addToCurrentEnergy(DrunkardSpaceShip.ADD_ENERGY_AFTER_TELEPORT);

    }

    /**
     * the third drunk action,
     * the drunk pilot figured this is it for him and he should charge for a glorious death.
     * so he only moves forward and shoots.
     */
    private void chargeForGlory(SpaceWars game) {
        this.getPhysics().move(true, 0);
        this.fire(game);
        this.resetDelay();
        this.addToCurrentEnergy(DrunkardSpaceShip.ADD_ENERGY_AFTER_SHOT);
    }

    /**
     * a method that is used to create a random number for the order or for the delay respectfully.
     *
     * @param orderOrDelay -   true if we try to randomize the order and false if it is the delay.
     * @return a new random number based on the parameter.
     */
    private static int randomizeParameter(boolean orderOrDelay) {
        Random rand = new Random();
        int bound;
        if (orderOrDelay)
            bound = DrunkardSpaceShip.BOUND_FOR_ORDER;
        else
            bound = DrunkardSpaceShip.BOUND_FOR_DELAY;
        return rand.nextInt(bound);
    }
}
