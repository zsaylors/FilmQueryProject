# Film Query Project
![alt text](https://i.ibb.co/pKhdBGy/Screen-Shot-2019-10-26-at-9-45-44-PM.png "Project image")
#### Week 7 Skill Distillery Homework

### Overview
The film query project served as an introduction to using mySQL and utilizing databases to obtain information for Java programs.  The primary function of the app, as it currently stands, is to provide data on movies if the user has a film id or wants to search by a keyword.

When opened, the user will be presented with a menu that displays three options as seen below:
```
🍿 | Film Lists | 🎞
1.  Look up a film by its id.
2.  Look up a film by a search keyword.
3.  Exit.
```

#### Option 1: Look up a film by its id.
A user may input an id of a film.  If the id does not exist in the database, the program will return the user to the main menu.  When a correct id is entered, the user will receive the title, year, rating, description, language, category, and a list of actors.  Example output is displayed below.
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

A submenu will display as shown below.
```
Would you like to:
1. View all film details?
2. See available copies?
3. Return to main menu?
```
1.  View all film details? - This will show the toString of the film, retrieving all available data from the film table for that specific film.  Note that it is not formatted and prints as the standard Eclipse generated toString().

2. See available copies? - The conditions that the films are in and the addresses of the store where each film is located will display.

3. Return to main menu? - This will bring the user back to the main menu.

#### Option 2: Look up a film by a search keyword.
The user will be prompted to type a keyword.  This may be a single letter, single word, or a phrase.  The program will search for that specific phrase in the film title or film description.  If all films that have matching keywords will display.

### How to Run
Download the files from the GitHub repository.

Open the files in an Java Integrated Development Environment such as Eclipse (free).

The main class is called FilmQueryApp.java
```
FilmQuery > src > com.skilldistillery.filmquery.app > FilmQueryApp.java
 ```

From FilmQueryApp, the program can be run as a Java Program through the IDE's console.

### Technologies and Topics Applied
* CRUD
* mySQL
* Java Data Base Connectivity (JDBC)
* SQL Queries

to be finished tomorrow
### Lessons Learned

### Future Implementation
