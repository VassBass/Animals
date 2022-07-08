package fauna.organ;

import exception.NotEnoughEnergyException;

public interface Wings {

    /**
     * ability to fly
     * @param distance in meters
     * @throws NotEnoughEnergyException if animal has no energy
     */
    void fly(int distance) throws NotEnoughEnergyException;
}
