PROJECT TITLE: LZW
PURPOSE OF PROJECT: Implement a simple version of the standard LZW compressions algorithm. Uses 3 modes to determine compression type. Do nothing mode does nothing when codeword dictionary is filled. Monitor mode monitors the compressions ration and when it is over a threshold, resets the codeword dictionary. Reset mode resets dictionary when codeword dictionary is filled. 
VERSION or DATE: 1.0
HOW TO START THIS PROJECT: To compile: javac MyLZW.java
Execution:    java LZW - r < input.txt   (compress reset mode)
Execution:    java LZW - m < input.txt   (compress monitor mode)
Execution:    java LZW - n < input.txt   (compress do nothing mode)
Execution:    java LZW + < input.txt   (expand)
AUTHORS: Sanketh Kolli 
USER INSTRUCTIONS: Compile and run the program with the file you wish to compress or decompress. Make sure you only compress and decompress files only modified by this file or you may corrupt the files and lose them irreversibly. 
