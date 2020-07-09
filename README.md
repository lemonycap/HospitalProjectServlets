# HospitalProjectServlets

## Task description
Система Больница. Врач определяет диагноз, делает назначение Пациенту (процедуры, лекарства, операции). Назначение может выполнить Медсестра (процедуры, лекарства) или Врач (любое назначение). Пациент может быть выписан из Больницы, при этом фиксируется окончательный диагноз.

## Project documentation
Check [systemHospital file](https://github.com/lemonycap/HospitalProjectServlets/blob/master/systemHospital.pdf). 
Documentation generated using Doxygen.

## How to install
Clone this repository: in your command line enter git clone https://github.com/lemonycap/HospitalProjectServlets.git

## Launch instructions
  * Install the database. Necessary data is located in [databaseData folder](https://github.com/lemonycap/HospitalProjectServlets/tree/master/databaseData).
  * Make sure Tomcat is installed on your computer. In order to install it, follow this [link](https://tomcat.apache.org/download-90.cgi).
  * Modify Tomcat's tomcat-users.xml by adding next line inside **tomcat-users**  tag :
      ```
      <user username="tomcat" password="tomcat" roles="manager-script" />
      ```
  * Check that your settings.xml file inside your .m2 folder is properly configured to use Tomcat 7 plugin :
      ```
      <servers>  
       <server>
          <id>TomcatServer</id>
          <username>tomcat</username>
          <password>tomcat</password>
       </server>
    </servers>  
    ```
   * Copy your **mysql-connector** jar file to your TomcatHome/lib  
   * Run the application using **tomcat7:deploy** 
   * Go to http://localhost:8000/
