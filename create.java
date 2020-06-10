import java.util.*;

public class create{

    public static final int NUM_OF_DUPLICATE_WEAPONS = 4;
    public static final int AWARENESS_PERCENT = 2;
    public static final int PERCENT = 100;
    public static final int SECONDS_TO_SLEEP = 4;

    public static void main(String[] args){

        Thread sound = new SoundBackground();

        if(args.length != 2){
            System.out.println("Error: java create (player1) (player2)");
            System.exit(0);
        }

        Random rand = new Random();

        Person one = new Person(args[0]);
        Person two = new Person(args[1]);

        Person three = new Person(args[0]);

        Pistol gun = new Pistol(1);
        Pistol gun2 = new Pistol(2);
        Pistol gun3 = new Pistol(3);
        Starplatinum star = new Starplatinum();
        ZaWarudo za = new ZaWarudo();
         //WeebKatana katana = new  WeebKatana();

        int num_of_rounds = 0;

        ArrayList<Weapons> weaponList = new ArrayList<Weapons>();//rusty
        ArrayList<Weapons> weaponList2 = new ArrayList<Weapons>();//common
        ArrayList<Weapons> weaponList3 = new ArrayList<Weapons>();//golden

        ArrayList<Weapons> person_one_backpack = new ArrayList<Weapons>();//person 1
        ArrayList<Weapons> person_two_backpack = new ArrayList<Weapons>();// person 2

        for(int i = 0; i < NUM_OF_DUPLICATE_WEAPONS;i++){//multiples of items
        weaponList.add(gun);
        weaponList2.add(gun2);
        weaponList3.add(gun3);
        //weaponList2.add(katana);
        }

        weaponList3.add(za);
        weaponList3.add(star);

        //System.out.println(weaponList3);

        ArrayList<ArrayList<Weapons>> loot = new ArrayList<ArrayList<Weapons>>();
        loot.add(weaponList);
        loot.add(weaponList2);
        loot.add(weaponList3);

/////////////////////////////START GAME////////////////////////////////////////
        while(one.isAlive() && two.isAlive()){

///////////////////////////////////looting phase////////////////////////////////
            while(!(one.isAware() || two.isAware())){

                num_of_rounds++;

                int findLoot = rand.nextInt(101);
                int moveFirst = rand.nextInt(2);

                int lootRarity_one = rand.nextInt(3);
                int lootRarity_two = rand.nextInt(3);

                int awareCheck = rand.nextInt(100)+1;
                int whoIsAware = rand.nextInt(2);

                boolean enoughForTwo = true;

                if(loot.get(0).size() + loot.get(1).size() + loot.get(2).size() == 0){

                    break;
                }
                else{
                    enoughForTwo = true;
                }

                if(lootRarity_one == lootRarity_two){

                    if(loot.get(lootRarity_one).size() <= 1){

                        enoughForTwo = false;
                    }
                }
                else if(lootRarity_one != lootRarity_two){
                    if(loot.get(lootRarity_one).size() == 0){
                        enoughForTwo = false;
                    }
                    if(loot.get(lootRarity_two).size() == 0){
                        enoughForTwo = false;
                    }
                }
                else{
                    enoughForTwo = true;
                }

                if(false && findLoot < 25 && loot.get(lootRarity_one).size() >= 1){
                    int foundItem = rand.nextInt(loot.get(lootRarity_one).size());
                    Weapons.assessWeapons(person_one_backpack,loot.get(lootRarity_one).get(foundItem));

                    loot.get(lootRarity_one).remove(loot.get(lootRarity_one).get(foundItem));
                }
                else if(false && findLoot >= 25 && findLoot < 50 &&
                        loot.get(lootRarity_one).size() >= 1){
                    int foundItem = rand.nextInt(loot.get(lootRarity_one).size());
                    Weapons.assessWeapons(person_two_backpack,loot.get(lootRarity_one).get(foundItem));
                    loot.get(lootRarity_one).remove(loot.get(lootRarity_one).get(foundItem));
                    }
                //person 1 loots first
                 else if(findLoot >= 50 && findLoot < 75 &&
                         loot.get(lootRarity_one).size() + loot.get(lootRarity_two).size() >= 2 && enoughForTwo && moveFirst == 0){

                            int foundItem = 0;

                            if(loot.get(lootRarity_one).size() == 0){
                                foundItem = 0;
                            }
                            else{
                                foundItem = rand.nextInt(loot.get(lootRarity_one).size());
                            }

                             Weapons.assessWeapons(person_one_backpack,loot.get(lootRarity_one).get(foundItem));

                             loot.get(lootRarity_one).remove(loot.get(lootRarity_one).get(foundItem));

                                int foundItem2 = 0;
                                if(loot.get(lootRarity_two).size() == 0){
                                    foundItem2 = 0;
                                }
                                else{
                                    foundItem2 = rand.nextInt(loot.get(lootRarity_two).size());
                                }

                             Weapons.assessWeapons(person_two_backpack,loot.get(lootRarity_two).get(foundItem2));
                             loot.get(lootRarity_two).remove(loot.get(lootRarity_two).get(foundItem2));

                 }//person two loots first

                   else if(findLoot >= 50 && findLoot < 75 &&
                           loot.get(lootRarity_one).size() + loot.get(lootRarity_two).size() >= 2 && enoughForTwo && moveFirst == 1){

                               int foundItem2 = 0;
                               if(loot.get(lootRarity_two).size() == 0){
                                   foundItem2 = 0;
                               }
                               else{
                                   foundItem2 = rand.nextInt(loot.get(lootRarity_two).size());
                               }

                              Weapons.assessWeapons(person_two_backpack,loot.get(lootRarity_two).get(foundItem2));
                              loot.get(lootRarity_two).remove(loot.get(lootRarity_two).get(foundItem2));

                              int foundItem = 0;

                              if(loot.get(lootRarity_one).size() == 0){
                                  foundItem = 0;
                              }
                              else{
                                  foundItem = rand.nextInt(loot.get(lootRarity_one).size());
                              }

                              Weapons.assessWeapons(person_one_backpack,loot.get(lootRarity_one).get(foundItem));
                              loot.get(lootRarity_one).remove(loot.get(lootRarity_one).get(foundItem));
                   }

                else if(awareCheck < AWARENESS_PERCENT && whoIsAware == 0){
                    one.setAware(true);
                    System.out.println(one.getName()+" became aware !" + "\n");
                }
                else if(awareCheck < AWARENESS_PERCENT && whoIsAware == 1){
                    two.setAware(true);
                    System.out.println(two.getName()+" became aware !" + "\n");
                }
                else{
                    continue;
                }
            }
            ///////////////////////////////////////////////////////battle phase////////////////////////////////////////////////////////
            //one.setIsAlive(false);
            sound.start();
            while(one.isAlive() && two.isAlive()){
                try {
                    Thread.sleep(SECONDS_TO_SLEEP * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
//System.out.println("loop start");
            int firstInteraction = 0;
            int bothAware_who_Goes_First = rand.nextInt(2) + 1;
            int typeOfAttack = rand.nextInt(PERCENT) + 1;



            if(one.isAware() && !(two.isAware())){
                one.attack(person_one_backpack, two);
                two.setAware(true);
                if(two.isAlive() == false){
                    System.out.println(two.getName() +" DIED !!!"+ "\n");
                    break;
                }
                two.attack(person_two_backpack, one);

                if(one.isAlive() == false){
                    System.out.println(one.getName() +" DIED !!!"+ "\n");
                    break;
                }
                System.out.println(one.toString() + two.toString() + "--------------------------------------------------------");
            }
            else if(two.isAware() && !(one.isAware())){
                two.attack(person_two_backpack, one);
                if(one.isAlive() == false){
                    System.out.println(one.getName() +" DIED !!!"+ "\n");
                    break;
                }
                one.setAware(true);
                one.attack(person_one_backpack, two);
                if(two.isAlive() == false){
                    System.out.println(two.getName() +" DIED !!!"+ "\n");
                    break;
                }
                System.out.println(one.toString() + two.toString()+ "--------------------------------------------------------");
            }
            else{
                if(bothAware_who_Goes_First == 1){
                    one.attack(person_one_backpack, two);
                    if(two.isAlive() == false){
                        System.out.println(two.getName() +" DIED !!!"+ "\n");
                        break;
                    }
                    two.attack(person_two_backpack, one);
                    if(one.isAlive() == false){
                        System.out.println(one.getName() +" DIED !!!"+ "\n");
                        break;
                    }
                    System.out.println(one.toString() + two.toString()+ "--------------------------------------------------------");
                }
                if(bothAware_who_Goes_First == 2){
                    two.attack(person_two_backpack, one);
                    if(one.isAlive() == false){
                        System.out.println(one.getName() +" DIED !!!"+ "\n");
                        break;
                    }
                    one.attack(person_one_backpack, two);
                    if(two.isAlive() == false){
                        System.out.println(two.getName() +" DIED !!!"+ "\n");
                        break;
                    }
                    System.out.println(one.toString() + two.toString() + "--------------------------------------------------------");
                }
            }
//System.out.println("loop end")
            }

            // if(one.isAlive() == false){
            //     System.out.println(one.getName() +" DIED !!!"+ "\n");
            //     break;
            // }
            // if(two.isAlive() == false){
            //     System.out.println(two.getName() +" DIED !!!"+ "\n");
            //     break;
            // }
            SoundBackground.terminate();
            //SoundBackground.endSong();
            //sound.start();

            //song.currentThread().interrupt();
            System.out.println(one.toString() + person_one_backpack.toString()+ "\n"+
            two.toString() + person_two_backpack.toString());
        }
    }
}
