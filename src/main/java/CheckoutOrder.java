import java.util.HashMap;

public class CheckoutOrder {

    private HashMap<String, Item> inventory = new HashMap<String, Item>();

    public void addItemToInventory( Item item ) {
        inventory.put( item.getItemName(), item );
    }

    public HashMap<String, Item> getInventory() {
        return inventory;
    }

    public void setInventory( HashMap<String, Item> inventory ) {
        this.inventory = inventory;
    }
}
