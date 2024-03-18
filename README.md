# COMP3005_A3
Student name: Chloe Lachance-Soulard
Student ID: 101096672

Video link: https://mediaspace.carleton.ca/media/COMP3005+A3P1+Chloe+Lachance-Soulard/1_ofbyesz0

Setup instructions:
1. In pgadmin 4, create a new database (right click on databases and select create datanamed A3 and create/populate the table using the A3P1.txt file found in the database_files folder
2. Open IntelliJ and create a new project
3. Select maven and click next
4. Name your project and create it.
5. Open the pom.xml file and add the dependencies found in the pom.xml file found in the database_scripts folder (copy the contents of the pom.xml file found in the database_scripts folder and paste it under the properties in the pom.xml file in IntelliJ)
7. Load the maven changes (should have an m with a blue arrow symbol that shows up on page)
9. In src->main, create a new java file (name it main.java)
10. copy the code found in the main.java file found in the database_scripts folder and paste it into your java file in IntelliJ
11. You are now ready to run the application
12. To see the edits made to the database in pgadmin 4, open the query tool in your A3 databse and copy/paste the query found in the test_query file in the database_files folder
Note. Make sure you edit the main.java script lines to reflect the correct URL, username and password for your database 

How to run application:
1. In IntelliJ, run the script.
Note: in main function, comment out any of the application functions you do not want to test (getAllStudents(), addStudent(first_name, last_name, email, enrollment_date), updateStudentEmail(student_id, new_email), deleteStudent(student_id)). The function tests are hardcoded so edit the function calls to test out with more values if wanted.  
