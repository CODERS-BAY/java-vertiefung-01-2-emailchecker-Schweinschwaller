package com.codersbay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmailChecker {

    @Test
    @DisplayName("haveAt one @ in the email")
    public void testhaveAt() {
        assertEquals(new EmailChecker().haveAt("georg.schweinschwaller@live.at"), true);
        assertEquals(new EmailChecker().haveAt("georg.schweinschwaller@@live.at"), false);
        assertEquals(new EmailChecker().haveAt("georg.schweinschwaller@@@live.at"), false);
        assertEquals(new EmailChecker().haveAt("georg.schweinschwallerlive.at"), false);
        assertEquals(new EmailChecker().haveAt(""), false);
        assertEquals(new EmailChecker().haveAt(null), false);
    }

    @Test
    @DisplayName("CheckDomain")
    public void testCheckDomain() {
        assertEquals(new EmailChecker().CheckDomain("georg.schweinschwaller@live.at"), true);
        assertEquals(new EmailChecker().CheckDomain("georg.schweinschwaller@@live.at"), false);
        assertEquals(new EmailChecker().CheckDomain("georg.schweinschwallerlive.at"), false);
        assertEquals(new EmailChecker().CheckDomain("georg.schweinschwaller@"), false);
        assertEquals(new EmailChecker().CheckDomain(""), false);
        assertEquals(new EmailChecker().CheckDomain(null), false);
    }

    @Test
    @DisplayName("CheckRecipientName")
    public void testCheckRecipientName() {
        assertEquals(new EmailChecker().CheckRecipientName("georg.schweinschwaller@live.at"), true);
        assertEquals(new EmailChecker().CheckRecipientName("georg.schweinschwaller@@live.at"), true);
        assertEquals(new EmailChecker().CheckRecipientName("georg.schweinschwallerlive.at"), true);
        assertEquals(new EmailChecker().CheckRecipientName("!georg.schweinschwaller@live.at"), false);
        assertEquals(new EmailChecker().CheckRecipientName("georg.schweinschwallerlive.at"), true);
        assertEquals(new EmailChecker().CheckRecipientName(""), false);
        assertEquals(new EmailChecker().CheckRecipientName(null), false);
    }

    @Test
    @DisplayName("CheckAll")
    public void testCheckAll() {
        assertEquals(new EmailChecker().CheckAll("georg.schweinschwaller@live.at"), true);
        assertEquals(new EmailChecker().CheckAll(""), false);
        assertEquals(new EmailChecker().CheckAll(null), false);

        //Valid email addresses
        assertEquals(new EmailChecker().CheckAll("simple@example.com"), true);
        assertEquals(new EmailChecker().CheckAll("very.common@example.com"), true);
        assertEquals(new EmailChecker().CheckAll("disposable.style.email.with+symbol@example.com"), true);
        assertEquals(new EmailChecker().CheckAll("other.email-with-hyphen@example.com"), true);
        assertEquals(new EmailChecker().CheckAll("fully-qualified-domain@example.com"), true);
        // may go to user.name@example.com inbox depending on mail server
        assertEquals(new EmailChecker().CheckAll("user.name+tag+sorting@example.com"), true);
        // one-letter local-part
        assertEquals(new EmailChecker().CheckAll("x@example.com"), true);
        assertEquals(new EmailChecker().CheckAll("example-indeed@strange-example.com"), true);
        // local domain name with no TLD, although ICANN highly discourages dotless email addresses[10]
        assertEquals(new EmailChecker().CheckAll("admin@mailserver1"), true);
        // see the List of Internet top-level domains
        assertEquals(new EmailChecker().CheckAll("example@s.example"), true);
        //bangified host route used for uucp mailers
        assertEquals(new EmailChecker().CheckAll("mailhost!username@example.org"), true);
        //% escaped mail route to user@example.com via example.org
        assertEquals(new EmailChecker().CheckAll("user%example.com@example.org"), true);

        //Invalid email addresses
        // only one @ is allowed outside quotation marks
        assertEquals(new EmailChecker().CheckAll("A@b@c@example.com"), false);
        // none of the special characters in this local-part are allowed outside quotation marks
        assertEquals(new EmailChecker().CheckAll("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com"), false);
        assertEquals(new EmailChecker().CheckAll("this is\"not\\allowed@example.com"), false);
        assertEquals(new EmailChecker().CheckAll("this\\ still\\\"not\\\\allowed@example.com"), false);
        // local part is longer than 64 characters
        assertEquals(new EmailChecker().CheckAll("1234567890123456789012345678901234567890123456789012345678901234+x@example.com"), false);
        //Underscore is not allowed in domain part
        assertEquals(new EmailChecker().CheckAll("i_like_underscore@but_its_not_allow_in_this_part.example.com"), false);

    }
}