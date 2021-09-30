# data_processing

Seznam
----------------------------------------------------------------------------------------------- 
Conditions:
URL is unique

className as argument
TOP, ContentType, Grep
TOP - top 10 <url>\t<click-count> from highest
ContentType - <content-type>\t<url-count>, sorted alphabetically, how many contetn types there is, contenty types given by input!!
Grep - grep exactly the string given by the regex (required parametr)

TODO
1. Write tests
2. OPT memory and CPU
3. Validation format <url>\t<content-type>\t<click count>
4 check the clikck count is a positive and a number
5. FIX - stream when sorting map
6. check for processing is DRY
7. learn maven!

cd Desktop\programming\job\interview\interview\

cmd /r dir /s /b *.java > sources.txt
dir /s /B *.java > sources.txt

cat data/input.txt | java -classpath ./target/classes cz.seznam.fulltext.robot.Runner className par

C:\Users\nelib\Desktop\programming\job\interview\interview\target\classes
