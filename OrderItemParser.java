import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderItemParser {
    private String filePath;
    private List<OrderItem> orderItems;
private ProductsParser productsParser;

    public OrderItemParser(String filePath, String filePathProducts){
        this.filePath=filePath;

        this.productsParser = new ProductsParser(filePathProducts);

        orderItems = new ArrayList<>();
        fillList();
    }

    public List<OrderItem> getOrderItems(String orderId){
        List<OrderItem> items = new ArrayList<>();
        for (OrderItem orderItem: orderItems){
            if(orderItem.getOrderId().equals(orderId)){
                Product product = this.productsParser.getProduct(orderItem.getProductId());
                if(product!=null){
                    orderItem.setName(product.getName());
                    orderItem.setProductId(product.getProductId());
                    orderItem.setPrice(product.getPrice() * orderItem.getQuantity());
                    items.add(orderItem);
                }
            }
        }

        return items;
    }

    private void fillList(){
        CSVReader reader = new CSVReader(filePath);
        String[] lines = reader.readCSVFile();
        for(String s : lines){
            String[] columns = s.split(",");

            String orderId = columns[0];
            String productId = columns[1];
            Integer quantity = Integer.parseInt(columns[2]);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(productId);
            orderItem.setQuantity(quantity);

            orderItems.add(orderItem);
        }
    }
}
