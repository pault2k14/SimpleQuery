SimpleQuery
 - A simple database amd query tool for user view data.

To build jar
 - Ensure that Maven and Java 1.8 JDK are installed.
 - run the command: mvn clean package

Be sure to first load data by running the following commands
 - copy the data.tst file to target directory where the query.jar file
   is located.
 - change to the target directory where the query.jar is located.
 - run the command: java -jar query.jar -l data.txt

To perform query
 - run the command java -jar query.jar -s select columns -f filters -o order rows
 - i.e. java -jar query.jar -s TITLE,DATE -f DATE=2014-04-02 -o TITLE 
   - output: 
             the hobbit,2014-04-02
             the matrix,2014-04-02
