package com.crud.model;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.GenericGenerator;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
@Table(name = "nationality")
@Entity(name = "Nationality")
public class Nationality implements Serializable {
    private static final long serialVersionUID = 9178661439383356177L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String n_id;

    @Column(unique = true, nullable = false, updatable = false)
    private String n_name;

    @Column(nullable = true, updatable = true)
    private String n_desc;

    // @OneToMany(mappedBy = "nationality", cascade = CascadeType.ALL)
    // private List<Person> persons;
}