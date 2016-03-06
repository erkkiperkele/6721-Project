public class coordinatesHelper {

    public static Coor[] getPolarizationCoor(StateName stateName, Coor stateCoor) {
        int symFactor = stateName == StateName.Left
                ? 1
                : -1;

        Coor[] coorToCheck = new Coor[]{
                new Coor((2 - stateCoor.getX()) * symFactor, 0 - stateCoor.getY()),
                new Coor((0 - stateCoor.getX()) * symFactor, -2 - stateCoor.getY()),
        };
        return coorToCheck;
    }

    public static Coor[] getLadderCoor(StateName stateName, Coor stateCoor) {

        //By default, we check the left ladder. For the right ladder, need to invert the col coordinates
        int symFactor = stateName == StateName.Left
                ? 1
                : -1;

        Coor[] coorToCheck = new Coor[]{
                new Coor((0 * symFactor - stateCoor.getX()), 0 - stateCoor.getY()),
                new Coor((0 * symFactor - stateCoor.getX()), 1 - stateCoor.getY()),
                new Coor((1 * symFactor - stateCoor.getX()), 1 - stateCoor.getY()),
                new Coor((1 * symFactor - stateCoor.getX()), 2 - stateCoor.getY()),
                new Coor((2 * symFactor - stateCoor.getX()), 2 - stateCoor.getY()),
        };
        return coorToCheck;
    }

    public static Coor[] getStatesCoor(StateName stateName) {
        int symFactor = stateName == StateName.Left
                ? 1
                : -1;

        return new Coor[]{
                new Coor(0 * symFactor, 0),
                new Coor(0 * symFactor, 1),
                new Coor(1 * symFactor, 1),
                new Coor(1 * symFactor, 2),
                new Coor(2 * symFactor, 2),
        };
    }
}
