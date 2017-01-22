package com.dataloom.mail.requirements;

import com.dataloom.mail.RenderableEmailRequest;
import com.hazelcast.core.IQueue;

import java.util.function.Supplier;

/**
 * This is used to express dependencies of the mail service.
 * @author Matthew Tamayo-Rios &lt;matthew@kryptnostic.com&gt;
 */
public interface EmailQueueRequirement {
    public IQueue<RenderableEmailRequest> getEmailQueue();
}
