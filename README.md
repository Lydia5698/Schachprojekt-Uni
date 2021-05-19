# Schach

Bedienungsanleitung Schach Gruppe 39 

1.	Allgemeine Hinweise:
Dieses Schachspiel befindet sich noch in der Entwicklungsphase.
Dies bedeutet, dass die Funktionalität rudimentär ist und entsprechend auf die Grundfunktionen beschränkt ist.
Bitte genau an die im Folgenden beschriebenen Handlungsanweisungen, um fehlverhalten Seitens des Programms zu vermeiden.
2.	CMD Interface: Nach Starten des Programms mit dem Argument --no-gui 
Gelangt man in den PvP-Modus
Folgend die Symbole der Figuren: 
Anmerkung: große Buchstaben für Weiße Figuren / kleine Buchstaben für schwarze Figuren
Bauern		(Pawn):		„P“
Türme		(Rook):		„R“ 
Pferd/Springer	(Knight): 	„N“
Läufer		(Bishop):	„B“
Dame		(Queen):	„Q“
König		(King):		„K“
Die Befehle sind wir wie folgt einzugeben: Startkoordinate-Zielkoordinate
z.B. a3-a4 
Somit bewegt die Figur sich vom aktuellen a3 auf das neue a3 Feld.
Bauern können umgewandelt werden sofern sie die Startlinie der Gegnerischen Offiziere Betreten. So wandelt ”e7-e8B” einen Bauern in einen Läufer um, werden hier keine Spezifikationen angegeben, wird automatisch von einer Dame ausgegangen und diese anstelle des Bauern gesetzt.
Die Ausführung einer Rochade erfordert lediglich die Eingabe der Bewegung des Königs. 
3.	Allgemeine Schachregeln 
Es gelten die allgemeinen Schachregeln
https://www.schachbund.de/files/dsb/srk/2019/FIDE-Regeln-2018-Final-DEU.pdf, 
Somit ergeben sich zusätzliche Vorgaben: Sollte im Dialog über dem neuen Schachfeld ein ”!Check” (Schach) auftauchen, so sorgt der Spieler dafür, dass der nächste Zug den König aus dem Schach bringt oder diesen Schützt. Nach einem ”!CheckMate” (Schach Matt) oder ”!Patt” (Unentschieden) endet das Spiel.
Eine Aufgabe ist nicht implentiert.
4.	GUI - Graphical User Interface
Funktion nicht verfügbar.


# Maven

Kurzübersicht nützlicher Maven-Befehle. Weitere Informationen finden sich im Tutorial:

* `mvn clean` löscht alle generierten Dateien
* `mvn compile` übersetzt den Code
* `mvn javafx:jlink` packt den gebauten Code als modulare Laufzeit-Image. Das Projekt kann danach gestartet werden mit `target/chess/bin/chess`
* `mvn test` führt die Tests aus
* `mvn compile site` baut den Code, die Dokumentation und die Tests und führt alle Tests, sowie JaCoCo und PMD inklusive CPD aus. Die Datei `target/site/index.html` bietet eine Übersicht über alle Reports.
* `mvn javafx:run` führt das Projekt aus
* `mvn javafx:run -Dargs="--no-gui"` führt das Projekt mit Command-Line-Parameter `--no-gui` aus.
