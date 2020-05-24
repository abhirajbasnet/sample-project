import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
  private HashMap<String, Item> inventory;
  private ArrayList<Item> playerItems; 
 /**
     * Constructor for objects of class Player
     */
    public Player()
    {
      inventory = new HashMap <> (); // Initilise hash Map  
      
    }
  
     public Item dropItem(Item name)
      {
     Set <String> keys = inventory.keySet();
    for (String item:keys){
               if (item.equals(name)){
                Item tempItem = inventory.get(name);
                inventory.remove(name);
                return tempItem;
               
          }
        
         }
          System.out.println("There is nothing to drop!");
          return null;
    }
 
 public String getInventoryString()
 {
  String returnInventory = "Inventory:";
  Set<String> keys = inventory.keySet();
  for (String item: keys)
  {
    returnInventory += "" +item;
    }
  return returnInventory;
 }

 public boolean playerHasItem (Item name)
{
  return inventory.containsKey(name);
}

public Item getItem(Item name)
    {
      return inventory.get(name);
    }

public void addItemInventory(Item item){
playerItems.add(item);
 System.out.println(item.getItemDescription() + "was taken" + "it has " +  "power");
}

public void removeItemInventory(Item item){
playerItems.remove(item);
}

public boolean hasItemInventory(Item item){
    
return inventory.containsKey(item);
}

 public String printAllInventory()
 {
   String returnString = "Items:";
    for(Item item : playerItems){
    
     returnString += " " + item.getName();
    }
    return returnString;
        
   }

}



