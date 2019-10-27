# Film Query Project
![alt text](https://i.ibb.co/pKhdBGy/Screen-Shot-2019-10-26-at-9-45-44-PM.png "Project image")
#### Week 7 Skill Distillery Homework

### Overview
The film query project served as an introduction to mySQL and databases by reading information into a Java program.  As it currently stands, the primary function of the program is to provide data on movies that user has chosen.

When opened, the user will be presented with a menu that displays three options as seen below:
```
🍿 | Film Lists | 🎞
1.  Look up a film by its id.
2.  Look up a film by a search keyword.
3.  Exit.
```

#### Option 1: Look up a film by its id.
A user may input an id of a film.  If the id does not exist in the database the program will return the user to the main menu.  When a correct id is entered, the user will receive the title, year, rating, description, language, category, and a list of actors.  Example output is displayed below.
```
Please enter film Id: 20

Title: AMELIE HELLFIGHTERS
Year: 2008
Rating: R
Description: A Boring Drama of a Woman And a Squirrel who must Conquer a Student in A Baloon
Language: English
Category: Music
Actors: Carmen Hunt, Walter Torn, Ed Mansfield, Ewan Gooding, Ian Tandy, Laura Brody.
```

Also, a submenu will display as shown below.
```
Would you like to:
1. View all film details?
2. See available copies?
3. Return to main menu?
```
1.  View all film details? - This will show the toString of the film, retrieving all available data from the film class for that specific film.  Note that it is not formatted and prints as the standard Eclipse generated toString().

2. See available copies? - The condition (used, new, etc.) that the films are in and the address of the store where each film is located will display.

3. Return to main menu? - This will bring the user back to the main menu.

#### Option 2: Look up a film by a search keyword.
The user will be prompted to type a keyword.  This may be a single letter, a single word, or a phrase.  The program will search for and exact match in the film title or film description.  All films that have matching keywords will display.

#### Option 3: Exit.
Choosing exit will terminate the program.


### How to Run
Download the files from the GitHub repository.

Open the files in an Java Integrated Development Environment such as Eclipse (free).

The main class is called FilmQueryApp.java
```
FilmQuery > src > com.skilldistillery.filmquery.app > FilmQueryApp.java
 ```

From FilmQueryApp, the program can be run as a Java Program through the IDE's console.


### Technologies and Topics Applied
* **CRUD** - In this project only the read aspect of CRUD was applied.  I believe next week the project will be expanded to include all aspects of this CRUD principle.

* **Java Data Base Connectivity (JDBC)** - Reading the database was accomplished by:
1.  Connecting by `DriverManager.getConnection` and `getConnection` with arguments of url, username, and password.
2.  Utilizing `Class.forName` to ensure driver class is loaded.
3.  Closing connections when finished using them.

* **Maven** - A XML configuration file was created, and dependencies were added the library `mysql-connector-java` with its respective groupId, artifactId, and version tags.

* **SQL Queries**
PreparedStatements objects were created.  Then a `ResultSet` object was created with `.executeQuery`.  The `ResultSet` could then retrieve rows of data.

* **SQL Injection**
To prevent SQL injection, PreparedStatements were used with bind variables `?` instead of using regular statements that concatenate runtime data.  Using the prepared statement also prevents the query from having to parse, compile, and execute each time, making the program more efficient.

* **Join, Where, and Like**
Film categories, actors, and languages were in their own tables.  Sometimes, a single `JOIN` was used and a `ON` statement added to compare two columns.  Other times, there was an association table, and two `JOIN` and `ON` statements had to be added in order to compare columns.

The `WHERE` clause was used to filter predicates.  For example it was used to find film's id and return data for the given id, as seen below.
```actors = new ArrayList<>();
		String sql = "SELECT * FROM film_actor
                  JOIN film ON film.id = film_actor.film_id
                  JOIN actor on actor.id = film_actor.actor_id
                  WHERE film.id = ?";
  ```

  `LIKE` was used to compare wildcards.  In this program, it compared the user entered keyword with film titles and descriptions.

* **Referential Integrity (RI)**
RI was used when joining tables.  The project used keys to connect tables.  For example, to add the film category, three tables were connected by using an association table.  All primary and foreign keys had to agree with each other in order for this connection to work.

* **JUnit 5**
A few test cases were written for practice, so in the future unit testing won't be as trivial.  This was done after the program was run and debugged, so it wasn't as useful as it could have been.


### Lessons Learned
The biggest lesson learned from this project was learning the SQL language.  It was important for me to write out the syntax in Atom and then paste it into mySQL in order to determine if the syntax was right.  Sometimes I had spelling errors or I wanted to use  `show` instead of `select`, or `in` seemed like a more intuitive word than `from`.  It would have been very hard to debug the syntax directly from Eclipse.  If it didn't work in the mySQL console, I knew something was wrong with what I had typed.

Joining tables together with primary and foreign keys was also something that I had to wrap my mind around.  

Beyond that the assignment expanded on how to connect a database and retrieve information to use in an application that used object oriented programming.


### Future Implementation
The sdvid database contains many tables that were not used.  A store and inventory class were created only to display the inventory of certain films.  A future menu item could list all the movies at a store and so on.  Also, tables exist with employees, mangers, and their addresses.  An entire management program could be formed with this.  Obviously, that is much out of the scope of this project.  Later, CRUD principles could be added to add or update films and a front end could be added.  The sky is the limit with this one - not that film stores are really around anymore!
