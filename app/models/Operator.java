package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: ck870711
 * Date: 7/6/12
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Operator")
public class Operator extends Model {

    @Column(name = "external_id")
    Long externalID;
    @Column(name = "name")
    String name;
    @Column(name = "max_charge")
    Long maxCharge;
    @Column(name = "max_volume")
    Long maxVolume;
    @Column(name = "max_duration")
    Long maxDuration;
}
