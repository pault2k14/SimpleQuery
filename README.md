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
 - run the command java -jar query.jar -s :select columns: -f :filters: -o :order rows:
 - i.e. java -jar query.jar -s TITLE,DATE -f DATE=2014-04-02 -o TITLE 
   - output: 
             the hobbit,2014-04-02
             the matrix,2014-04-02
             
Detailed command line options
- Options can be specified in any order.
- -l datafile : Loads a datafile into the database.
- -s COLUMN1,COLUMN2,COLUMN3 (any number of columns) : Sets the columns to display in the query result.
- -o COLUMN1,COLUMN2,COLUMN3 (any number of columns) : Sets the ordering of the query results.
- -f COLUMN(operator)VALUE (any number of filters) : Sets filters for the query results.
  - Possible filter operatorss are =, !=, :lt:, :lte:, :gt:, :gte: 
- Possible columns: STB, TITLE, PROVIDER, DATE, REV, VIEW_TIME