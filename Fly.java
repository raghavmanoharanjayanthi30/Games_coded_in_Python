/**
 * Represents the Fly class.
 *
 * In order to help learn course concepts, I worked on the homework with nobody else,
 * discussed homework topics and issues with nobody else, and/or consulted related
 * material which is entitled Java How to Program by Paul Deitel and Harvey Deitel.
 *
 * @author rjayanthi30 (Raghav Raahul Manoharan Jayanthi)
 * @version 1.0
 */
public class Fly {
    private double mass;
    private double speed;
    /**
     * Creates a Fly with all required parameters
     *
     * @param mass - the mass of the fly
     * @param speed - how quickly this Fly can maneuver through the air while flying
     */
    public Fly(double mass, double speed) {
        this.mass = mass;
        this.speed = speed;
    }
    /**
     * Creates a Fly with mass as parameter and assigns a default value for speed
     *
     * @param mass - the mass of the fly
     */
    public Fly(double mass) {
        this(mass, 10);
    }
    /**
     * Creates a Fly with no parameters and assigns a default value for both mass and speed
     *
     */
    public Fly() {
        this(5, 10);
    }
    /**
     * Creates a method to set the mass of the fly
     *
     * @param mass - the mass of the fly
     */
    public void setMass(double mass) {
        this.mass = mass;
    }
    /**
     * Creates a method to set the speed of the fly
     *
     * @param speed - the speed of the fly
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    /**
     * Creates a method to retrieve the mass of the fly
     *
     * @return mass - the mass of the fly
     */
    public double getMass() {
        return mass;
    }
    /**
     * Creates a method to retrieve the speed of the fly
     *
     * @return speed - the speed of the fly
     */
    public double getSpeed() {
        return speed;
    }
    /**
     * Creates a method to retrieve the string representation of the object
     *
     * @return String
     */
    public String toString() {
        if (mass == 0) {
            return String.format("I'm dead, but I used to be a fly with a speed of %.2f.", speed);
        } else {
            return String.format("I'm a speedy fly with %.2f speed and %.2f mass.", speed, mass);
        }
    }
    /**
     * Creates a method to check if the fly is dead
     *
     * @return boolean
     */
    public boolean isDead() {
        return (mass == 0) ? true : false;
    }
    /**
     * Creates a method for grow and update the speed accordingly
     *
     * @param addedMass - the added mass of the fly
     */
    public void grow(int addedMass) {
        if (mass < 20) {
            for (int count = 1; count <= addedMass; count++) {
                int track = 20 - (int) mass;
                if (count <= track) {
                    speed++;
                } else {
                    speed = speed - 0.5;
                }
            }
        } else {
            for (int count = 1; count <= addedMass; count++) {
                speed = speed - 0.5;
            }
        }
        mass += addedMass;
    }
}

