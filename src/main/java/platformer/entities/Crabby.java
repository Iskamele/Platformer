package platformer.entities;

import static platformer.utilz.Constants.EnemyConstants.CRABBY;
import static platformer.utilz.Constants.EnemyConstants.CRABBY_HEIGHT;
import static platformer.utilz.Constants.EnemyConstants.CRABBY_WIDTH;

public class Crabby extends Enemy {

    public Crabby(float xCoordinate, float yCoordinate) {
        super(xCoordinate, yCoordinate, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
    }
}
