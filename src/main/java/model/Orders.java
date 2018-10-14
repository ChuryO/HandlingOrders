package model;

import javax.persistence.*;

/**
 * this class with Model orders
 */
@Entity
@Table
public class Orders {
    public Orders() {
    }

    public Orders(String clientId, long requestId, String name, int quantity, double price) {
        this.clientId = clientId;
        this.requestId = requestId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 6)
    private String clientId;

    @Column
    private Long requestId;

    @Column
    private String name;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
