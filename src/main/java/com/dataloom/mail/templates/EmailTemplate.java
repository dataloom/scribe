package com.dataloom.mail.templates;

public enum EmailTemplate {

    KODEX_INVITATION( "Invitation to Kodex", "mail/templates/kodex/invitation.mustache" ),
    KODEX_EMAIL_CONFIRMATION( "Kodex registration confirmation", "mail/templates/kodex/confirmation.mustache" ),
    KODEX_DIGEST( "Kodex messages digest", "mail/templates/kodex/digest.mustache" ),
    KODEX_REGISTRATION_DOMAIN_NOTIFICATION(
            "New User Registered for Kodex",
            "mail/templates/kodex/registrationNotification.mustache" ),
    KODEX_DAILY_REPORT( "Daily Kodex Report", "mail/templates/kodex/dailyReport.mustache" ),
    KODEX_THANKS_FOR_APPLYING( "Thanks for Applying Kodex", "mail/templates/kodex/ThanksForApplying.mustache" ),

    SERVICE_OUTAGE( "Service Outage", "mail/templates/shared/outage.mustache" ),

    INTERNAL_ERROR( "Internal Error", "mail/templates/shared/error.mustache" ),

    DEFAULT( "Kryptnostic Info", "mail/templates/shared/default.mustache" );

    private final String        path;
    private final String        subject;
    private static final String KRYPTNOSTIC_COURIER_EMAIL_ADDRESS = "Kryptnostic Courier <courier@kodex.im>";

    EmailTemplate( String subject, String path ) {
        this.subject = subject;
        this.path = path;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getPath() {
        return this.path;
    }

    public static String getCourierEmailAddress() {
        return KRYPTNOSTIC_COURIER_EMAIL_ADDRESS;
    }

}
