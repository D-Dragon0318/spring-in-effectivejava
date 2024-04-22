package uml.时序图;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public Product getProductById(String id) {
        return productDAO.getProductById(id);
    }
}
