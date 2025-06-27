import com.app.ProductSingleton;

public class TestDriver {
    public static void main(String[] args){
        ProductSingleton p1 = ProductSingleton.getInstance();
//        p1.sayHello();
        p1.setProduct("P004", "Switch2", 19800);
//        p1.showProduct();
//        p1.insertProduct("POO5", "Switch1", 12000);

        String p_name = p1.getProductName("POO5");
        System.out.printf("Product name: %s", p_name);
    }
}
