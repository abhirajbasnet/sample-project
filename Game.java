import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *  This class is part of the "Money Heist" game.
 * "Money Heuist" is a very simple, text based  game.
 *  
 *  This game is about the heist. 
 *  The player should heist the money from the bank.
 *  It is intresting an enjoyable to ply the game
 * 
 * @author  abhiraj
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Player player;
    private Room currentRoom;
    private ArrayList<Item>playerItem;
    private HashMap<Item, Room > roomItem;
    private int count;//to count the steps
    String playerName;
    private Scanner reader = new Scanner(System.in);
    
    ArrayList<Item> inventory = new ArrayList<Item>();
     private int numberOfMoves;
    private int limitOfMoves;
    
     Room basecamp,storage, ammunition,basement,reception, 
        managerOffice, policeDepartment, securityOffice, moneyVault, carPark;
        
     
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        playerItem = new ArrayList();
        numberOfMoves = 0;
        player = new Player(playerName, currentRoom);
        
     }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
       
        Item blueprint, cellphone, twoWayRadio, drillMachine, duffelBag, guns,faceMask,
        maps, toolHolder, cars, keys, computers, telephones, vaultKey, flashLights,bullet,
        money, gold, carKey, roomKey;
        
        // create the rooms
        basecamp = new Room("basecamp", "stading in the main room plan", false);
        storage = new Room("storage", "searching for the equipment in the storage",true);
        ammunition = new Room("ammunition", "standing in the ammuinition room", false);
        basement= new Room("basement", "standing in the basement", false);
        reception = new Room("reception", "standing in the entrance of the bank", false);
        managerOffice = new Room("managerOffice", "standing in the manager office", false);
        policeDepartment = new Room("policeDepartment", "standing in the police office", false);
        securityOffice = new Room("securityOffice", "standing in the security office", true);
        moneyVault = new Room("moneyVault", "standing in the money vault", true);
        carPark = new Room("carPark", "standing in the car park", false);

        // initialise room exits
        basecamp.setExit("east", basement);
        basecamp.setExit("south", storage);

        storage.setExit("north", basecamp);
        storage.setExit("south", ammunition);

        ammunition.setExit("north", storage);

        basement.setExit("west", basecamp);
        basement.setExit("south", reception);

        reception.setExit("north", basement);
        reception.setExit("east", policeDepartment);
        reception.setExit("west", managerOffice);
        reception.setExit("up", securityOffice);
        
        managerOffice.setExit("east", reception);
        

        policeDepartment.setExit("west",reception);

        securityOffice.setExit("down",reception);
        securityOffice.setExit("upstair", moneyVault);

        moneyVault.setExit("downstair", securityOffice);
        moneyVault.setExit("south", carPark);
        
        //creating the Item
        
        blueprint = new Item ("blueprint", " It provides the detail information of the bank.!!!", 3);
        cellphone = new Item ( "cellphone", " The best cellphone of 2020 !!!",300);
        twoWayRadio = new Item ( "twoWayRadio", " It helps to have conversation between two people and it cannot be traced.!!!",50);
        drillMachine = new Item ( "drillMachine", " It is use to drill the wall and locker.!!!",10 );
        duffelBag = new Item ("duffelBag", " It is use to put money.!!!", 50);
        guns = new Item ("guns", " It is the pistol and holds 10 bullets in the magzine.!!!", 50);
        faceMask = new Item ("faceMask", " It is the monkey mask !!!", 10);
        maps = new Item ("maps", " It has the detail way in and way of the bank.!!!", 50);
        toolHolder = new Item ("toolHolder", " It has the necessary tools for the heist.!!!", 90);
        cars = new Item ("cars", " Van which is used for the theft.!!!", 500);
        keys = new Item ("keys", " It is use to open the security office.!!!", 5);
        computers = new Item ("computers", " It is use to disarm the security.!!!", 500);
        telephones = new Item ("telephones", " It is use to contact people.!!!", 50);
        vaultKey = new Item ("vaultKey", " It is use to open the vault room.!!!", 5);
        flashLights = new Item ("duffelBag", " It helps to see in the dark.!!!", 5);
        bullet = new Item ("bullet", " It is use in the magzine of the gun.!!!", 50);
        money = new Item ("money", " It helps to buy stuff.!!!", 50);
        gold = new Item ("gold", " Yellow shiny object.!!!", 50);
        carKey = new Item ("carKey", " It helps to run a car.!!!", 50);
        roomKey = new Item ("key", "hap heart shape", 50);

        //insert items 

        storage.addItemInRoom(drillMachine);
        storage.addItemInRoom(keys);
        storage.addItemInRoom(duffelBag);
        ammunition.addItemInRoom(guns);
        ammunition.addItemInRoom(faceMask);
        basecamp.addItemInRoom(blueprint);
        basecamp.addItemInRoom(cellphone);
        basecamp.addItemInRoom(twoWayRadio);
        basement.addItemInRoom(maps);
        basement.addItemInRoom(toolHolder);
        basement.addItemInRoom(cars);
        reception.addItemInRoom(keys);
        reception.addItemInRoom(computers);
        managerOffice.addItemInRoom(telephones);
        managerOffice.addItemInRoom(vaultKey);
        policeDepartment.addItemInRoom(telephones);
        policeDepartment.addItemInRoom(computers);
        policeDepartment.addItemInRoom(guns);
        securityOffice.addItemInRoom(computers);
        securityOffice.addItemInRoom(flashLights);
        moneyVault.addItemInRoom(money);
        moneyVault.addItemInRoom(gold);
        carPark.addItemInRoom(cars);
        carPark.addItemInRoom(carKey);
        
        currentRoom = basecamp;  // start game outside
    }
    
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing " + playerName + "Have a beautiful day ahead.");
    }
     
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("What is your name?");
        playerName = reader.nextLine();
        System.out.println();
        System.out.println("Welcome " + " to the Money heist! " + playerName);
        System.out.println("Your main task is to open the vault and get the money.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        chooseLevel();
        System.out.println(currentRoom.getLongDescription());
    }
    
    public void addItemInventory(Item item)
    {
    playerItem.add(item);
    System.out.println(item.getItemDescription() + "was taken");
    }
    
     public void removeItemInventory(Item item)
    {
     playerItem.remove(item);
     System.out.println(item.getItemDescription() + "was removed");
    
    
    }
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("inventory")) {
            printInventory();
        }
        else if (commandWord.equals("go")) {
            wantToQuit=goRoom(command);
        }
        else if (commandWord.equals("take")) {
            takeItem(command);
        }
        else if (commandWord.equals("drop")) {
            dropItem(command);
        }
        else if (commandWord.equals("use")) {
            useItem(command);
        }else if (commandWord.equals("inspect")) {
            //lookItem(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("look")){  // gives the real time situation of room
                System.out.println(currentRoom.getLongDescription());
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander ");
        //implement random Hints -> massive bonus points
        System.out.println("around at the rooms. ");
        System.out.println();
        System.out.println("Your command words are: ");
        parser.showCommands();
    }
    
    private String printInventory(){
    String returnString = "Items:";
    for(Item item : playerItem){
    
     returnString += " " + item.getName();
    }
    return returnString;
    }
    
    

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else  {
          
            if(currentRoom.getLockedStatus()== true){// door is locked
            System.out.println("The door is locked, you need to find key");
            System.out.println(currentRoom.getLongDescription());
            }
            else {
            currentRoom = nextRoom;  
            boolean decision = countMove();     
            
            System.out.println(currentRoom.getLongDescription());
       }
       if(currentRoom== carPark){
            System.out.println("You Win");
            return true;
            }
        
    }
    return false;
}
    
    private void takeItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return;
        }

        String itemFromCommand = command.getSecondWord();
        Item currentItem = currentRoom.getRoomItem(itemFromCommand);
        
        if (currentItem == null) {
            System.out.println("You can't take anything, no?!");
        }
        else {
            currentRoom.removeItemInRoom(currentItem);
            //player.addItemInventory(currentItem);
            playerItem.add(currentItem);
            
            System.out.println(currentRoom.getLongDescription());
        }
      
    }
    
    private void useItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("use what?");
            return;
        }

        String itemFromCommand = command.getSecondWord();
        Item currentItem = findPlayerItem(itemFromCommand);

        if (currentItem == null) {
            System.out.println("You can't use anything, no?!");
        }
        else {
             if(currentRoom.getName().equals("storage") && currentItem.getName().equals("keys")){
               currentRoom.setLockedStatus(false);
               System.out.println(currentRoom.getName());
               
               System.out.println(currentRoom.getLockedStatus());
               System.out.println("You just used the " + currentItem.getName());

            }
             else {
             if(currentRoom.getName().equals("moneyVault") && currentItem.getName().equals("vaultKey")){
               currentRoom.setLockedStatus(false);
               System.out.println(currentRoom.getName());
               
               System.out.println(currentRoom.getLockedStatus());
               System.out.println("You just used the " + currentItem.getName());
            }
           
        }}}
    
       
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    
    
    public void dropItem (Command command)
    {
       if(! command.hasSecondWord()){
           System.out.println("Drop what???"); 
           return;
    
      }
    
    String itemFromCommand = command.getSecondWord();
    Item itemToDrop = findPlayerItem(itemFromCommand);    
        
      if(itemToDrop == null){
          System.out.println("You don't have" + " " + itemFromCommand);
          
        }else{
          
          currentRoom.addItemInRoom(itemToDrop);
          removeItemInventory(itemToDrop);
          System.out.println ("You have dropped"+ " " + itemToDrop.getName());
          
    }
         
    }
    
    public Item getPlayerItem(String stringItem){
        Item itemToReturn = null;
        for(Item item: playerItem){
            if(item.getName().contains(stringItem)){
                itemToReturn = item;
            }
        }
        return itemToReturn;
    }
    
    private Item findPlayerItem(String name){
        Item itemToSearch = null;
        
        for (Item item : playerItem) {
        if (item.getName().equals(name)) {
            itemToSearch = item;
        }else{
            itemToSearch = null;
        }
        }
        
        if(itemToSearch == null){
            System.out.println("Item not found");
        }
        return itemToSearch;
    }
    
    private void chooseLevel()
    {
        // Choosing a level (asking to the user through the terminal)
        Scanner reader = new Scanner(System.in);
        System.out.println("Please choose a level : Easy for peasant(0) \n Medium for Smarts (1) \n Hard for masterminds (2)");
        // Find the chosen level and alter the number of moves accorfing to the chosen one
        try {
            switch (reader.nextInt()) {
            case 0:
                limitOfMoves = 15;
                System.out.println("You've chosen the easy way to win ! - Number of moves : " + limitOfMoves);
                break;
            case 1:
                limitOfMoves = 13;
                System.out.println("You've chosen the medium level :)- Number of moves : " + limitOfMoves);
                break;
            case 2:
                limitOfMoves = 11;
                System.out.println("It's gonna be hard this way :@  - Number of moves : " + limitOfMoves);
                break;
            default:
                limitOfMoves = 15;
                System.out.println("Unkown command - Default level : Easy - Number of moves : " + limitOfMoves);
                break;
            }
        } catch(Exception e){
            limitOfMoves = 15;
            System.out.println("Unkown command - Default level : Easy - Number of moves : " + limitOfMoves);
        }
    }
    
    public boolean countMove()
    {
    numberOfMoves++;

        // Give some informations concerning the number of moves
        if (numberOfMoves < limitOfMoves) {
            System.out.println("Current number of moves : " + numberOfMoves);
            System.out.println("Moves left : " + (limitOfMoves - numberOfMoves));
            return false;
            // Ending the game if the number of moves is reached
        } else {
            System.out.println("You have reached the maximum number of moves");
            System.out.println("By the way, GAME OVER ! ");
            System.out.println();
            System.out.println();
            System.exit(0);
            return true;
    
    }
    
    }
    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    /**
     * @return the limitOfMoves
     */
    public int getLimitOfMoves() {
        return limitOfMoves;
    }
    
    /**
     * @param limitOfMoves the limitOfMoves to set
     */
    public void setLimitOfMoves(int lom) {
        limitOfMoves = lom;
    }
}
