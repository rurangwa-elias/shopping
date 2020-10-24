package edu.miu.groupx.card.cardservice.service.impl;

import edu.miu.groupx.card.cardservice.service.CardUtilService;

import java.util.Random;

public class CardUtilServiceImplementation implements CardUtilService {
    /**
     * @param bin    is the bank identifier number or the issuer number the first one or two digits
     *               of a credit card identify the issuer
     * @param length a valid credit card's length is between 13 to 16 digits. The issuer sets the length
     *               as needed
     * @return a string of valid random credit card number generated
     */
    @Override
    public String generateCreditCardNumber(String bin, int length) {

        Random random = new Random(System.currentTimeMillis());

        int randomCardNumberLength = length - (bin.length()) - 1;
        StringBuilder builder = new StringBuilder(bin);
        for (int i = 0; i < randomCardNumberLength; i++) {
            /**
             * get a random int value between 0 inclusive and 10 (exclusive)
             */
            int cardDigit = random.nextInt(10);
            builder.append(cardDigit);
        }
        /**
         * Now we run Luhn algorithm to generate the  check digits
         */
        int checkDigit = this.checkDigit(builder.toString());
        builder.append(checkDigit);
        return builder.toString();
    }

    @Override
    public boolean isValidCard(String cardNumber) {
        int sum = 0;
        for (int i = 0; i < cardNumber.length(); i++) {
            int digit = Integer.parseInt(cardNumber.substring(i, (i + 1)));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit = digit / 10 + digit % 10;
                }
            }
            sum += digit;
        }
        return (sum % 10) == 0;
    }

    /**
     * Generates the check digit required to make a given card valid cred
     * return false;it card number
     *
     * @param number the number for which a check digit is to be generated
     * @return a check digit that can make a given credit number valid
     */
    @Override
    public int checkDigit(String number) {
        /**
         *  RUNS THE FOLLOWING LUHN ALGORITHM
         *
         *  	     From the rightmost digit (excluding the check digit) and moving left, double the value of every second digit.
         *  	     The check digit is neither doubled nor included in this calculation; the first digit doubled is the digit located immediately left of the check digit.
         *  	     If the result of this doubling operation is greater than 9 (e.g., 8 × 2 = 16), then add the digits of the result (e.g., 16: 1 + 6 = 7, 18: 1 + 8 = 9) or, alternatively, the same final result can be found by subtracting 9 from that result (e.g., 16: 16 − 9 = 7, 18: 18 − 9 = 9).
         *          Take the sum of all the digits (including the check digit).
         *          If the total modulo 10 is equal to 0 (if the total ends in zero) then the number is valid according to the Luhn formula; otherwise it is not valid.
         */
        int sum = 0;
        int everyNextIndex = number.length() - 1;
        for (int i = number.length() - 1; i >= 0; i--) {

            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if (everyNextIndex == i) {
                digit *= 2;
                everyNextIndex -= 2;

                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;

        }
        return (10 - (sum % 10));

    }
}
