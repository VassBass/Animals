package fauna.organ;

import exception.NotEnoughEnergyException;

public interface Hands {
    /**
     * ability to climb to a tree
     * @return result of climb
     * @throws NotEnoughEnergyException if animal has no energy
     */
    boolean climb() throws NotEnoughEnergyException;
}
