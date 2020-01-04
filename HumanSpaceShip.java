import oop.ex2.*;

/**
 * this class represents the human ship in the game SpaceWars,
 * the player controls this ship, you can rotate it right or left, accelerate to a direction,
 * shoot and teleport.
 *
 * @author Tom
 */
public class HumanSpaceShip extends SpaceShip {

    HumanSpaceShip() {
        super();
        this.setType('h');
    }

    public void doAction(SpaceWars game) {
        setShieldIsOn(false);
        GameGUI gui = game.getGUI();
        /* Teleport action */
        if (gui.isTeleportPressed())
            teleport();
        /* Moving actions */
        int direction = 0;
        boolean accelerate = false;
        if (gui.isLeftPressed())
            direction++;
        if (gui.isRightPressed())
            direction--;
        if (gui.isUpPressed())
            accelerate = true;
        this.getPhysics().move(accelerate, direction);
        /* Shield check */
        if (gui.isShieldsPressed())
            shieldOn();
        /* Shot check */
        if (gui.isShotPressed())
            fire(game);
        super.doAction(game);
    }
}
