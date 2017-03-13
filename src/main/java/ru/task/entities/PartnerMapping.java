package ru.task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;
import ru.task.entities.base.BaseEntity;

import javax.persistence.*;

/**
 * Created by mirak on 11.03.17.
 */
@Entity
@Table(name = PartnerMapping.TABLE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartnerMapping extends BaseEntity {
    public static final String CATEGORY = "partner-mappings";
    public static final String TABLE = "partner_mapping";


    //partner_mapping (id SERIAL, partner_id text, partner_account_id text, name_fio text, user_img_url text);

    @Column(name = "partner_id")
    @NotEmpty
    private String partnerId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Column(name = "partner_account_id")
    @NotEmpty
    private String partnerAccountId;

    public String getPartnerAccountId() {
        return partnerAccountId;
    }

    public void setPartnerAccountId(String partnerAccountId) {
        this.partnerAccountId = partnerAccountId;
    }

    @Column(name = "name_fio")
    private String nameFio;

    public String getNameFio() {
        return nameFio;
    }

    public void setNameFio(String nameFio) {
        this.nameFio = nameFio;
    }

    @Column(name = "user_img_url")
    private String userImgUrl;

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
