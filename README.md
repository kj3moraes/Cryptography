[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/kj3moraes/Cryptography/blob/master/LICENSE)

# Cryptography
This repository contains the codes and documentations for several known ciphers and a few hashing algorithms. The codes for all the ciphers are in Java. Anyone with intermediate experience working with Java (approx. 1-2 years ) will understand the codes. Alternate versions written in Python will be upladed soon. Every code has its own documentation to explain its workings to the reader. Some documentations have expositions on the generalization of the cipher and others have the mathematical definitions of security. The documentation also provides some historical context as well as the modern relevance of the cipher it describes. Some ciphers will have documentations within others. The entire list of ciphers is below:

### Simple Ciphers  
   * Caesar Cipher ✅
   * ROT13 ✅    
   * Affine Cipher ✅
   * Vigenère Cipher ✅  
   * Autokey Cipher ✅
   * Gronsfeld Cipher ✅
   * Beaufort Cipher ✅
   * Trithemius Cipher ✅
   * Running Key Cipher  
   * Checkerboard Cipher
   * Rail Fence Cipher  
   * Polybius Cipher ⏺
   * ADFGX Cipher ✅
   * Nihilist Cipher ✅
   * Tap Code ✅
   * Bifid Cipher ✅ 
   * Trifid Cipher ⏺
   * Wheatstone-Playfair Cipher ⏺
   * Bacon Cipher  
   * One Time Pad ✅
   * Enigma  
   * Lorentz
   * Sigaba
### Modern Ciphers
   * Feistel Network ✅
   * Lai Maussey Network ✅
   * RC5
   * IDEA
   * DES
   * 3DES
   * DESX
   * AES ❗
   * Blowfish
   
### One-way compression functions & Hash functions
   * Davies-Meyer ⏺
   * Miyaguchi-Prenel ⏺
   * Matyas-Meyer Oseas ⏺
   * Merkle-Damgård Construction ⏺
   * MD5 ⏺
   * SHA-1
   * SHA-256


  
 ✅ - Completed \\
 ❗ - Commited but incomplete \\
 ⏺ - In the Works

The classification of 'Simple Ciphers' and 'Modern Ciphers' is rather arbitrary (since ROT13 is a modern variant of the Caesar cipher yet is listed under Simple Ciphers) but is in place to segregate ciphers based on difficulty to comprehend. The ciphers which do not have separate documentations are specified in others which are similar to it and have existed before. Furthermore, the documentation for each cipher provides background knowledge and mathematical representation of the cipher's encryption and decryption algorithms. The documentation is simply to assist the reader in understanding the working of the code. However, one must **read the documentations before going through the code.**

**Note that you MUST DOWNLOAD the PDFs and then study them. The Web version is badly formatted**
 
The following image below shows the flowchart of documentation for all the listed ciphers.:
![Simple Ciphers](https://raw.githubusercontent.com/LordVader31/Cryptography/master/Flowcharts/Simple%20Ciphers%20Flowchart%20-%20Web.png)
![Complex Ciphers](https://raw.githubusercontent.com/LordVader31/Cryptography/master/Flowcharts/Modern%20Ciphers%20Flowchart%20-%20Web.png)
![Hashing Algorithms](https://raw.githubusercontent.com/LordVader31/Cryptography/master/Flowcharts/Hashing%20Algorithms%20Flowchart%20-%20Web.png)

It is highly recommended that the reader follows the order specified above. Although most documentations are relatively self-contained, there are several concepts that carry forth from previous ciphers.

## **Additional Resources to learning Cryptography** :

**Journey into Cryptography (Khan Academy) :**  
This course by Brit Cruise is a fun and easy to understand first peek into cryptography. The course material is not too technical and does not require the viewer to have any prior knowledge of computer science or mathematics. The course is recommended to those who do not want to delve too deeply into the mathematics of the cryptographic primitives but want to build an understanding of how they work. Anyone who has taken high school math courses can understand the course material.

**Cryptography I (Coursera)**  
This is a rigorous course by Dan Boneh of Stanford University that is a very strong starting point for an introduction into the world of cryptography. The course material is significantly technical and although there are no distinct prerequisties, a background in discrete mathematics and number theory (especially modular arithmetic and probability) would benefit the viewer. Regardless, the course material is very well explained. This course is recommended to those who want to explore the mathematics and fundamental workings of several crpytographic primtives. The course is aimed at undergraduates in applied mathematics, computer science and related fields.


**Cryptography Stack Exchange**  
[Cryptograhy Stack Exchange](https://crypto.stackexchange.com/) is an excellent forum to learn about cryptography. One can search for previously answered questions or post one's own to clarify a doubt. There are multiple great answers from the community and it should be the first step for anybody wanting to learn more about cryptography.

**Practical Cryptography**  
[Practical Cryptography](http://practicalcryptography.com/) is a website that elaborates on the working of the ciphers and encryption schemes mentioned above. Recommendeded if one prefers a visual approach to learning about the algorithms.

**Boxentriq | Cryptii**  
[Boxentriq](https://www.boxentriq.com/) is similar to Practical Cryptography and provides an in-built encryption-decryption tool that allows the user to play around and experiment. Download the app to get puzzles and ciphers to crack. [Cryptii](https://cryptii.com/) is nearly the same Boxentriq except that it offers more than just ciphers. You can play around with Encoding and convert to and from different numeral bases. There are several  other fun operations to play around with.

**Reddit**  
[r/codes](https://www.reddit.com/r/codes/) and [r/cryptography](https://www.reddit.com/r/cryptography/) are two subreddits where one can learn a whole lot from other enthusiasts and professionals in the field of cryptography. r/codes is mainly for cracking ciphers and puzzles. r/cryptogrpahy is for learning about the encryption and decryption schemes.

**High School Mathematics Extensions**  
This webpage ['High School Mathematics Extension'](https://en.wikibooks.org/wiki/High_School_Mathematics_Extensions) is a fantastic WikiBooks page that helps you take the high school math curriculum beyond the prescribed syllabus. It delves into number theory, set theory and discrete probability.

**CryptoHack | Cryptopals**  
These websites are a fun and engaging way of learning modern cryptography. It is aimed at those who like to learn by solving puzzles and problems.

## **Books**  
**Introduction to Mathematical Cryptography (Hoffmann and Silvermann)**  
This is a great book for beginners and professinals alike. Its main focus is introducing the reader to cryptanalytic methods and the mathematics behind the encryption and decryption algorithms. Although it is a great read, it delves into lots of technical material very swiftly so only read if this is what you want to learn.

**The Codebreakers (Kahn, David)**  
A rich and detailed history of the history of ciphers and crpytography all the way from the Babylonians to modern day security. A great read to learn about the history of cryptography.

**The Code Book (Singh, Simon)**  
Similar to The Codebreakers in content, however, it is far more concise.

**Abstract Algebra (Dummit and Foote)**  
Abstract Algebra is crucial to those pursuing cryptography. To study this branch of mathematics thorougly, this is a great book to start with group theory and ring theory. 

**A Classical Introduction to Modern Number Theory (Ireland and Rosen)**  
This book is arguably the gold standard for studying and understanding number theory. However, this is a graduate textbook and requires a working knowledge of group theory and ring theory. This textbook must be completed after Abstract Algebra or if one is thorough with the prerequisites.

**A Compuational Introduction to Number Theory and Algebra (Shoup)**  
This would be an alternate to studying from the previous two books. Although not as rigorous as the previous two combined, it is a fitting alternative to them. The prerequisite is high school mathematics. It is sufficient for any high school student learning about cryptography but may not be the right book to study from if you are an undergraduate or graduate student in computer science and related fields.


## LICENSING
All code except under the Modern Ciphers folder is licenses under the MIT License. The code under the Modern Ciphers
folder is licensed under the Apache 2.0 License. All documentation in this repository in PDF form is licensed under the
GNU Free Documentation License v1.3. 
