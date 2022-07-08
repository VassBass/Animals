package fauna.organ;

import fauna.Animal;

public interface Fangs {
    /**
     * ability to bite another animal
     * @param animal to bite
     * @return result of trying
     */
    boolean bite(Animal animal);
}
