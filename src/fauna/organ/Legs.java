package fauna.organ;

import exception.NotEnoughEnergyException;

public interface Legs {
    /**
     * ability to walk
     * @param distance in meters
     */
    void walk(int distance) throws NotEnoughEnergyException;

    /**
     * ability to run
     * @param distance in meters
     */
    void run(int distance) throws NotEnoughEnergyException;

    /**
     * ability to jump
     * @param height in meters
     * @return jump success
     */
    boolean jump(double height) throws NotEnoughEnergyException;
}
