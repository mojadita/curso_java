/* Name: Realm.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 23 mar. 2019 20:29:05
 * Project: ejbDataModel
 * Package: es.lcssl.java.j2ee.realms.ejbDataModel
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package es.lcssl.java.j2ee.realms.ejbDataModel;

import java.sql.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A {@link Realm} is a security domain.
 * 
 * <p>
 * {@link Realm}s have a {@link User} {@link Map} (actually a {@link TreeMap},
 * based on that they are {@link Comparable}) in which {@link Role}s
 * are assigned to {@link User}s, so security checks can be made to see if a
 * {@link User} is authorized to do some action in the application.
 * </p>
 * <p>
 * A {@link Realm} allows to get the {@link Set}{@code <R>} of roles, for each
 * registered {@link User} in the {@link Map} so you can inspect it. It also
 * allows you to get if a {@link User} is registered on the {@link Realm}.
 * </p>
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public class Realm<R extends Enum<?>> implements Iterable<Entry<User, Set<R>>> {

    long              m_id;
    String            m_name;
    Date              m_dateCreated;
    Map<User, Set<R>> m_roles = new TreeMap<User, Set<R>>();

    /**
     * @return the {@code Map<User,Set<R>>} {@code roles}.
     */
    public Set<R> getRoles( User user ) {
        return m_roles.get( user );
    }

    /**
     * @param roles the {@code Map<User,Set<R>>} {@code roles} to set
     */
    public void setRoles( Map<User, Set<R>> roles ) {
        m_roles = new TreeMap<User, Set<R>>( roles );
    }

    /**
     * Checks if {@link User} {@code user} is registered in this {@link Realm}.
     * 
     * @param  user the user to be checked.
     * @return      {@code true} if the user has at least one
     */
    public boolean isUserInRealm( User user ) {
        return m_roles.containsKey( user ) // must have at least one role.
                ? m_roles.get( user ).size() > 0
                : false;
    }

    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Entry<User, Set<R>>> iterator() {
        return m_roles.entrySet().iterator();
    }

    /**
     * @return the {@code long} {@code id}.
     */
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "realm" )
    public long getId() {
        return m_id;
    }

    /**
     * @param id the {@code long} {@code id} to set
     */
    public void setId( long id ) {
        m_id = id;
    }

    /**
     * @return the {@code String} {@code name}.
     */
    @Column( unique = true )
    public String getName() {
        return m_name;
    }

    /**
     * @param name the {@code String} {@code name} to set
     */
    public void setName( String name ) {
        m_name = name;
    }

    /**
     * @return the {@code Date} {@code dateCreated}.
     */
    @Temporal( TemporalType.TIMESTAMP )
    public Date getDateCreated() {
        return m_dateCreated;
    }

    /**
     * @param dateCreated the {@code Date} {@code dateCreated} to set
     */
    public void setDateCreated( Date dateCreated ) {
        m_dateCreated = dateCreated;
    }
}
