/*
 * Copyright (c) 2007-2013 Sonatype, Inc. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package org.sonatype.security.realms.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.security.model.Configuration;

public abstract class AbstractConfigurationManager
    implements ConfigurationManager
{
    private final Logger logger = LoggerFactory.getLogger( getClass() );

    protected Logger getLogger()
    {
        return logger;
    }

    //

    private volatile EnhancedConfiguration configuration = null;

    // FIXME: Synchronized to avoid threads eating up cpu while re-building configuration.
    // FIXME: Really need to revisit how the configuration is created and rebuilt to avoid a lock here.

    public synchronized void clearCache()
    {
        configuration = null;
    }

    protected synchronized EnhancedConfiguration getConfiguration()
    {
        //Assign configuration to local variable first, as calls to clearCache
        //can null it out at any time
        EnhancedConfiguration config = configuration;
        if(config == null)
        {
            config = new EnhancedConfiguration(doGetConfiguration());
            configuration = config;
        }
        
        return config;
    }

    protected abstract Configuration doGetConfiguration();
}