# Cryptography

This repository contains the codes for several known ciphers and a few hashing algorithms. The codes for the ciphers are in Java. Another repostiory of those codes in Python will be uploaded soon. Every code has its own documentation to explain its workings to the reader. It also provides some historical context as well as the modern relevance of the cipher it describes.  The entire list of ciphers is as below.  

### Simple Ciphers  
   * Caesar Cipher   
   * Beale Cipher  
   * Vignere Cipher  
   * Gronsfeld Cipher  
   * Beaufort Cipher  
   * Trithemius Cipher  
   * Checkerboard Cipher  
   * Affine Cipher  
   * ROT13  
   * Rail Fence Cipher  
   * Polybius Cipher
   * ADFGX Cipher
   * Bifid Cipher
   * Nihilist Cipher
   * Tap Code  
   * Trifid Cipher  
   * Alphabetical Substitution  
   * Bacon Cipher  
   * One Time Pad

### Modern Ciphers
   * Enigma-V  
   * Lorentz SB-40
   * Feistel Network
   * Lai Maussey Network
   * RC5
   * IDEA
   * DES
   * 3DES  
   * AES - 256bit    

### Encoding
   * Base32
   * Base64
   * ASCII65
   * Baudot Code
   * URL Encoding
   * Bootstring       

### Hashing Algorithms
   * MD5
   * SHA-1
   * SHA-2

There is a documentation for nearly all the ciphers and encryption schemes. The ones who don not have  
a separate documentation are specified in others which either are similar and have existed before or
simply needed no special algorithm or mathematical dictum to represent its working.  
Eg. Beale Cipher which is a variant of the Vigenère cipher where the key is an excerpt of a book or
journal. It uses the same encryption algorithm as the Vigenère and hence is specified under 'Variants and
Cryptanalysis' section of the Vigenère documentation.  

 Furthermore, the documentation for each cipher provides a background knowledge and mathematical
 representation of the cipher's encryption and decryption algorithms. **This documentation is simply
 to  assist the reader in understanding the working of the code.**  

The following image below shows the flowchart of documentation for each and every encryption scheme listed.
INSERT IMAGE HERE

This project has been a personal endeavor in order to provide ease of access to budding cryptography
enthusiasts out there to be able to get an understanding of the fundamentals of code in the crypto
world without having to spend hours scouring the internet trying to make sense of the algorithms and
the formulae.
