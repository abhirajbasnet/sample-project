
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
   private String name;
    private  String des;
    private int wt;
    

    /**
     * Constructor for objects of class Item
     */
    public Item()
    {
        // initialise instance variable
    }

    public Item ( String name, String description, int weight)
    {
        //intitialise instance variables
        this.name= name;
        this.des= description;
        this.wt = weight;
    }
    public String getItemDescription()
    {
        String itemString = "This room contains a ";
        itemString = itemString + name + des + wt + "grams";
        return itemString;
 
    }
    
    public String getName ()
    {
        return name;
    }
    
    public boolean checkName(String name)
    {
    
        return this.name.equals(name);
    }
    
}
