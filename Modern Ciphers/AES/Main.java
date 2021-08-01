/*
 * Copyright (c) 2021 Keane Jonathan Moraes
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package AES;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner txt = new Scanner(System.in), num = new Scanner(System.in);
        String salt;
        int keyLength;

        // STEP 1 : COLLECT THE USER'S CHOICES
        System.out.println("\nEnter your choice " + "\n\t [E]ncrypt \n\t [D]ecrypt \n\t E[x]it");
        char choice = num.next().toUpperCase().charAt(0);
        String plainText, encryptedText, seed;

        System.out.println("\t SUBMIT YOUR AES VERSION: \n\t\t [1] AES-128 \n\t\t [2] AES-192 \n\t\t [3] AES-256");
        char aesVersion = txt.next().trim().charAt(0);

        // STEP 2 : SET THE VARIABLES IN ACCORDANCE WITH THE USERS CHOICE
        switch (aesVersion) {
            case '1':
                keyLength = 128;

            case '2':
                keyLength = 192;

            case '3':
                keyLength = 256;
        }

        switch (choice) {
            // ENCRYPTION
            case 'E' -> {
                System.out.print("\t PLAIN TEXT : ");
                plainText = txt.nextLine().trim();
                System.out.print("\t SEED (for secret key) : ");
                seed = txt.nextLine().toUpperCase().trim();
                System.out.print("\t SALT (16 character long string |" +
                        " leave blank if you want a pseudorandom one generated) : ");
                salt = txt.nextLine().toUpperCase().trim();
                //encryptedText = encrypt(plainText, seed);
                System.out.println("\n\t\t INPUTED PLAIN TEXT : " + plainText);
                System.out.println("\t\t KEY (seed for secret key): " + seed);
                System.out.println("\t\t SALT (salt for key generation): " + salt);
                System.out.println("\t\t GENERATED ENCRYPTION : " + encryptedText);
            }

            // DECRYPTION
            case 'D' -> {
                System.out.print("\t ENCRYPTED TEXT : ");
                encryptedText = txt.nextLine().trim();
                System.out.print("\t KEY : ");
                seed = txt.nextLine().toUpperCase().trim();
                System.out.print("\t SALT (the same 16 character string that was used for encryption) : ");
                salt = txt.nextLine().toUpperCase().trim();
                //plainText = decrypt(encryptedText, seed);
                System.out.println("\n\t\t INPUTED ENCRYPTED TEXT : " + encryptedText);
                System.out.println("\t\t KEY (seed for secret key): " + seed);
                System.out.println("\t\t GENERATED DECRYPTION : " + plainText);
            }

            // EXIT
            case 'X' -> System.exit(0);
            default -> System.out.println("KINDLY ENTER A NUMBER E,D or X");
        }
    }
}//end of class
