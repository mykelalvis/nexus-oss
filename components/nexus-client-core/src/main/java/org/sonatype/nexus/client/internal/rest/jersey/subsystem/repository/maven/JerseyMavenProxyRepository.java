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
package org.sonatype.nexus.client.internal.rest.jersey.subsystem.repository.maven;

import org.sonatype.nexus.client.core.subsystem.repository.maven.MavenProxyRepository;
import org.sonatype.nexus.client.internal.rest.jersey.subsystem.repository.JerseyProxyRepository;
import org.sonatype.nexus.client.rest.jersey.JerseyNexusClient;
import org.sonatype.nexus.rest.model.RepositoryProxyResource;

/**
 * Jersey based {@link MavenProxyRepository} implementation.
 *
 * @since 2.3
 */
public class JerseyMavenProxyRepository
    extends JerseyProxyRepository<MavenProxyRepository>
    implements MavenProxyRepository
{

    static final String PROVIDER = "maven2";

    public JerseyMavenProxyRepository( final JerseyNexusClient nexusClient, final String id )
    {
        super( nexusClient, id );
    }

    public JerseyMavenProxyRepository( final JerseyNexusClient nexusClient, final RepositoryProxyResource settings )
    {
        super( nexusClient, settings );
    }

    @Override
    protected RepositoryProxyResource createSettings()
    {
        final RepositoryProxyResource settings = super.createSettings();

        settings.setProvider( PROVIDER );
        settings.setIndexable( true );
        settings.setRepoPolicy( "RELEASE" );

        return settings;
    }

    private MavenProxyRepository me()
    {
        return this;
    }

    @Override
    public MavenProxyRepository withArtifactMaxAge( final int minutes )
    {
        settings().setArtifactMaxAge( minutes );
        return me();
    }

    @Override
    public MavenProxyRepository withMetadataMaxAge( final int minutes )
    {
        settings().setMetadataMaxAge( minutes );
        return me();
    }

    @Override
    public MavenProxyRepository downloadRemoteIndexes()
    {
        settings().setDownloadRemoteIndexes( true );
        return me();
    }

    @Override
    public MavenProxyRepository doNotDownloadRemoteIndexes()
    {
        settings().setDownloadRemoteIndexes( false );
        return me();
    }

    @Override
    public int artifactMaxAge()
    {
        return settings().getArtifactMaxAge();
    }

    @Override
    public int metadataMaxAge()
    {
        return settings().getMetadataMaxAge();
    }

}
