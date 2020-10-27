package no.olai;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {

    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>(); // Linked -> alfabetisk?
    }

    public int addStock (StockItem item) {
        if (item != null) {
            // Check if already have quantities of this item
            StockItem inStock = list.getOrDefault(item.getName(), item); // Trenger ikke sjekk for inStock != null
            // If there are already stocks on this item, adjust the quantity
            if (inStock != item) {
                item.adjustStock(inStock.availableQuantity());
            }

            list.put(item.getName(), item);
            return item.availableQuantity();
        }
        return 0;
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity > 0)) {
            return inStock.finializeStock(quantity);
        }
        return 0;

//        StockItem inStock = list.getOrDefault(item, null);
//
//        if ((inStock != null) && (inStock.availableQuantity() >= quantity) && (quantity > 0)) {
//            inStock.adjustStock(-quantity);
//            return quantity; // How many items we've taken out of stock
//        }
//        return 0; // Nothing taken out of stock..
    }

    public int reserveStock(String item, int quantity) {
        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity > 0)) {
            return inStock.reserveStock(quantity);
        }
        return 0;
    }

    public int unreserveStock(String item, int quantity) {
        StockItem inStock = list.get(item);

        if ((inStock != null) && (quantity > 0)) {
            return inStock.unreserveStock(quantity);
        }
        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, Double> priceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list); // Read-only access to internal maps
    }


    @Override
    public String toString() {
        // Complete stocklist
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String,StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.availableQuantity();
            s += stockItem + ". There are " + stockItem.availableQuantity() + " in stock. Value of items: ";
            s += String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }
        return s + "Total stock value " + totalCost;
    }
}
