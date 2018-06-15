package com.tma.intern.luule.phonestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bills")
public class Bill implements Serializable {


    private static final long serialVersionUID = 354116928804183399L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "price")
    private Long price;

    @NotNull
    @Size(max = 100)
    @Column(name = "username")
    private String username;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "phone_bill",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "phone_id", referencedColumnName = "id")})
    private Set<Phone> phones = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", price=" + price +
                ", username='" + username + '\'' +
                ", phones=" + phones +
                '}';
    }
}
