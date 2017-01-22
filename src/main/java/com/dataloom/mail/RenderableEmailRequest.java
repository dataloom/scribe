package com.dataloom.mail;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import jodd.mail.att.ByteArrayAttachment;

public class RenderableEmailRequest extends EmailRequest {
    private final Optional<String> subject;
    private final String           templatePath;
    private final Optional<Object> templateObjs;
    private final Optional<ByteArrayAttachment[]> byteArrayAttachment;
    private final Optional<String[]> attachmentPath;

    public RenderableEmailRequest(
            Optional<String> from,
            String[] to,
            Optional<String[]> cc,
            Optional<String[]> bcc,
            String templatePath,
            Optional<String> subject,
            Optional<Object> templateObjs,
            Optional<ByteArrayAttachment[]> byteArrayAttachment,
            Optional<String[]> attachmentPath ) {
        super( from, to, cc, bcc );
        this.subject = subject;
        this.templatePath = templatePath;
        Preconditions.checkArgument( StringUtils.isNotBlank( this.templatePath ) );
        this.templateObjs = templateObjs;
        this.byteArrayAttachment = byteArrayAttachment;
        this.attachmentPath = attachmentPath;
    }

    public Optional<String> getSubject() {
        return subject;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public Optional<Object> getTemplateObjs() {
        return templateObjs;
    }
    
    public Optional<String[]> getAttachmentPaths() {
        return attachmentPath;
    }
    
    public Optional<ByteArrayAttachment[]> getByteArrayAttachment() {
        return byteArrayAttachment;
    }

    public static RenderableEmailRequest fromEmailRequest(
            String subject,
            String templatePath,
            Optional<Object> templateObjs,
            Optional<String[]> attachmentPaths,
            Optional<ByteArrayAttachment[]> byteArrayAttachment,
            EmailRequest request ) {
        return new RenderableEmailRequest(
                request.getFrom(),
                request.getTo(),
                request.getCc(),
                request.getBcc(),
                templatePath,
                Optional.of( subject ),
                templateObjs,
                byteArrayAttachment,
                attachmentPaths);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ( ( subject == null ) ? 0 : subject.hashCode() );
        result = prime * result + ( ( templatePath == null ) ? 0 : templatePath.hashCode() );
        result = prime * result + ( ( templateObjs == null ) ? 0 : templateObjs.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) return true;
        if ( !super.equals( obj ) ) return false;
        if ( !( obj instanceof RenderableEmailRequest ) ) return false;
        RenderableEmailRequest other = (RenderableEmailRequest) obj;
        if ( subject == null ) {
            if ( other.subject != null ) return false;
        } else if ( !subject.equals( other.subject ) ) return false;
        if ( templatePath == null ) {
            if ( other.templatePath != null ) return false;
        } else if ( !templatePath.equals( other.templatePath ) ) return false;
        if ( templateObjs == null ) {
            if ( other.templateObjs != null ) return false;
        } else if ( !templateObjs.equals( other.templateObjs ) ) return false;
        return true;
    }

}