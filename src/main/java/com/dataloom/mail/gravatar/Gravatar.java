package com.dataloom.mail.gravatar;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.dataloom.mail.requirements.ScribeRequirements;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.dataloom.mail.MailServiceClient;
import com.dataloom.mail.RenderableEmailRequest;
import com.dataloom.mail.templates.EmailTemplate;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.hazelcast.core.HazelcastInstance;

public class Gravatar {
    private final static int    DEFAULT_SIZE = 80;
    private final static String GRAVATAR_URL = "http://www.gravatar.com/avatar/";

    private int                 size         = DEFAULT_SIZE;
    
    @Inject
    private ScribeRequirements requirements;

    public void setSize( int sizeInPixels ) {
        if ( sizeInPixels >= 1 && sizeInPixels <= 512 ) {
            this.size = sizeInPixels;
        }
    }

    public URL getUrl( String email ) {
        String emailHash = DigestUtils.md5Hex( email.toLowerCase().trim() );
        String params = formatUrlParameters();
        try {
            URL url = new URL( GRAVATAR_URL + emailHash + params );
            return url;
        } catch ( MalformedURLException e ) {
            logErrorAndSendEmailReport( e );
        }
        return null;
    }

    private String formatUrlParameters() {
        List<String> params = new ArrayList<String>();
        if ( size != DEFAULT_SIZE )
            params.add( "s=" + size );
        params.add( "d=" + "https://www.kryptnostic.com/assets/default_avatar.png" );
        if ( params.isEmpty() ) {
            return "";
        } else {
            return "?" + StringUtils.join( params.iterator(), "&" );
        }
    }
    
    private void logErrorAndSendEmailReport( Exception ex ) {
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace( pw );
        
        System.err.println( sw.toString() );
        
        MailServiceClient mailService = new MailServiceClient( requirements.getEmailQueue() );
        RenderableEmailRequest emailRequest = new RenderableEmailRequest(
                Optional.of( EmailTemplate.getCourierEmailAddress() ),
                new String[] { "outage@kryptnostic.com" },
                Optional.absent(),
                Optional.absent(),
                EmailTemplate.INTERNAL_ERROR.getPath(),
                Optional.of( EmailTemplate.INTERNAL_ERROR.getSubject() ),
                Optional.of( ImmutableMap.of( "message", sw.toString() ) ),
                Optional.absent(),
                Optional.absent());
        mailService.spool( emailRequest );
        
    }
}
