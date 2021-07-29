# AES README
This file gives a brief overview of the implementation of AES in the repository. The in-depth analysis of the algorithm,
the differences between the variants and some mathematical security parameters are discusses in the PDF file. 

The class that 





**Class Descriptions**
* `Main.java`
* `KeyGeneration.java`
* `Round Function.java`
* `SBox.java` - implements the AES Substitution Box as described by [Rjindael S-Box](https://en.wikipedia.org/wiki/Rijndael_S-box)
* `KeyOperations.java` - performs the major cryptographic operations.
    * `KeyLength128` - 128 bit variant of AES
    * `KeyLength192` - 192 bit variant of AES
    * `KeyLength256` - 256 bit variant of AES