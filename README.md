[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

# Cryptography

This repository contains the codes for several known ciphers and a few hashing algorithms. The codes for all the ciphers are in Java. Anyone with intermediate experience working with Java (approx. 1-2 years ) will understand the codes. Another repository of the ciphers in Python will be uploaded soon. Every code has its own documentation to explain its workings to the reader. The documentation also provides some historical context as well as the modern relevance of the cipher it describes. Some ciphers will have documentations within others. The entire list of ciphers is below:

### Simple Ciphers  
   * Caesar Cipher
   * ROT13     
   * Affine Cipher
   * Vigenère Cipher  
   * Gronsfeld Cipher
   * Beaufort Cipher  
   * Trithemius Cipher
   * Running Key Cipher  
   * Checkerboard Cipher    
   * Rail Fence Cipher  
   * Polybius Cipher
   * ADFGX Cipher
   * Bifid Cipher
   * Nihilist Cipher
   * Tap Code  
   * Trifid Cipher  
   * Wheatstone-Playfair cipher
   * Bacon Cipher  
   * One Time Pad

### Modern Ciphers
   * Enigma  
   * Lorentz
   * Sigaba
   * Feistel Network
   * Lai Maussey Network
   * RC5
   * IDEA
   * DES
   * 3DES  
   * AES-256

### Encoding
   * Base32
   * Base64
   * ASCII65
   * Baudot Code
   * URL Encoding
   * Bootstring

### Hashing Algorithms
   * Merkel-Damgård Construction
   * MD5
   * SHA-1
   * SHA-256

The classification of 'Simple Ciphers' and 'Modern Ciphers' is rather arbitrary (since ROT13 is a modern variant of the Caesar cipher yet is listed under Simple Ciphers) but is in place to segregate ciphers based on difficulty to 
The ciphers which do not have separate documentations are specified in others which are similar to it and have existed before. Furthermore, the documentation for each cipher provides background knowledge and mathematical representation of the cipher's encryption and decryption algorithms. The documentation is simply to assist the reader in understanding the working of the code. However, one must **read the documentations before going through the code.**
 
The following image below shows the flowchart of documentation for all the listed ciphers.:

It is highly recommended that the reader follows the order specified above. Although most documentations are relatively self-contained, there are several concepts that carry forth from previous ciphers.

## **Additional Resources to learning Cryptography** :

**Journey into Cryptography (Khan Academy) :**  
This course by Brit Cruise is a fun and easy to understand first peek into cryptography. The course material is not too technical and does not require the viewer to have any prior knowledge of computer science or mathematics. The course is recommended to those who do not want to delve too deeply into the mathematics of the cryptographic primitives but want to build an understanding of how they work. Anyone who has taken high school math courses can understand the course material.

**Cryptography I (Coursera)**  
This is a rigorous course by Dan Boneh of Stanford University that is a very strong starting point for an introduction into the world of cryptography. The course material is significantly technical and although there are no distinct prerequisties, a background in discrete mathematics and number theory (especially modular arithmetic and probability) would benefit the viewer. Regardless, the course material is very well explained. This course is recommended to those who want to explore the mathematics and fundamental workings of several crpytographic primtives. The course is aimed at undergraduates in applied mathematics, computer science and related fields.

**Practical Cryptography**  
[Practical Cryptography](http://practicalcryptography.com/) is a website that elaborates on the working of the ciphers and encryption schemes mentioned above. Recommendeded if one prefers a visual approach to learning about the algorithms.

**Boxentriq**  
[Boxentriq](https://www.boxentriq.com/) is similar to Practical Cryptography and provides an in-built encryption-decryption tool that allows the user to play around and experiment. Download the app to get puzzles and ciphers to crack.

**Reddit**  
[r/codes](https://www.reddit.com/r/codes/) and [r/cryptography](https://www.reddit.com/r/cryptography/) are two subreddits where one can learn a whole lot from other enthusiasts and professionals in the field of cryptography. r/codes is mainly for cracking ciphers and puzzles. r/cryptogrpahy is for learning about the encryption and decryption schemes.

**High School Mathematics Extensions**  
This webpage ['High School Mathematics Extension'](https://en.wikibooks.org/wiki/High_School_Mathematics_Extensions) is a fantastic WikiBooks page that helps you take the high school math curriculum beyond the prescribed syllabus. It delves into number theory, set theory and discrete probability.

**CryptoHack**  
This website is a fun and engaging way of learning modern cryptography. However, one must be well versed with JavaScript to be able to complete some 'missions'.

## **Books**  
**Introduction to Mathematical Cryptography (Hoffmann and Silvermann)**  
This is a great book for beginners and professinals alike. Its main focus is introducing the reader to crpytanalytic methods and the mathematics behind the encryption and decryption algorithms. Although it is a great read, it delves into lots of technical material very swiftly so only read if this is what you want to learn.

**The Codebreakers (Kahn, David)**  
A rich and detailed history of the history of ciphers and crpytography all the way from the Babylonians to modern fay security. A great read to learn about the history of cryptography.

**The Code Book (Singh, Simon)**  
Similar to The Codebreakers in content, however, it is far more concise.

**Abstract Algebra (Dummit and Foote)**  
Abstract Algebra is crucial to those pursuing cryptography. To study this branch of mathematics thorougly, this is a great book to start with group theory and ring theory. 

**A Classical Introduction to Modern Number Theory (Ireland and Rosen)**  
This book is arguably the gold standard for studying and understanding number theory. However, this is a graduate textbook and requires a working knowledge of group theory and ring theory. This textbook must be completed after Abstract Algebra or if one is thorough with the prerequisites.

**A Compuational Introduction to Number Theory and Algebra (Shoup)**  
This would be an alternate to studying from the previous two books. Although not as rigorous as the previous two combined, it is a fitting alternative to them. The prerequisite is high school mathematics. It is sufficient for any high school student learning about cryptography but may not be the right book to study from if you are an undergraduate or graduate student in computer science and related fields.

---
Although **this repository does not cover everything** in cryptography, it is a good starting point from which one can learn about several primitives of this field. 

I sincerely hope that this has been helpful to you and that you enjoyed reading and learning from it as much as I have enjoyed making it.
