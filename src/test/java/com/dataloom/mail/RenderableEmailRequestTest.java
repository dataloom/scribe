package com.dataloom.mail;

import java.util.Map;

import org.junit.Test;

import com.dataloom.serializer.BaseJacksonSerializationTest;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;

import jodd.mail.att.ByteArrayAttachment;

public class RenderableEmailRequestTest extends BaseJacksonSerializationTest<RenderableEmailRequest> {

    @Override
    public RenderableEmailRequest getSampleData() {
        return sampleData();
    }

    public static RenderableEmailRequest sampleData() {
        Map<String, String> map = Maps.newHashMapWithExpectedSize( 2 );
        map.put( "key1", "value1" );
        map.put( "key2", "value2" );
        return new RenderableEmailRequest(
                Optional.absent(),
                new String[] { "yao@kryptnostic.com" },
                Optional.absent(),
                Optional.absent(),
                "OutageTemplate",
                Optional.of( "Outage" ),
                Optional.of( map ),
                Optional.of( new ByteArrayAttachment[] {} ),
                Optional.absent() );
    }

    @Override
    protected Class<RenderableEmailRequest> getClazz() {
        return RenderableEmailRequest.class;
    }

    @Test(
        expected = IllegalStateException.class )
    public void testNoTo() {
        new RenderableEmailRequest(
                Optional.<String> absent(),
                new String[] {},
                Optional.<String[]> absent(),
                Optional.<String[]> absent(),
                "TemplateName",
                Optional.<String> absent(),
                Optional.<Object> absent(),
                Optional.of( new ByteArrayAttachment[] {} ),
                Optional.absent() );
    }

    @Test(
        expected = NullPointerException.class )
    public void testNullTo() {
        new RenderableEmailRequest(
                Optional.<String> absent(),
                null,
                Optional.<String[]> absent(),
                Optional.<String[]> absent(),
                "",
                Optional.<String> absent(),
                Optional.<Object> absent(),
                Optional.of( new ByteArrayAttachment[] {} ),
                Optional.absent() );
    }

}