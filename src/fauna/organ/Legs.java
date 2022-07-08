package fauna.organ;

import exception.NotEnoughEnergyException;

public interface Legs {
    /**
     * ability to walk
     * @param distance in meters
     * @throws NotEnoughEnergyException if animal has no energy
     */
    void walk(int distance) throws NotEnoughEnergyException;

    /**
     * ability to run
     * @param distance in meters
     * @throws NotEnoughEnergyException if animal has no energy
     */
    void run(int distance) throws NotEnoughEnergyException;

    /**
     * ability to jump
     * @param height in meters
     * @return jump success
     * @throws NotEnoughEnergyException if animal has no energy
     */
    boolean jump(double height) throws NotEnoughEnergyException;
}
