/* Name: User.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 23 mar. 2019 13:56:36
 * Project: ejbDataModel
 * Package: es.lcssl.java.j2ee.realms.ejbDataModel
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package es.lcssl.java.j2ee.realms.ejbDataModel;

import java.sql.Date;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.opensaml.saml.saml2.metadata.EmailAddress;

/**
 * This class implements a {@link User} for the realms database.
 * 
 * <p>
 * The idea is to implement a {@link Realm}, which maintains a domain of users
 * and their roles in respect to the Realm.
 * </p>
 * <p>
 * Users are {@link Comparable}, based on their user name.
 * </p>
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 * @see    Realm
 *
 */
@Entity
public class User implements Comparable<User> {

    private long    m_id;
    private String  m_name;
    private String  m_encryptedPassword;
    private String  m_displayName;
    private String  m_email;
    private boolean m_emailVerified;
    private Date    m_dateCreated;
    private Date    m_validUntil;


    /**
     * @return the {@code long} {@code id}.
     */
    @Id
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
     * @return the {@code String} {@code encryptedPassword}.
     */
    public String getEncryptedPassword() {
        return m_encryptedPassword;
    }


    /**
     * @param encryptedPassword the {@code String} {@code encryptedPassword} to
     *                          set
     */
    public void setEncryptedPassword( String encryptedPassword ) {
        m_encryptedPassword = encryptedPassword;
    }


    /**
     * @return the {@code String} {@code displayName}.
     */
    public String getDisplayName() {
        return m_displayName;
    }


    /**
     * @param displayName the {@code String} {@code displayName} to set
     */
    public void setDisplayName( String displayName ) {
        m_displayName = displayName;
    }

    /**
     * @return the {@code EmailAddress} {@code email}.
     */
    public String getEmail() {
        return m_email;
    }


    /**
     * @param email the {@code EmailAddress} {@code email} to set
     */
    public void setEmail( String email ) {
        m_email = email;
    }


    /**
     * @return the {@code boolean} {@code emailVerified}.
     */
    public boolean isEmailVerified() {
        return m_emailVerified;
    }


    /**
     * @param emailVerified the {@code boolean} {@code emailVerified} to set
     */
    public void setEmailVerified( boolean emailVerified ) {
        m_emailVerified = emailVerified;
    }


    /**
     * @return the {@code Date} {@code dateCreated}.
     */
    public Date getDateCreated() {
        return m_dateCreated;
    }


    /**
     * @param dateCreated the {@code Date} {@code dateCreated} to set
     */
    public void setDateCreated( Date dateCreated ) {
        m_dateCreated = dateCreated;
    }

    /**
     * Convenience function to set creation date based on
     * {@link System#currentTimeMillis()}.
     * 
     * @param millis time in milliseconds from the epoch of creation date.
     */
    public void setDateCreated( long millis ) {
        m_dateCreated = new Date( millis );
    }


    /**
     * @return the {@code Date} {@code validUntil}.
     */
    public Date getValidUntil() {
        return m_validUntil;
    }


    /**
     * @param validUntil the {@code Date} {@code validUntil} to set
     */
    public void setValidUntil( Date validUntil ) {
        m_validUntil = validUntil;
    }

    /**
     * Convenience function to set valid until based on
     * {@link System#currentTimeMillis()}.
     * 
     * @param millis time in milliseconds of the time at which this {@link User}
     *               is not valid anymore.
     */
    public void setValidUntil( long millis ) {
        m_validUntil = new Date( millis );
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo( User o ) {
        return m_name.compareToIgnoreCase( o.m_name );
    }

}
