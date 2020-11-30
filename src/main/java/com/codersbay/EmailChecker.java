package com.codersbay;

public class EmailChecker {

    // 64 RecipientName + 1 @ + 253 Domain
    final int maxLength = 64 + 1 + 253;

    // if CheckAll returns false the user can find out what went wrong with CheckRecipientName, haveAt and CheckDomain
    public boolean CheckAll(String email) {

        if (CheckRecipientName(email) && CheckDomain(email) && haveAt(email)) {
            return true;
        }

        return false;
    }

    /* Recipient name
        The recipient name represents an email mailbox that belongs to:

            A specific person
            A mailing list
            A department
            A role within a company (such as sales or customer service)
            The recipient name may be a maximum of 64 characters long and consist of:

            Uppercase and lowercase letters in English (A-Z, a-z)
                Digits from 0 to 9
                Special characters such as !#$%&'*+-/=?^_`{|}~.
            A special character cannot appear as the first or last character in an email address or appear consecutively two or more times. The most commonly used special characters are the period (.), underscore(_), hyphen (-) and plus sign (+).

            Alternative special characters such as  " ( ) , : ; < > @ [ \ ]
            These alternative special characters may be used with certain restrictions but are generally avoided since they may be prohibited by a sending or receiving server.

        Because an organization or mailbox provider may restrict the use of special characters even though they are technically valid, it is best to minimize the use of special characters in recipient names.

        Although recipient names are technically case sensitive, most mailbox providers and organizations accept upper and lower case letters to indicate the same user (for example, JOHNDOE@domainsample.com is the same as johndoe@domainsample.com).
    */
    public boolean CheckRecipientName(String email) {
        if (email == null || email.isEmpty() || email.length() > maxLength) {
            return false;
        }
        if (!(isAlphaNumeric(email.charAt(0)))) {
            return false;
        }
        for (int i = 1; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                if (i > 64) {
                    return false;
                }
                return true;
            }
            if (!(isAlphaNumeric(email.charAt(i)) ||
                    email.charAt(i) == '-' ||
                    email.charAt(i) == '.' ||
                    email.charAt(i) == '!' ||
                    email.charAt(i) == '#' ||
                    email.charAt(i) == '$' ||
                    email.charAt(i) == '%' ||
                    email.charAt(i) == '&' ||
                    email.charAt(i) == '\'' ||
                    email.charAt(i) == '*' ||
                    email.charAt(i) == '+' ||
                    email.charAt(i) == '-' ||
                    email.charAt(i) == '/' ||
                    email.charAt(i) == '=' ||
                    email.charAt(i) == '?' ||
                    email.charAt(i) == '^' ||
                    email.charAt(i) == '_' ||
                    email.charAt(i) == '`' ||
                    email.charAt(i) == '{' ||
                    email.charAt(i) == '}' ||
                    email.charAt(i) == '(' ||
                    email.charAt(i) == ')' ||
                    email.charAt(i) == '|' ||
                    email.charAt(i) == ':' ||
                    email.charAt(i) == '~' ||
                    email.charAt(i) == '.')) {
                return false;
            } else if (email.charAt(i) == '.' && email.charAt(i - 1) == '.') {
                return false;
            }
        }

        return true;
    }

    private boolean isAlphaNumeric(Character input) {
        return (input >= 'A' && input <= 'Z') ||
                (input >= 'a' && input <= 'z') ||
                (input >= '0' && input <= '9');
    }

    public boolean haveAt(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        boolean oneAt = false;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                if (!oneAt) {
                    oneAt = true;
                } else {
                    return false;
                }
            }
        }
        return oneAt;
    }

    /*Domain name
        The domain name is a string of letters and digits that defines a space on the Internet owned and controlled by a specific mailbox provider or organization.
        Domain names may be a maximum of 253 characters and consist of:

        Uppercase and lowercase letters in English (A-Z, a-z)
        Digits from 0 to 9
        A hyphen (-)
        A period (.)  (used to identify a sub-domain; for example,  email.domainsample)

        Top-level domain
        Top-level domains are the highest level of the domain name system for the Internet and is placed after the domain name in an email address.

        Common top-level domains are:        .com        .net        .org
    */
    public boolean CheckDomain(String email) {

        if (email == null || email.isEmpty() || email.length() > maxLength) {
            return false;
        }
        boolean afterAT = false;

        for (int i = 0; i < email.length(); i++) {

            if (afterAT) {
                if (!(isAlphaNumeric(email.charAt(i)) || email.charAt(i) == '-' || email.charAt(i) == '.')) {
                    return false;
                }
            }

            if (!afterAT && email.charAt(i) == '@') {
                afterAT = true;
                if(email.length()-i>254){
                    return false;
                }
            }
        }

        if (email.charAt(email.length() - 1) == '@') {
            return false;
        }

        return afterAT;
    }
}