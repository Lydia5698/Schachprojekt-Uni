# Schach

Bedienungsanleitung Schach Gruppe 38 

1.	Allgemeine Hinweise:
Dieses Schachspiel befindet sich noch in der Entwicklungsphase.
Dies bedeutet, dass die Funktionalität rudimentär ist und entsprechend auf die Grundfunktionen beschränkt ist.
Bitte genau an die im Folgenden beschriebenen Handlungsanweisungen, um fehlverhalten Seitens des Programms zu vermeiden.
2.	CMD Interface: Nach Starten des Programms mit dem Argument `--no-gui`
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
z.B.`a3-a4` 
Somit bewegt die Figur sich vom aktuellen a3 auf das neue a3 Feld.
Bauern können umgewandelt werden sofern sie die Startlinie der Gegnerischen Offiziere Betreten. So wandelt`”e7-e8B”` einen Bauern in einen Läufer um, werden hier keine Spezifikationen angegeben, wird automatisch von einer Dame ausgegangen und diese anstelle des Bauern gesetzt.
Die Ausführung einer Rochade erfordert lediglich die Eingabe der Bewegung des Königs. 
3.	Allgemeine Schachregeln 
Es gelten die 
[allgemeinen Schachregeln](https://www.schachbund.de/files/dsb/srk/2019/FIDE-Regeln-2018-Final-DEU.pdf) 
Somit ergeben sich zusätzliche Vorgaben: Sollte im Dialog über dem neuen Schachfeld ein`”!Check”` (Schach) auftauchen, so sorgt der Spieler dafür, dass der nächste Zug den König aus dem Schach bringt oder diesen Schützt. Nach einem`”!CheckMate”` (Schach Matt) oder`”!Patt”` (Unentschieden) endet das Spiel.
Eine Aufgabe ist nicht implentiert.
4.	GUI - Graphical User Interface
Die Gui startet mit dem Start Screen dort kann sich die `Anleitung` druchgelesen werden oder das Spiel über den Button Game Start gestartet werden. Als nächstes kann sich der Spieler entscheiden, welchen `Spielmodus` er wählen möchte. Zur Auswahl stehen:

  4.1 `Normales Spiel`
  hier spielt der Spieler gegen eine andere Person lokal an seinem Computer. 

  4.2 `Gegen die KI` 
  hier kann der Spieler gegen den Computer antreten. Dafür muss er als nächstes seine Farbe (weiß oder schwarz) wählen, damit das Spiel starten kann.

  4.3 `Netzwerkspiel`
  hier kann der Spieler gegen eine andere Person über das Netzwerk spielen. Hierfür muss er eine/seine? iP Adresse und Portnummer angeben.

Der Spieler kann während des Spiels jederzeit auf die `Optionen` zugreifen und folgende Dinge anpassen:

  4.4 `Zeigt mögliche Züge an`
  bei dieser Option werden alle Züge die die Figur machen kann mit einem roten Punkt angezeigt. Dafür muss der Spieler die Option aktivieren und dann auf eine   Figur klicken

  4.5 `Zeigt an, ob der König im Schach steht`
  Nachdem die Option aktiviert wurde wird das Schach über ein Popup angezeigt. 
  
  4.6 `Kein Mehrfachklicken erlaub`
  Bei dieser Option ist es verboten mehr als eine Figur anzuklicken. Das heißt, dass der Spieler immer die Figur ziehen muss die er zuerst angelickt hat.

  4.7 `Das Spielfeld wird automatisch gedreht`
  hier wird nach jedem Zug das Spielfeld so gedreht, dass die Person, die als nächstes ihren Zug ausführt, unten ist.

Die Optionen sind alle Standartmäßig deaktiviert. Außerdem kann der Spieler jederzeit die Sprache wechseln. Dafür muss er auf den Button oben rechts mit der Flagge klicken. Zur Auswahl stehen Deutsch und Englisch.

Das Spiel kann jederzeit beendet werden, wenn der Spieler in den Optionen auf `Spiel beenden` klickt. Dann wird er wieder zum Start Screen zurück geleitet. Außerdem kann die Anwendung auch jederzeit durch `exit` beendet werden. 


# Maven

Kurzübersicht nützlicher Maven-Befehle. Weitere Informationen finden sich im Tutorial:

* `mvn clean` löscht alle generierten Dateien
* `mvn compile` übersetzt den Code
* `mvn javafx:jlink` packt den gebauten Code als modulare Laufzeit-Image. Das Projekt kann danach gestartet werden mit `target/chess/bin/chess`
* `mvn test` führt die Tests aus
* `mvn compile site` baut den Code, die Dokumentation und die Tests und führt alle Tests, sowie JaCoCo und PMD inklusive CPD aus. Die Datei `target/site/index.html` bietet eine Übersicht über alle Reports.
* `mvn javafx:run` führt das Projekt aus
* `mvn javafx:run -Dargs="--no-gui"` führt das Projekt mit Command-Line-Parameter `--no-gui` aus.
