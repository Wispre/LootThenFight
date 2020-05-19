import java.util.*;

public class Weapons{

    protected String Name;
    protected String Quality;
    protected int rarity;
    protected int Damage;
    protected int ammo;
    protected int clip;
    protected int DPR;
    protected boolean usesAmmo;
    // protected int FallOff;

    // public final int MAX_FIGHT_DISTANCE = 100;

    //protected static final int rarity = 3;
    public String Rusty = "Rusty";
    public String Standard = "Standard";
    public String Golden = "Golden";


    public Weapons(){
        this.Name = "";
        this.Quality = "";
        this.Damage = 0;
        //this.FallOff = 0;
    }

    public String getName(){
        return Name;
    }

    public String getQuality(){
        return Quality;
    }

    public int getDamage(){
        return Damage;
    }

    public int getAmmo(){
        return ammo;
    }

    public int getClip(){
        return clip;
    }
    public int getDPR(){
        return DPR;
    }

    public boolean useAmmo(){
        return usesAmmo;
    }

    public void damage(Person target){
        target.takeDamage(10);
    }

    public static void assessWeapons(ArrayList<Weapons> current, Weapons newFound){
        //System.out.println("method start");
        if(current.size() < 2){
            current.add(newFound);
            return;
        }

        Weapons one = current.get(0);
        Weapons two = current.get(1);

        double oneStats = 0;
        double twoStats = 0;

        Weapons worseWeapon = null;


        if(one.useAmmo() == false && two.useAmmo() == false){
            //System.out.println("test start1");
            oneStats = (double)one.getDamage();
            twoStats = (double)two.getDamage();
            //System.out.println("test end1");
        }
        else if(one.useAmmo() == false){
            //System.out.println("test start1");
            oneStats = (double)one.getDamage();
            twoStats = ((double)(two.getAmmo())/(double)(two.getClip()))* ((double)two.getDamage());
            //System.out.println("test end1");
        }
        else if(two.useAmmo() == false){
            //System.out.println("test start2");
            twoStats = (double)two.getDamage();
            oneStats = ((double)(one.getAmmo())/(double)(one.getClip()))* ((double)one.getDamage());
            //System.out.println("test end2");
        }
        else if(one.useAmmo() == true && two.useAmmo() == true){
            //System.out.println("first: " +one+ " | second: "+two);
            oneStats = ((double)(one.getAmmo())/(double)(one.getClip()))* ((double)one.getDamage());
            twoStats = ((double)(two.getAmmo())/(double)(two.getClip()))* ((double)two.getDamage());
        }

        //System.out.println(oneStats);
        //System.out.println(twoStats);

        if(oneStats < twoStats){
            worseWeapon = one;
            //System.out.println("worked 1");
        }
        else if(oneStats > twoStats)
        {
            worseWeapon = two;
            //System.out.println("worked 2");
        }
        if(oneStats == twoStats){
            if(one.useAmmo() == true && two.useAmmo() == false){
                worseWeapon = one;
                //System.out.println("worked 3");
            }
            else if(one.useAmmo() == false && two.useAmmo() == true){
                worseWeapon = two;
                //System.out.println("worked 4");
            }
            else{
                worseWeapon = one;
                //System.out.println("worked 5");
            }
        }

        double oneStats1 = 0;
        double twoStats2 = 0;
//System.out.println(worseWeapon);
        if(worseWeapon.useAmmo() == false && newFound.useAmmo() == false){
            //System.out.println("test start1");
            oneStats1 = (double)worseWeapon.getDamage();
            twoStats2 = (double)newFound.getDamage();
            //System.out.println("test end1");
        }
        else if(worseWeapon.useAmmo() == false){
            oneStats1 = (double)worseWeapon.getDamage();
            twoStats2 = ((double)(newFound.getAmmo())/(double)(newFound.getClip()))* ((double)newFound.getDamage());
        }
        else if(newFound.useAmmo() == false){
            twoStats2 = (double)newFound.getDamage();
            oneStats1 = ((double)(worseWeapon.getAmmo())/(double)(worseWeapon.getClip()))* ((double)worseWeapon.getDamage());
        }
        else if(worseWeapon.useAmmo() == true && newFound.useAmmo() == true){
            //System.out.println("test start4");
            oneStats1 = ((double)(worseWeapon.getAmmo())/(double)(worseWeapon.getClip()))* ((double)worseWeapon.getDamage());
            twoStats2 = ((double)(newFound.getAmmo())/(double)(newFound.getClip()))* ((double)newFound.getDamage());
            //System.out.println("test start4");
        }

        if(oneStats1 < twoStats2){
            current.remove(current.indexOf(worseWeapon));
            current.add(newFound);
        }
        else if(oneStats1 > twoStats2){
            return;
        }
        else if(oneStats1 == twoStats2){
            if(worseWeapon.useAmmo() == true && newFound.useAmmo() == false){
                current.remove(current.indexOf(worseWeapon));
                current.add(newFound);
            }
            else{
                return;
            }
        }
        // else{
        //     return;
        // }
    }

    public String toString(){

        String weaponDis = getName();

        return weaponDis;
    }
}
