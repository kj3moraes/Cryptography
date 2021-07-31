# AES README
This file gives a brief overview of the implementation of AES in the repository. The in-depth analysis of the algorithm,
the differences between the variants and some mathematical security parameters are discusses in the PDF file.


    -----------
    |  MAIN   | is the main interface the user operates with 
    -----------
         |
         |
         --------------------  
                            | calls for encryption, decryption
                            | operations.
                    -----------------
          --------- | CRYPTOGRAPHIC | ---------------------------
          |         |   OPERATIONS  |                           | calls for generating initial
          |         -----------------                           | keys and round keys. Uses the 
          |                                                     |  (...)
          | calls for round functions                     --------------
     --------------                                       |    KEY     | ---------------
     |   ROUND    |                                       | GENERATION |               |
     |  FUNCTIONS |                                       --------------               | uses the generateKeyMatrix()
     --------------                                                                    | to generate all the round keys.
           |                                                                           | Calls the respective class
           | calls for Substituion operations                                          | based on the key length
       ---------                                                                -------------------
       |  SBOX |                                                                |  KEY LENGTH (.) |
       ---------                                                                -------------------  


The Version Inheritance Hierarchy specifies the inheritance hierarchy of the 

## DISCLAIMER
This program is not meant for any real-world usage. This is only written to be studied in an educational environment.
DO NOT USE THIS TO ENCRYPT ANY DATA COMMUNICATIONS. 
