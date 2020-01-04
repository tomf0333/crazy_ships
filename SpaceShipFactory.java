import oop.ex2.*;

/**
 * this class is used to create the different ships in the instance of the SpaceWars game,
 * the created ships are based on the arguments to the program.
 * ('a' = aggressive, 'h' = human, 'r' = runner, 'd' = drunk, 's' = special, 'b' = basher)
 */
public class SpaceShipFactory {

    /**
     * creates an array of ship according to the args from the command.
     *
     * @param args -   several letters given as arguments that determine the ships in the game according
     *             to the info above.
     * @return an array of SpaceShips as specified above.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] result = new SpaceShip[args.length];
        String argsString = "";
        for (String stringCount: args)
            argsString += stringCount;
        char[] charArray = argsString.toCharArray();
        for (int i = 0; i < args.length; i++) {
            switch (charArray[i]) {
                case 'h':
                    result[i] = new HumanSpaceShip();
                    break;
                case 'r':
                    result[i] = new RunnerSpaceShip();
                    break;
                case 'a':
                    result[i] = new AggressiveSpaceShip();
                    break;
                case 'b':
                    result[i] = new BasherSpaceShip();
                    break;
                case 'd':
                    result[i] = new DrunkardSpaceShip();
                    break;
                case 's':
                    result[i] = new SpecialSpaceShip();
                    break;
                default:
            }
        }
        return result;
    }
}
