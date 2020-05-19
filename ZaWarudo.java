public class ZaWarudo extends Weapons{

    private final int ONE_ZAWARUDO_DMG = 100;

    public ZaWarudo(){
        this.Name = "Za Warudo";
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
        //System.out.println("OraOraOraOraOraOraOraOraOraOraOraOra");
    }
}
