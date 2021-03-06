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
package org.sonatype.nexus.proxy.mirror;

import java.util.List;

import org.sonatype.nexus.proxy.repository.Mirror;

public interface DownloadMirrorSelector
{

    /**
     * Returns possibly empty list of available urls.
     */
    List<Mirror> getMirrors();

    /**
     * Requested item was successfully downloaded from specified mirror url.
     */
    void feedbackSuccess( Mirror mirror );

    /**
     * There was a problem (like IOException or ItemNotFound) retrieving requested item from specified mirror url.
     * 
     * @throws IllegalStateException if there is no selected mirror.
     */
    void feedbackFailure( Mirror mirror );

    /**
     * Updates mirror statistics and closes this mirror selector.
     */
    void close();

}
