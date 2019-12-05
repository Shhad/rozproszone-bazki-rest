package inz.repository;

import inz.model.Order;
import inz.model.Product;

public interface OrderProductsRepositoryCustom {

    void deleteProduct(Product product, Order order);
    void deleteProduct(Integer productid, Integer favouriteid);
    void addProduct(Product product, Order order);
    void addProduct(String productid, String favouriteid);
}
