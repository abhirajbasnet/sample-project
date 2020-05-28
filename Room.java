import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * This class is part of the "Money Heist" game.
 * "Money Heist" is a very simple, text based  game.
 * 
 *It contains the room and the  description of the room needed for the player
 *
 * @author  Abhiraj Basnet
 * @version 2020.05.28
 */

public class Room 
{
    private String description;
    private String name;
    private boolean isLocked;
    private HashMap<String, Room> exits; // stores exits of this room.
    private ArrayList<Item> roomItem;
    private ArrayList<Room> roomNames;
    private HashMap<Room, Item> roomHashMapItem;
    

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String name, String description, boolean isLocked) 
    {
        this.description = description;
        this.name=name;
        this.isLocked=isLocked;
        exits = new HashMap<>();
        roomItem = new ArrayList();
        roomNames = new ArrayList();
        roomHashMapItem = new HashMap<>();
        
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" +  getExitString() + ".\n" +getAllItems();
    }
    
    public String getAllItems()
    {
    return "You have some " + listOfItems();
    
    }
    
    private String listOfItems(){
    String returnString = "Items:";
    for(Item item : roomItem){
    
     returnString += " " + item.getName();
    }
    return returnString;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Method getRoomItem
     *
     * @param stringItem taken from the command which was converted into a string
     * @return Item clss according to the input string
     */
    public Item getRoomItem(String stringItem){
        Item itemToReturn = null;
        for(Item item: roomItem){
            if(item.getName().contains(stringItem)){
                itemToReturn = item;
            }
        }
        return itemToReturn;
    }
    public Room getRoomName(String stringName){
    Room nameToReturn = null;
    for(Room name: roomNames){
    if(name.getName().equals(stringName)){
        nameToReturn = name;
    }
    }
return nameToReturn;

}
    
    public void addItemInRoom(Item item)
    {
    roomItem.add(item);
    }
    
     public void removeItemInRoom(Item item)
    {
    roomItem.remove(item);
    
    }
    public boolean getLockedStatus()
    {
    return isLocked;
    }
    
    public void setLockedStatus(boolean newStatus)
    {
     isLocked = newStatus;
    }
    public String getName(){
    return name;
    }
    public boolean checkRoomItem(ArrayList<Item> roomItems)
    {       
           //Same issue as above. roomItems is not an Item so you cannot call checkName(). you need for each loop.
      if (hasroomItem())
       {
         for(Item item: roomItems) {
        if(item.getName().equals(name)){
              return true;
            } else
       {
            return false;
        }
      }
       }
    
          else{
            return false;
      }
         return this.name.equals(name);
    }

     public boolean hasroomItem() 
    {
        
        
        if ( listOfItems() != null) {
            return true;
        }
        else{
            return false;
        }
    }
    
}

