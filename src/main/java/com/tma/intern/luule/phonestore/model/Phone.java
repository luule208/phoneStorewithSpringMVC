package com.tma.intern.luule.phonestore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "phones")
public class Phone implements Serializable {

    private static final long serialVersionUID = -4532690640929635881L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;


    @Size(max = 50)
    @Column(name = "name")
    public String name;

    @Size(max = 250)
    @Column(name = "description")
    public String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_supplier", nullable = false)
    @JsonBackReference
    public Supplier supplier;

    @ManyToMany(cascade = {
            CascadeType.REMOVE
    }, mappedBy = "phones")
    @JsonIgnore
    private Set<Bill> phoneOders = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<Bill> getPhoneOders() {
        return phoneOders;
    }

    public void setPhoneOders(Set<Bill> phoneOders) {
        this.phoneOders = phoneOders;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", supplier=" + supplier +
                '}';
    }
}
