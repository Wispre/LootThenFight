public class WeebKatana extends Weapons{

    private final int ONE_ZAWARUDO_DMG = 61;

    public WeebKatana(){
        this.Name = "Weeb Katana";
        this.Damage = ONE_ZAWARUDO_DMG;
        this.DPR = getDamage();
        this.usesAmmo = false;
    }

    public String getName(){
        return Name;
    }

    public int getDamage(){
        return Damage;
    }

    @Override
    public void damage(Person target){
        target.trueDamage(this.getDamage());
    }
}
