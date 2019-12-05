package inz.repository.implementation;

import inz.model.Order;
import inz.model.OrderProducts;
import inz.model.Product;
import inz.repository.OrderProductsRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class OrderProductsRepositoryCustomImplementation implements OrderProductsRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public void deleteProduct(Product product, Order order) {
        Query query = entityManager.createNativeQuery("DELETE * FROM inzynierka.favouriteproducts WHERE favouriteid = ? AND productid = ?", OrderProducts.class);
        query.setParameter(1, order.getFavouriteId());
        query.setParameter(2, product.getProductId());
        query.executeUpdate();
    }
    
    @Override
    public void deleteProduct(Integer productid, Integer favouriteid) {
        Query query = entityManager.createNativeQuery("DELETE * FROM inzynierka.favouriteproducts WHERE favouriteid = ? AND productid = ?", OrderProducts.class);
        query.setParameter(1, favouriteid);
        query.setParameter(2, productid);
        query.executeUpdate();
    }

    @Override
    public void addProduct(Product product, Order order) {
        Query query = entityManager.createNativeQuery("INSERT INTO inzynierka.favouriteproducts (favouriteid, productid) VALUES (?,?)", Order.class);
        query.setParameter(1, order.getFavouriteId());
        query.setParameter(2, product.getProductId());
        query.executeUpdate();
    }

    @Override
    public void addProduct(String productid, String favouriteid) {
        Query query = entityManager.createNativeQuery("INSERT INTO inzynierka.favouriteproducts (favouriteid, productid) VALUES (?,?)", Order.class);
        query.setParameter(1, favouriteid);
        query.setParameter(2, productid);
        query.executeUpdate();
    }
}
