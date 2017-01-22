package com.dataloom.mail;

import com.hazelcast.core.IQueue;

public class MailServiceClient {
    private final IQueue<RenderableEmailRequest> emailRequestQueue;

    public MailServiceClient( IQueue<RenderableEmailRequest> emailRequestQueue ) {
        this.emailRequestQueue = emailRequestQueue;
    }

    public void spool( RenderableEmailRequest emailRequest ) {
        emailRequestQueue.add( emailRequest );
    }

}
