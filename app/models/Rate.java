package models;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntegerelliJ IDEA.
 * User: ck870711
 * Date: 7/3/12
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "rates")
public class Rate extends GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long id;

    @Column(name = "Description")
    public String description;
    @Column(name = "EndPrice")
    public Integer endPrice;
    @Column(name = "ShareFactor")
    public Double shareFactor;
    @Column(name = "OperatorShare")
    public Integer operatorShare;
    @Column(name = "OtherCost")
    public Double otherCost;
    @Column(name = "MinShare")
    public Integer minShare;
    @Column(name = "MaxShare")
    public Integer maxShare;
    @Column(name = "ProductID")
    public Long productID;
    @Column(name = "InterfaceID")
    public Long interfaceID;
    @Column(name = "OperatorID")
    public Long operatorID;
    @Column(name = "CountFactor")
    public Integer countFactor;
    @Column(name = "DiffFactor")
    public Double diffFactor;
    @Column(name = "ValidFrom")
    public Date validFrom;
    @Column(name = "ValidTo")
    public Date validTo;
    @Column(name = "ClientID")
    public Long clientID;
    @Column(name = "PortalID")
    public Long portalID;
    @Column(name = "CPShare")
    public Double cpShare;
    @Column(name = "ChannelID")
    public Integer channelID;
    @Column(name = "SubscriptionID")
    public Integer subscriptionID;
    @Column(name = "Package")
    public Integer isPackage;
    @Column(name = "Currency")
    public String currency;
    @Column(name = "VAT")
    public Double vat;
    @Column(name = "PriceExVAT")
    public Double priceExVAT;
    @Column(name = "RDMLShare")
    public Double rdmlShare;

    @Transient
    public Domain domain;
    @Transient
    public Product product;
    @Transient
    public Portal portal;
    @Transient
    public Operator operator;
    @Transient
    public BillingInterface billingInterface;

    public boolean getIsPackage() {
        if (null != isPackage && 1 == isPackage) {
            return true;
        } else {
            return false;
        }
    }

    public static Rate findRate(long rateId) {
        Rate rate = Rate.findById(rateId);
        Domain domain = Domain.find("FROM Domain d WHERE d.domainID=?", rate.clientID).first();
        Product product = Product.find("FROM Product p WHERE p.id=?", rate.productID).first();
        Portal portal = Portal.find("FROM Portal p WHERE p.id=?", rate.portalID).first();
        Operator operator = Operator.find("FROM Operator p WHERE p.id=?", rate.operatorID).first();
        BillingInterface billingInterface = BillingInterface.find("FROM BillingInterface b WHERE b.interfaceID=?", rate.interfaceID).first();
        rate.domain = domain;
        rate.product = product;
        rate.portal = portal;
        rate.operator = operator;
        rate.billingInterface = billingInterface;
        return rate;
    }

    public static List<Rate> findRatesByPortalId(long portalId) {
        play.Logger.debug("PortalId " + portalId);
        List<Rate> rates = Rate.find("FROM Rate r WHERE r.portalID=? ORDER BY r.clientID", portalId).fetch();
        for (Rate rate : rates) {
            Domain domain = Domain.find("FROM Domain d WHERE d.domainID=?", rate.clientID).first();
            Product product = Product.find("FROM Product p WHERE p.id=?", rate.productID).first();
            Portal portal = Portal.find("FROM Portal p WHERE p.id=?", rate.portalID).first();
            Operator operator = Operator.find("FROM Operator p WHERE p.id=?", rate.operatorID).first();
            BillingInterface billingInterface = BillingInterface.find("FROM BillingInterface b WHERE b.interfaceID=?", rate.interfaceID).first();
            rate.domain = domain;
            rate.product = product;
            rate.portal = portal;
            rate.operator = operator;
            rate.billingInterface = billingInterface;
        }

        return rates;

    }

}
