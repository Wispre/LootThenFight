import java.util.Random;

public class Pistol extends Weapons implements usable{

    private boolean reload = false;


    private final int RUSTY_BULLET_DMG = 6;
    private final int STANDARD_BULLET_DMG = 8;
    private final int GOLDEN_BULLET_DMG = 10;
    private final int AMMO = 18;

    private final int CLIP_AMOUNT = 6;
    ///////////////////////////////////
    // RUSTY_RARITY_LEVEL = 1;       //
    // STANDARD_RARITY_LEVEL = 2;    //
    // GOLDEN_RARITY_LEVEL = 3;      //
    //////////////////////////////////
    public Pistol(int rarityLevel){

        rarity(rarityLevel);
        this.clip = CLIP_AMOUNT;
        setDamage(getName());
        //this.FallOff = 70;
        this.DPR = getClip() * getDamage();
        this.usesAmmo = true;
        this.ammo = AMMO;
    }

    public String getName(){
        return Name;
    }

    public void setClip(int clip){
        this.clip = clip;
    }

    public int getDamage(){
        return Damage;
    }

    public void setDamage(String name){
        if(name.contains("Rusty")){
            this.Damage = RUSTY_BULLET_DMG;
        }
        if(name.contains("Standard")){
            this.Damage = STANDARD_BULLET_DMG;
        }
        if(name.contains("Golden")){
            this.Damage = GOLDEN_BULLET_DMG;
        }
    }
    @Override
    public void damage(Person target){
        if(reload == true){
            reload = false;
            System.out.println(target.getName() + " noticed his opponent reloading " + "\n");
            return;
        }

        for(int i = 0;i < getClip();i++){
            if(ammo == 0){
                break;
            }

            target.takeDamage(this.getDamage());
            ammo--;
        }
    }

    public void rarity(int rarity){
        // Random rand = new Random();
        // int rarityLevel = rand.nextInt(rarity) + 1;

        if(rarity == 1){
            this.Name = "Rusty Pistol";
            this.rarity = 1;
        }
        if(rarity == 2){
            this.Name = "Standard Pistol";
            this.rarity = 2;
        }
        if(rarity == 3){
            this.Name = "Golden Pistol";
            this.rarity = 3;
        }
    }

    public int getRarity(){
        return rarity;
    }
}
