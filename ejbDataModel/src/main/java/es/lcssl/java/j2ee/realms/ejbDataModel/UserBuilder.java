/*      Name: UserBuilder.java
 *    Author: Luis Colorado <luiscoloradourcola@gmail.com>
 *      Date: 23 mar. 2019 23:11:47
 *   Project: ejbDataModel
 *   Package: es.lcssl.java.j2ee.realms.ejbDataModel
 * Copyright: (C) 2019 Luis Colorado.  All rights reserved.
 */
package es.lcssl.java.j2ee.realms.ejbDataModel;

import java.sql.Date;

/**
 * A chainable builder for {@link User} instances.
 * 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public class UserBuilder {
    
    private User m_target = new User();
    
    public UserBuilder setName(String name) {
        m_target.setName( name );
        return this;
    }
    
    public UserBuilder setId(long id) {
        m_target.setId( id );
        return this;
    }
    
    public UserBuilder setEmail( String email ) {
        m_target.setEmail( email );
        return this;
    }
    
    public UserBuilder setEncryptedPassword( String encrypted ) {
        m_target.setEncryptedPassword( encrypted );
        return this;
    }
    
    public UserBuilder setDisplayName( String disp_name ) {
        m_target.setDisplayName( disp_name );
        return this;
    }
    
    public UserBuilder setEmailVerified( boolean verify ) {
        m_target.setEmailVerified( verify );
        return this;
    }
    
    public UserBuilder setDateCreated( Date date_created ) {
        m_target.setDateCreated( date_created );
        return this;
    }
    
    public UserBuilder setDateCreated( long millis ) {
        m_target.setDateCreated( millis );
        return this;
    }
    
    public UserBuilder setValidUntil( Date valid_until ) {
        m_target.setValidUntil( valid_until );
        return this;
    }
    
    public UserBuilder setValidUntil( long millis ) {
        m_target.setValidUntil( millis );
        return this;
    }

    public User build() {
        return m_target;
    }
}
