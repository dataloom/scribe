package com.dataloom.mail;

import com.hazelcast.core.IQueue;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public class MailServiceClientTest {
    private static RenderableEmailRequest request = RenderableEmailRequestTest.sampleData();
    private static final IQueue<RenderableEmailRequest> emailRequests = Mockito.mock( IQRER.class );
    private static final MailServiceClient client = new MailServiceClient( emailRequests );

    @Test
    public void testSpool() {
        client.spool( request );
        Mockito.verify( emailRequests, Mockito.atLeastOnce() ).add( request );
    }
    static interface IQRER extends IQueue<RenderableEmailRequest> {

    }
}
