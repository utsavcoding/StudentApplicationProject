starter.sql -> sql script for data-setup to test the project

Configuration needed to be done:-
 1) Update hibernate.properties as per your system.
 2) Specify the path where you want to upload the CV on server in the config.properties file.
 
Steps to run application:-
 1) Goto http://localhost:8080/alumni-placement/
 2) Login using any of the credential mentioned in the Student table(say Roll No: MT2019126, password: tiwariutsav)
 3) All the offers for which the given student is eligible will be shown as per the student's domain, specialization and grade.
 4) Student needs to upload the CV in order to apply. Once the student applies, that job offer won't be shown anymore.
 5) Uploaded CV can be seen in the location specified in config.properties. Path of this CV is stored in JobApplication table.
 6) Student can logout using the button at the top.