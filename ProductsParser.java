import java.util.ArrayList;
import java.util.List;

public class ProductsParser {
    private String filePath;
    private List<Product> products;

    public ProductsParser(String filePath){
        this.filePath=filePath;
        products = new ArrayList<>();
        fillList();
    }

    public Product getProduct(String productId) {
        for (Product product: this.products){
            if(product.getProductId().equals(productId)){
               return product;
            }
        }

        return null;
    }

    private void fillList(){
        CSVReader reader = new CSVReader(filePath);
        String[] lines = reader.readCSVFile();
        for(String s : lines){
            String[] columns = s.split(",");

            String productId = columns[0];
            String name = columns[1];
            Double price = Double.parseDouble(columns[2]);

            Product product = new Product();
            product.setProductId(productId);
            product.setName(name);
            product.setPrice(price);

            products.add(product);
        }
    }
}
