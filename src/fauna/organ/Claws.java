package fauna.organ;

import fauna.Animal;

public interface Claws extends Hands {
    /**
     * ability to scratch another animal
     * @param animal to scratch
     * @return result of trying
     */
    boolean scratch(Animal animal);
}
