/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2007-2013 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.security.usermanagement;

import java.util.Set;

import org.sonatype.configuration.validation.InvalidConfigurationException;

/**
 * Extends the UserManager interface to allow a UserManager to add roles to users from other UserManagers. For example,
 * a User might come from a JDBC UserManager, but has additional roles mapped in XML.
 * 
 * @author Brian Demers
 */
public interface RoleMappingUserManager
    extends UserManager
{

    /**
     * Returns a list of roles for a user.
     * 
     * @param userId
     * @param userSource
     * @return
     * @throws UserNotFoundException
     */
    Set<RoleIdentifier> getUsersRoles( String userId, String userSource )
        throws UserNotFoundException;

    /**
     * Sets a users roles.
     * 
     * @param userId
     * @param userSource
     * @param roleIdentifiers
     * @throws UserNotFoundException
     * @throws InvalidConfigurationException
     */
    void setUsersRoles( String userId, String userSource, Set<RoleIdentifier> roleIdentifiers )
        throws UserNotFoundException, InvalidConfigurationException;

}
