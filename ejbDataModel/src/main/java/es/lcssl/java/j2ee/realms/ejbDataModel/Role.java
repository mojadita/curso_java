/* Name: Role.java
 * Author: Luis Colorado <luiscoloradourcola@gmail.com>
 * Date: 23 mar. 2019 20:15:36
 * Project: ejbDataModel
 * Package: es.lcssl.java.j2ee.realms.ejbDataModel
 * Copyright: (C) 2019 Luis Colorado. All rights reserved. */
package es.lcssl.java.j2ee.realms.ejbDataModel;


/**
 * This enumeration specifies the set of roles to 
 * @author Luis Colorado {@code <luiscoloradourcola@gmail.com>}
 *
 */
public enum Role {
    SUPER_USER("Super user"), USER_DB_EDITOR("User database editor"),
    REALM_DB_EDITOR("Realm database editor"),
    REALM_ROLES_EDITOR("Realm's roles editor"),
    ;

    private final String m_desc;

    /**
     * @param desc includes a description of the role for human consumption.
     */
    Role( String desc ) {
        m_desc = desc;
    }

    /**
     * @return the {@code String} {@code desc}.
     */
    public String getDesc() {
        return m_desc;
    }

}
