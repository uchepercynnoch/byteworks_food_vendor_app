# byteworks_food_vendor_app
Java backend assessment application
<br>
<h2>Technology Stack</h2>
<ul>
<li>Spring Boot v2.1.8</li>
<li>Spring Data JPA v2.1.8</li>
<li>Spring MVC v5.1.9</li>
<li>PostgreSQL v42.2.6</li>
<li>Jackson Databind v2.9.9.3</li>
<li>Maven v4.0.0</li>
</ul>
<h2>Setup Instructions</h2>

<h3>Clone the repository</h3>
<p>Clone this repository by either downloading the zip file or using git commandline tool.</p>
<p>Open the application on your favourite IDE, then import the dependecies in pom.xml file.</p>

<h3>Install Postgresql Database Server</h3>
<p>The application uses PostgresSQL database to persist data, so you will need to insall the server in your working PC.</p>
<p>I have provided a link below on how to install postgresql server for Mac, Windows, and Linux Operating Systems.</p>

<ul>
<li><a href="https://gist.github.com/ibraheem4/ce5ccd3e4d7a65589ce84f2a3b7c23a3" target="_blank">Mac</a></li>
<li><a href="https://www.postgresql.org/download/windows/" target="_blank">Windows</a></li>
<li><a href="http://postgresguide.com/setup/install.html" target="_blank">Linux</a></li>
</ul>
<p>After downloading and setting up your postgresql server, create your database.</p>
<p>Note that you can set password for your database server 
(in my case I left it blank) and name your database schema anything you want,
but you must make the required changes in the <b>applications.properties</b> file in the classpath to avoid errors
</p>

<h3>Run Application</h3>
<p>Lastly run the application from your favourite IDE</p>
