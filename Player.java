import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * This class is part of the "Money Heist" game.
 * "Money Heuist" is a very simple, text based  game.
 *
 * @author (Abhiraj Basnet)
 * @version (2020/05/28)
 */
public class Player
{
  private String playerName;
  private Room currentRoom;
    private HashMap<String, Item> inventory;
  private ArrayList<Item> playerItems; 
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
      inventory = new HashMap <> (); // Initilise hash Map  
      
     
    }
    
    /**
     * returns the player name the current room
     */
    public Player(String playerName, Room currentRoom)
    {
    this.playerName = playerName;
      this.currentRoom = currentRoom;
    }
    
    /**
     *Gives the name of the player name when called
     */
    public String getPlayerName(){
    
    return playerName;
    
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

    /**
     * Gives the answer weather the player has item or not
     */
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



