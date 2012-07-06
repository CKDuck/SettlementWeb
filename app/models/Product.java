package models;

import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ck870711
 * Date: 7/3/12
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Products")
public class Product extends Model {

    @Column(name = "Name")
    public String name;
    @Column(name = "Description")
    public String description;
}
