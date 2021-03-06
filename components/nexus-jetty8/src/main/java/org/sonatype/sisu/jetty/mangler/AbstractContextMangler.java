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
package org.sonatype.sisu.jetty.mangler;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;

/**
 * Abstract base class for manglers working with contexts.
 * 
 * @author cstamas
 */
public abstract class AbstractContextMangler
{
    private final String contextPath;

    protected AbstractContextMangler( final String contextPath )
    {
        this.contextPath = contextPath;
    }

    protected ContextHandler getContext( final Server server )
    {
        Handler[] handlers = server.getHandlers();
        if ( handlers == null )
        {
            handlers = new Handler[] { server.getHandler() };
        }

        return getContextHandlerOnPath( contextPath, handlers );
    }

    // ==

    protected ContextHandler getContextHandlerOnPath( final String contextPath, final Handler[] handlers )
    {
        for ( int i = 0; i < handlers.length; i++ )
        {
            if ( handlers[i] instanceof ContextHandler )
            {
                ContextHandler ctx = (ContextHandler) handlers[i];

                if ( contextPath.equals( ctx.getContextPath() ) )
                {
                    return ctx;
                }
            }
            else if ( handlers[i] instanceof HandlerCollection )
            {
                Handler[] handlerList = ( (HandlerCollection) handlers[i] ).getHandlers();

                return getContextHandlerOnPath( contextPath, handlerList );
            }
        }

        return null;
    }
}
