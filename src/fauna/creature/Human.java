package fauna.creature;

import exception.NotEnoughEnergyException;
import fauna.Animal;
import fauna.Pet;
import fauna.organ.Fangs;
import fauna.organ.Glottis;
import fauna.organ.Legs;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Human extends Pet implements Legs, Fangs, Glottis {
    private final String surname;

    private final ArrayList<Animal>pets = new ArrayList<>();

    protected Human(String name, String surname, int maxEnergy, int intelligence) {
        super(maxEnergy, intelligence, name);
        this.surname = surname;
    }

    public static Human create(String name, String surname, int maxEnergy, int intelligence){
        if (name == null || surname == null || maxEnergy <= 0 || intelligence <= 0
                || name.isEmpty() || name.isBlank() || surname.isEmpty() || surname.isBlank()){
            return null;
        }else {
            return new Human(name, surname, maxEnergy, intelligence);
        }
    }

    public String getSurname(){
        return surname;
    }

    public ArrayList<Animal>getPetsList(){
        return pets;
    }

    @Override
    public void eat() {
        increaseEnergy(getMaxEnergy()/2);
    }

    @Override
    public void sleep() {
        increaseEnergy(getMaxEnergy()/2);
    }

    @Override
    public void walk(int distance) throws NotEnoughEnergyException {
        if (distance < 200){
            decreaseEnergy(1);
        }else {
            decreaseEnergy(distance/100);
        }
    }

    @Override
    public void run(int distance) throws NotEnoughEnergyException {
        if (distance < 100){
            decreaseEnergy(2);
        }else {
            decreaseEnergy(distance/50);
        }
    }

    @Override
    public boolean jump(double height) throws NotEnoughEnergyException {
        int successChance;
        if (height < 1D) {
            decreaseEnergy(1);
            successChance = ThreadLocalRandom.current().nextInt(60, 101);
        }else if (height < 2D) {
            decreaseEnergy(2);
            successChance = ThreadLocalRandom.current().nextInt(0, 101);
        }else if (height < 2.45) {
            decreaseEnergy(5);
            successChance = ThreadLocalRandom.current().nextInt(0, 91);
        }else {
            System.out.println(this + " thought about jumping " + height + " meters high, but remembered that people cannot jump to such a height.");
            return false;
        }

        return successChance >= 80;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), surname, getIntelligence(), getMaxEnergy());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (obj == this) return true;

        Human human = (Human) obj;
        return getName().equals(human.getName()) && surname.equals(human.getSurname());
    }

    @Override
    public String toString() {
        return getName() + " " + surname;
    }

    @Override
    public boolean bite(Animal animal) {
        return animal.survive(20);
    }

    @Override
    public void makeSound() {
        System.out.println("Hello! My name is " + getName() + " " + surname + ".");
    }
}
