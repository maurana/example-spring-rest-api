package com.crud.model;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import com.crud.enums.Gender;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
@Table(name = "person")
@Entity(name = "Person")
public class Person implements Serializable {
    private static final long serialVersionUID = 9178661439383356177L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String p_id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "p_n_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Nationality p_n_id;

    @Column(nullable = true, updatable = true)
    private String p_name;

    @Column(nullable = true, updatable = true)
    private Integer p_age;

    @Column(unique = true, nullable = true, updatable = true)
    private String p_email;

    @Column(nullable = true, updatable = true)
    private Date p_birthday;

    @Column(nullable = true, updatable = true)
    private BigDecimal p_income;

    @Enumerated(EnumType.STRING)
    private Gender p_gender;

    @Column(nullable = true, updatable = true)
    private Boolean p_status;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime p_created;

    @Column(nullable = true, updatable = true)
    @UpdateTimestamp
    private LocalDateTime p_updated;
}
