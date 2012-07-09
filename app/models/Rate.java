package models;

import play.db.jpa.GenericModel;

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
    public String description = "";
    @Column(name = "EndPrice")
    public Integer endPrice = 0;
    @Column(name = "ShareFactor")
    public Double shareFactor = 0.1;
    @Column(name = "OperatorShare")
    public Integer operatorShare = 0;
    @Column(name = "OtherCost")
    public Double otherCost = 0.0;
    @Column(name = "MinShare")
    public Integer minShare = 0;
    @Column(name = "MaxShare")
    public Integer maxShare = 999999;
    @Column(name = "ProductID")
    public Long productID;
    @Column(name = "InterfaceID")
    public Long interfaceID;
    @Column(name = "OperatorID")
    public Long operatorID;
    @Column(name = "CountFactor")
    public Integer countFactor = 0;
    @Column(name = "DiffFactor")
    public Double diffFactor = 0.0;
    @Column(name = "ValidFrom")
    public Date validFrom;
    @Column(name = "ValidTo")
    public Date validTo;
    @Column(name = "ClientID")
    public Long clientID;
    @Column(name = "PortalID")
    public Long portalID;
    @Column(name = "CPShare")
    public Double cpShare = 0.0;
    @Column(name = "ChannelID")
    public Integer channelID = 0;
    @Column(name = "SubscriptionID")
    public Integer subscriptionID = 0;
    @Column(name = "Package")
    public Integer isPackage = 0;
    @Column(name = "Currency")
    public String currency = "EU";
    @Column(name = "VAT")
    public Double vat = 25.0;
    @Column(name = "PriceExVAT")
    public Double priceExVAT = 0.0;
    @Column(name = "RDMLShare")
    public Double rdmlShare = 0.0;

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
