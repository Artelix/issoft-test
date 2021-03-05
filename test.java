
public class test {
    public static void main(String[] args) {
        OrdersParser ordersParser = new OrdersParser("resources/orders.csv", "resources/products.csv", "resources/order_items.csv");
        OrderItem leader = ordersParser.getLeader("2021-01-21");
        System.out.println(leader.getName());
    }
}
