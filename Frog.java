/**
 * Represents the Frog class.
 *
 * In order to help learn course concepts, I worked on the homework with nobody else,
 * discussed homework topics and issues with nobody else, and/or consulted related
 * material which is entitled Java How to Program by Paul Deitel and Harvey Deitel.
 *
 * @author rjayanthi30 (Raghav Raahul Manoharan Jayanthi)
 * @version 1.0
 */
public class Frog {
    private String name;
    private int age;
    private double tongueSpeed;
    private boolean isFroglet;
    private static String species = "Rare Pepe";
    /**
     * Creates a Frog with all required parameters
     *
     * @param name - the name of the frog
     * @param age - the age of the frog in months
     * @param tongueSpeed - how quickly the frog's tongue can shoot out of its mouth
     */
    public Frog(String name, int age, double tongueSpeed) {
        this.name = name;
        this.age = age;
        this.tongueSpeed = tongueSpeed;
        if (age > 1 && age < 7) {
            isFroglet = true;
        } else {
            isFroglet = false;
        }
    }
    /**
     * Creates a Frog with name as the parameter
     *
     * @param name - the name of the frog
     */
    public Frog(String name) {
        this(name, 5, 5);
    }
    /**
     * Creates a Frog with all required parameters
     *
     * @param name - the name of the frog
     * @param ageInYears - the age of the frog in years
     * @param tongueSpeed - how quickly the frog's tongue can shoot out of its mouth
     */
    public Frog(String name, double ageInYears, double tongueSpeed) {
        this(name, (int) (ageInYears * 12), tongueSpeed);
    }
    /**
     * Creates a method to set the species of the frog
     *
     * @param species - the species of the frog
     */
    public void setSpecies(String species) {
        this.species = species;
    }
    /**
     * Creates a method to retrieve the species of the frog
     *
     * @return species - the species of the frog
     */
    public String getSpecies() {
        return species;
    }
    /**
     * Creates a method to retrieve the string representation of the object
     *
     * @return String
     */
    public String toString() {
        if (isFroglet) {
            return
            String.format("My name is %s and I'm a rare froglet! I'm %d months old and my tongue has a speed of %.2f.",
                name, age, tongueSpeed);
        } else {
            return
               String.format("My name is %s and I'm a rare frog! I'm %d months old and my tongue has a speed of %.2f.",
               name, age, tongueSpeed);
        }
    }
    /**
     * Creates a method for grow to age the frog by one month and update the tongue speed accordingly
     *
     */
    public void  grow() {
        grow(1);
    }
    /**
     * Creates a method for grow to age the frog by given number of months and update the tongue speed accordingly
     *
     * @param numberOfMonths - the no of months to grow
     */
    public void grow(int numberOfMonths) {
        if (age < 12) {
            if (numberOfMonths + age > 12) {
                tongueSpeed += (12 - age);
            } else {
                tongueSpeed += numberOfMonths;
            }
        } else if (age >= 30) {
            if (tongueSpeed > 5) {
                if (tongueSpeed - numberOfMonths > 5) {
                    tongueSpeed -= numberOfMonths;
                } else {
                    tongueSpeed = 5;
                }
            }
        }
        age += numberOfMonths;
        if (age > 1 && age < 7) {
            isFroglet = true;
        } else {
            isFroglet = false;
        }
    }
    /**
     * Creates a method for the frog to catch and eat the fly
     *
     * @param fly - the fly object
     */
    public void eat(Fly fly) {
        boolean caught = false;
        if (!(fly.isDead())) {
            if (tongueSpeed > fly.getSpeed()) {
                caught = true;
            }
            if (caught && (fly.getMass() >= 0.5 * age)) {
                grow();
            }
            if (caught) {
                fly.setMass(0);
            } else {
                fly.grow(1);
            }
        }
    }
}

