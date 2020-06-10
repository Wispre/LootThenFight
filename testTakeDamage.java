public class testTakeDamage{
    public static void main(String[] args) {

    Person alex = new Person("alex");
    Pistol pis = new Pistol(3);

    pis.damage(alex);
    //pis.damage(alex);
    //pis.damage(alex);
    //pis.damage(alex);
    //pis.damage(alex);

    //alex.takeDamage(100);
System.out.println("Alex dodged " + alex.getDodgeCounter() + " hits !");

alex.resetDodgeCounter();
    System.out.println(alex);
    }
}
