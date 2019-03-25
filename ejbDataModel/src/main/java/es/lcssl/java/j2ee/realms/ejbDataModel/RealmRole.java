/* Name: RealmRole.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 23 mar. 2019 21:37:03
 * Project: ejbDataModel
 * Package: es.lcssl.java.j2ee.realms.ejbDataModel
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package es.lcssl.java.j2ee.realms.ejbDataModel;

import javax.persistence.Entity;

/**
 * This class extends {@link Realm}{@code <Role>} and is annotated with
 * {@code @Entity} to make Hibernate to create a table of realms.
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
@Entity
public class RealmRole extends Realm<Role> {
}
