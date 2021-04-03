
/**
 * Write a description of class Scheibe here.
 *
 * @author Lukas Breuer
 * @version 03.04.2021
 */
public class Scheibe extends Rechteck
{
    private int id;
    
    public Scheibe(int id) {
        super();
        this.id = id;
    }
    
    public void zuStart() {}
    
    public void zuHilfe() {}
    
    public void zuZiel() {}
    
    public int getId() {
        return id;
    }
}
