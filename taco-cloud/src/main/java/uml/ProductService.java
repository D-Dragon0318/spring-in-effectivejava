package uml;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public Product getProductById(String id) {
        return productDAO.getProductById(id);
    }
}
