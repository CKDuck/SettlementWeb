package models;

import org.omg.CORBA.PUBLIC_MEMBER;
import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ck870711
 * Date: 7/3/12
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Portals")
public class Portal extends Model {

    @Column(name = "Name")
    public String name;
    @Column(name = "DomainID")
    public Integer domainID;
    @Column(name = "CatDomainID")
    public Integer catDomainID;
    @Column(name = "Currency")
    public String currency;
    @Column(name = "ShortName")
    public String shortName;
}
