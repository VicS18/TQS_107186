package tqs.hw1.backend.util;

import java.util.regex.Pattern;

public class PatternMatching {

    /*
     * From: https://www.baeldung.com/java-email-validation-regex
     */
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
        .matcher(emailAddress)
        .matches();
    }

    /*
     * Adapted from: https://www.autenticacao.gov.pt/documents/20126/0/Valida%C3%A7%C3%A3o+de+N%C3%BAmero+de+Documento+do+Cart%C3%A3o+de+Cidad%C3%A3o+%281%29.pdf/7d5745ba-2bcc-e861-3954-bafe9f7591a0?t=1658411665319
     * Sample number: 00000000 0 ZZ4 -> 000000000ZZ4
     */

    public static boolean validateNumeroDocumentoCC(String numeroDocumento)
    {
        int sum = 0;
        boolean secondDigit = false;

        if(numeroDocumento.length() != 12)
            throw new IllegalArgumentException("Invalid size for document number");
        
        for (int i = numeroDocumento.length()-1; i >= 0; --i)
        {
            int valor = getNumberFromChar(numeroDocumento.charAt(i));
            if (secondDigit)
            {
                valor *= 2;
                if (valor > 9)
                    valor -= 9;
            }
            sum += valor;
            secondDigit = !secondDigit;
        }

        return (sum % 10) == 0;
    }

    /*
     * Adapted from: https://www.autenticacao.gov.pt/documents/20126/0/Valida%C3%A7%C3%A3o+de+N%C3%BAmero+de+Documento+do+Cart%C3%A3o+de+Cidad%C3%A3o+%281%29.pdf/7d5745ba-2bcc-e861-3954-bafe9f7591a0?t=1658411665319
     */

    public static int getNumberFromChar(char letter)
    {
        switch(letter)
        {
            case '0' : return 0;
            case '1' : return 1;
            case '2' : return 2;
            case '3' : return 3;
            case '4' : return 4;
            case '5' : return 5;
            case '6' : return 6;
            case '7' : return 7;
            case '8' : return 8;
            case '9' : return 9;
            case 'A' : return 10;
            case 'B' : return 11;
            case 'C' : return 12;
            case 'D' : return 13;
            case 'E' : return 14;
            case 'F' : return 15;
            case 'G' : return 16;
            case 'H' : return 17;
            case 'I' : return 18;
            case 'J' : return 19;
            case 'K' : return 20;
            case 'L' : return 21;
            case 'M' : return 22;
            case 'N' : return 23;
            case 'O' : return 24;
            case 'P' : return 25;
            case 'Q' : return 26;
            case 'R' : return 27;
            case 'S' : return 28;
            case 'T' : return 29;
            case 'U' : return 30;
            case 'V' : return 31;
            case 'W' : return 32;
            case 'X' : return 33;
            case 'Y' : return 34;
            case 'Z' : return 35;
        }

        throw new IllegalArgumentException("Valor inválido no número de documento.");
    } 
}
