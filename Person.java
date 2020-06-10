import java.util.*;
import java.io.File;
import java.util.Random;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Person{

    String name;
    boolean isAlive;
    int health;
    int damageCounter;
    boolean aware = false;
    boolean scared = false;
    int dodgeCounter = 0;

    //public int distance = 100;
    public int awarenessMin = 20;
    public int engageChance = 10;

    public final int PERCENT = 100;

    public final int BASE_HEALTH = 200;
    public final int DODGE_CHANCE = 3;

    public static int distance_from_each = 100;

    public Person() {
        name = "";
        isAlive = true;
        health = BASE_HEALTH;
    }

    public Person(String name){
        this.name = name;
        isAlive = true;
        health = BASE_HEALTH;
    }

    public String getName(){
        return name;
    }

    public boolean isAlive(){
        // if(this.isAlive == false){
        // System.out.println(name + " has died !");
        // }
        return isAlive;
    }

    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public boolean isAware(){
        return aware;
    }

    public void setAware(boolean isAware){
        this.aware = isAware;
    }

    public boolean isScared(){
        return scared;
    }

    public void setScared(boolean scared){
        this.scared = scared;
    }

    public void takeDamage(int damage){

        if(this.dodge() == false){
            this.health = health - damage;

            damageCounter = damageCounter + damage;

            if(this.getHealth() <= 0){
                this.setHealth(0);
                isAlive = false;
            }
        }
        else{
        //System.out.println(this.getName() + " dodged !");
        dodgeCounter++;
        }
    }

    public void trueDamage(int damage){
        this.health = health - damage;

        this.damageCounter = this.damageCounter + damage;

        if(this.getHealth() <= 0){
            this.setHealth(0);
            isAlive = false;
        }
    }

    public int damageCounter(){
        return damageCounter;
    }

    public int getDodgeCounter(){
        return dodgeCounter;
    }

    public void resetDodgeCounter(){
        dodgeCounter = 0;
    }

    public void resetDamageCounter(){
        damageCounter = 0;
    }

    public void heal(int heal){

        int healing = health + heal;

        if(healing > BASE_HEALTH && isAlive == true){
            this.health = BASE_HEALTH;
        }
        else if(isAlive == true){
            this.health = healing;
        }
    }

    public boolean dodge(){
        Random rand  = new Random();
        int juke = rand.nextInt(10);

        if(juke < DODGE_CHANCE){
            return true;
        }
        else{
            return false;
        }
    }

    public void attack(ArrayList<Weapons> weapon, Person opponent){
        Random rand = new Random();
        int typeOfAttack = rand.nextInt(PERCENT) + 1;
        int backpackSize = weapon.size();
        int weaponToUse = 0;
        boolean didDodge = dodge();
        File ora = new File("ORA.WAV");
        File muda = new File("muda.WAV");
        File za = new File("ZaWarudo.WAV");
        File gendered = new File("gendered.WAV");



        if(typeOfAttack <= 20 && backpackSize >= 1){
            if(weapon.get(weaponToUse).useAmmo() == true && weapon.get(weaponToUse).getAmmo() == 0){
                if(backpackSize == 1){

                    if(dodge() == false){
                        System.out.println(this.getName() + " bashed " + opponent.getName() +
                        " with a " + weapon.get(weaponToUse).getName() +" out of desperation !"+ "\n");
                        opponent.trueDamage(5);
                        System.out.println(opponent.getName()+" took (" + opponent.damageCounter + ") damage " + "\n");
                        opponent.resetDamageCounter();
                    }
                    else{
                        System.out.println(this.getName()+" tried to bash "+ opponent.getName()+" with a " +
                        weapon.get(weaponToUse).getName() + " out of desperation, BUT missed !"+ "\n");
                    }
                    return;
                }
                weaponToUse = 1;
            }
            if(weapon.get(weaponToUse).useAmmo() == true && weapon.get(weaponToUse).getAmmo() == 0){

                if(dodge() == false){
                    System.out.println(this.getName() + " bashed " + opponent.getName() +
                    " with a " + weapon.get(weaponToUse).getName() +" out of desperation !"+ "\n");
                    opponent.trueDamage(5);
                    System.out.println(opponent.getName()+" took (" + opponent.damageCounter + ") damage " + "\n");
                    opponent.resetDamageCounter();
                }
                else{
                    System.out.println(this.getName()+" tried to bash "+ opponent.getName()+" with a " +
                    weapon.get(weaponToUse).getName() + " out of desperation, BUT missed !"+ "\n");
                }
                return;
            }

            if(weapon.get(weaponToUse) instanceof Starplatinum){
                weapon.get(weaponToUse).damage(opponent);
                System.out.println(this.getName() + ": STAR PLATINUM!!!" +"\n"+
                "OraOraOraOraOraOraOraOraOraOraOraOra OraOraOraOraOraOraOra OraOraOraOraOra...");
                Sound.PlaySound(ora);
                System.out.println(opponent.getName()+" took (" + opponent.damageCounter + ") damage " + "\n");
                opponent.resetDamageCounter();
                return;
            }
            if(weapon.get(weaponToUse) instanceof ZaWarudo){
                weapon.get(weaponToUse).damage(opponent);
                System.out.println(this.getName() + ": ZA WARUDO!!!" +"\n");
                Sound.PlaySound(za);
                System.out.println("MudaMudaMudaMudaMudaMudaMudaMuda MudaMudaMudaMudaMuda MudaMudaMudaMudaMuda...");
                Sound.PlaySound(muda);
                System.out.println(opponent.getName()+" took (" + opponent.damageCounter + ") damage " + "\n");
                opponent.resetDamageCounter();
                return;
            }


            if(weapon.get(weaponToUse).useAmmo() == true){
                weapon.get(weaponToUse).damage(opponent);
                System.out.println(this.getName() + " attacked with a " +
                weapon.get(weaponToUse).getName() + " "+weapon.get(weaponToUse).getClip()+" times !"+ "\n" +
                opponent.getName()+ " dodged " + opponent.getDodgeCounter() +" hits !");
                opponent.resetDodgeCounter();
                System.out.println(opponent.getName()+" took (" + opponent.damageCounter + ") damage " + "\n");
                opponent.resetDamageCounter();
                return;
            }
            else if(weapon.get(weaponToUse).useAmmo() == false  && dodge() == false){
                weapon.get(weaponToUse).damage(opponent);
                System.out.println(this.getName() + " attacked with " +
                weapon.get(weaponToUse).getName() + " !"+ "\n");
                System.out.println(opponent.getName()+" took (" + opponent.damageCounter + ") damage " + "\n");
                opponent.resetDamageCounter();
                return;
            }
            else{
                System.out.println(this.getName() + " attacked with " +
                weapon.get(weaponToUse).getName() + ", BUT missed !"+ "\n");
            }
            return;
        }

        else if(typeOfAttack <= 70 && didDodge == false){
            System.out.println(this.getName() + " punched " + opponent.getName() +" !");
            opponent.trueDamage(10);
            System.out.println(opponent.getName()+" took (" + opponent.damageCounter + ") damage " + "\n");
            opponent.resetDamageCounter();
        }
        else if(typeOfAttack <= 70 && didDodge == true){
            System.out.println(this.getName() + " tried to punched " + opponent.getName() +", BUT missed !"+ "\n");
        }
        else if(typeOfAttack <= 95 && didDodge == false){
            System.out.println(this.getName() + " kicked " + opponent.getName() +" in the crotch !");
            opponent.trueDamage(15);
            System.out.println(opponent.getName()+" took (" + opponent.damageCounter + ") damage " + "\n");
            opponent.resetDamageCounter();
        }
        else if(typeOfAttack <= 95 && didDodge == true){
            System.out.println(this.getName() + " tried to kick " + opponent.getName() +" in the crotch, BUT missed !"+ "\n");
        }
        else if(typeOfAttack > 95 && didDodge == true){
            System.out.println(this.getName() + " used the incorrect pronouns for " + opponent.getName() +" !");
            opponent.trueDamage(1);
            Sound.PlaySound(gendered);
            System.out.println(opponent.getName()+" took (" + opponent.damageCounter + ") damage " + "\n");
            opponent.resetDamageCounter();
        }
        else if(typeOfAttack > 95 && didDodge == false){
            System.out.println(this.getName() + " used the incorrect pronouns for " +
             opponent.getName() +", BUT "+ opponent.getName()+ " did not care :/"+ "\n");
        }
    }

    public String toString(){
        return name +": HP = "+ health +"\n";
    }
}
