package models;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ck870711
 * Date: 7/3/12
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "BillingInterface")
public class BillingInterface extends GenericModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "InterfaceID")
    public long interfaceID;

    @Column(name = "ProcedureName")
    public String procedureName;
    @Column(name = "Description")
    public String description;

}
