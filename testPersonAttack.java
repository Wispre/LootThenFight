import java.util.*;

public class testPersonAttack{

    public static void main(String[] args) {

    Pistol pistol = new Pistol(1);
    //System.out.println("dmg " + pistol.getDamage());
    Pistol pistol2 = new Pistol(2);
    //System.out.println("dmg " + pistol2.getDamage());
    Pistol pistol3 = new Pistol(3);
    //System.out.println("dmg " + pistol3.getDamage());

    ArrayList<Weapons> list = new ArrayList<Weapons>();
    list.add(pistol);
    list.add(pistol2);

    Person one = new Person("Juan");
    Person two = new Person("jaimes");


        System.out.println(one.toString() + two.toString());

        one.attack(list, two);

        System.out.println(one.toString() + two.toString());

        one.attack(list, two);

        System.out.println(one.toString() + two.toString());
        
        one.attack(list, two);

        System.out.println(one.toString() + two.toString());

    }
}
