package fauna.organ;

import fauna.Animal;

public interface Beak {
    /**
     * ability to peck another animal
     * @param animal to peck
     * @return result of trying
     */
    boolean peck(Animal animal);
}
