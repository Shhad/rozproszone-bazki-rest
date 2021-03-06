package inz.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "favouriteproducts")
public class OrderProducts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "favouriteid", nullable = false)
    private Integer favouriteId;

    @Column(name = "productid", nullable = false)
    private Integer productId;

    public OrderProducts() {}

    public OrderProducts(Integer favouriteId, Integer productId) {
        this.favouriteId = favouriteId;
        this.productId = productId;
    }

    public Integer getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(Integer favouriteId) {
        this.favouriteId = favouriteId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
