package com.tma.intern.luule.phonestore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.codehaus.jackson.map.Serializers;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {


    private static final long serialVersionUID = -3070715424194980331L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;


    @Size(max = 100)
    @Column(name = "name")
    public String name;

    @OneToMany(cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
            , mappedBy = "supplier"
    )
    @JsonManagedReference
    private Set<Phone> phones = new HashSet<>();

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

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones=" + phones +
                '}';
    }
}
