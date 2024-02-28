/**
 * Represents the Pond class.
 *
 * In order to help learn course concepts, I worked on the homework with nobody else,
 * discussed homework topics and issues with nobody else, and/or consulted related
 * material which is entitled Java How to Program by Paul Deitel and Harvey Deitel.
 *
 * @author rjayanthi30 (Raghav Raahul Manoharan Jayanthi)
 * @version 1.0
 */
public class Pond {
    /**
     * Creates a main method to create Frog and Fly objects,
     * perform simulated actions such as eat, grow and
     * print the objects
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Frog frog1 = new Frog("Peepo");
        Frog frog2 = new Frog("Pepe", 10, 15);
        Frog frog3 = new Frog("Peepaw", 4.6, 5);
        Frog frog4 = new Frog("Raghav");
        Fly fly1 = new Fly(1, 3);
        Fly fly2 = new Fly(6);
        Fly fly3 = new Fly();
        frog1.setSpecies("1331 Frogs");
        System.out.println(frog1.toString());
        frog1.eat(fly2);
        System.out.println(fly2.toString());
        frog1.grow(8);
        frog1.eat(fly2);
        System.out.println(fly2.toString());
        System.out.println(frog1.toString());
        System.out.println(frog4.toString());
        frog3.grow(4);
        System.out.println(frog3.toString());
        System.out.println(frog2.toString());
    }
}
