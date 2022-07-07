package exception;

import fauna.Animal;

public class NotEnoughEnergyException extends Exception {
    public NotEnoughEnergyException(Animal animal){
        System.out.println(animal + " is tired. Can't do anything.");
    }
}
