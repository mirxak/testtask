package ru.task.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;
import ru.task.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mirak on 11.03.17.
 */
@Entity
@Table(name = Customer.TABLE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer extends BaseEntity {

    public static final String CATEGORY = "customers";
    public static final String TABLE = "customer";

    @Column(name = "name_fio")
    @NotEmpty
    private String nameFio;

    public String getNameFio() {
        return nameFio;
    }

    public void setNameFio(String nameFio) {
        this.nameFio = nameFio;
    }

    @Column(name = "balance")
    private Float balance;

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    // 0 - не заблокирован, 1 - заблокирован
    @Column(name = "status")
    private Integer status;

    public Integer getStatus() {
        return (status == null) ? 0 : status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "login")
    @NotEmpty
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password")
    @NotEmpty
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private Set<PartnerMapping> partnerMappings = new HashSet<PartnerMapping>();

    public Set<PartnerMapping> getPartnerMappings() {
        return partnerMappings;
    }

    public void setPartnerMappings(Set<PartnerMapping> partnerMappings) {
        this.partnerMappings = partnerMappings;
    }

}
