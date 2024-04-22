package uml.时序图;

import java.util.Scanner;

public class ProductUI {
    private ProductService productService = new ProductService();

    public void displayProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入商品ID：");
        String id = scanner.nextLine();
        Product product = productService.getProductById(id);
        if (product != null) {
            System.out.println("商品信息：");
            System.out.println("ID: " + product.getId());
            System.out.println("名称: " + product.getName());
            System.out.println("价格: " + product.getPrice());
        } else {
            System.out.println("未找到该商品。");
        }
    }

    public static void main(String[] args) {
        ProductUI ui = new ProductUI();
        ui.displayProduct();
    }
}
