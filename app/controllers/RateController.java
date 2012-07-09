package controllers;

import models.*;
import org.apache.commons.lang.StringUtils;
import play.Logger;
import play.data.validation.Valid;
import play.mvc.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ck870711
 * Date: 7/3/12
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class RateController extends Controller {

    public static void index() {
        render();
    }

    public static void selectPortal(@Valid long portalId) {
        showRatesByPortalId(portalId);
    }

    public static void showPortals() {
        List<Portal> portals = Portal.findAll();
        render(portals);
    }

    public static void showRates() {
        List<Rate> rates = Rate.findAll();
        render(rates);
    }

    public static void showRatesByPortalId(long id) {
        List<Rate> rates = Rate.findRatesByPortalId(id);
        long portalId = id;
        render(rates, portalId);
    }

    public static void showRate(long id) {
        Rate rate = Rate.findRate(id);
        List<Domain> domains = Domain.find("FROM Domain d ORDER BY d.name").fetch();
        List<Portal> portals = Portal.find("FROM Portal p ORDER BY p.name").fetch();
        List<Product> products = Product.find("FROM Product p ORDER BY p.name").fetch();
        List<Operator> operators = Operator.find("FROM Operator p ORDER BY p.name").fetch();
        List<BillingInterface> interfaces = BillingInterface.find("FROM BillingInterface bi ORDER BY procedureName").fetch();
        render(rate, domains, portals, products, operators, interfaces);
    }

    public static void createRate(long portalId) {
        Rate rate = new Rate();
        rate.portalID = portalId;
        Portal portal = Portal.findById(portalId);
        rate.portal = portal;
        List<Domain> domains = Domain.find("FROM Domain d ORDER BY d.name").fetch();
        List<Portal> portals = Portal.find("FROM Portal p ORDER BY p.name").fetch();
        List<Product> products = Product.find("FROM Product p ORDER BY p.name").fetch();
        List<Operator> operators = Operator.find("FROM Operator p ORDER BY p.name").fetch();
        List<BillingInterface> interfaces = BillingInterface.find("FROM BillingInterface bi ORDER BY procedureName").fetch();
        render(rate, domains, portals, products, operators, interfaces);
    }

    public static void insertRate(Rate rate, @Valid String validFrom, @Valid String validTo, @Valid String checkPackage) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (null != validFrom) {
            rate.validFrom = df.parse(validFrom);
        }
        if (null != validTo) {
            rate.validTo = df.parse(validTo);
        }

        if (null != checkPackage && StringUtils.equalsIgnoreCase(checkPackage, "on")) {
            rate.isPackage = 1;
        } else {
            rate.isPackage = 0;
        }

        rate.save();

        showRatesByPortalId(rate.portalID);
    }

    public static void updateRate(Rate rate, @Valid String validFrom, @Valid String validTo, @Valid String checkPackage) throws Exception {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (null != validFrom) {
            rate.validFrom = df.parse(validFrom);
        }
        if (null != validTo) {
            rate.validTo = df.parse(validTo);
        }

        if (null != checkPackage && StringUtils.equalsIgnoreCase(checkPackage, "on")) {
            rate.isPackage = 1;
        } else {
            rate.isPackage = 0;
        }

        rate.save();

        showRatesByPortalId(rate.portalID);
    }

    public static void deleteRate(long rateId) {
        Logger.debug("Delete rate : " + rateId);
        Rate.findById(rateId)._delete();
    }
}
