import java.util.*;

public class testing{
    public static void main(String[] args) {

        Starplatinum star = new Starplatinum();


        ArrayList<Weapons> list = new ArrayList<Weapons>();
        list.add(star);
        list.add(star);



        Weapons.assessWeapons(list, star);

        System.out.println(list);
    }

}
