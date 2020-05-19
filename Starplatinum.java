public class Starplatinum extends Weapons{

    private final int ONE_STARPLATINUM_DMG = 100;

    public Starplatinum(){
        this.Name = "StarPlatinum";
        this.Damage = ONE_STARPLATINUM_DMG;
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
