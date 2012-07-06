package models;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ck870711
 * Date: 7/3/12
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Domains")
public class Domain extends GenericModel {

    @Id
    @Column(name = "domain_id")
    public long domainID;
    @Column(name = "category_id")
    public long categoryID;
    @Column(name = "name")
    public String name;
}
