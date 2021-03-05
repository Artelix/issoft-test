import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersParser {
    private String filePath;
    private OrderItemParser orderItemsParser;
    private HashMap<String, List<Order>> orders;

    public OrdersParser(String filePathOrders, String filePathProducts, String filePathOrderItems){
               this.orderItemsParser = new OrderItemParser(filePathOrderItems, filePathProducts);

        this.filePath =filePathOrders;
        orders = new HashMap<>();
        fillMap();
    }

    public OrderItem getLeader(String date){
        List<Order> currDate = orders.get(date);
        String productName = "";
        String productId = "";
        Double maxPrice = 0.0;
        for(Order order : currDate){
            for (OrderItem orderItem : order.getOrderItems()){
                if(orderItem.getPrice() > maxPrice){
                    maxPrice = orderItem.getPrice();
                    productName = orderItem.getName();
                    productId = orderItem.getProductId();
                }
            }
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setName(productName);
        orderItem.setProductId(productId);

        return orderItem;
    }

    private void fillMap(){
        CSVReader reader = new CSVReader(filePath);
        String[] lines = reader.readCSVFile();

        for(String s : lines){
            String[] columns = s.split(",");
            processLine(columns);
        }
    }

    private void processLine(String[] columns){
        String currDate = columns[1].substring(0, 10);

        if(!orders.containsKey(currDate)){
            orders.put(currDate, new ArrayList<>());
        }

        Order order = new Order();
        order.setId(columns[0]);
        order.setOrderItems(this.orderItemsParser.getOrderItems(order.getId()));

        orders.get(currDate).add(order);
    }
}
