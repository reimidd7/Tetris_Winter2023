# group1-tetris

## Group 1 TCSS 305 A

This program is the beginnings of a Tetris game. We have created; a GUI with a file menu
with clickable buttons, a red grid region for the Tetris board, a blue region for the
next Tetris piece, and a green region for other information relevant to the player.

<img width="403" alt="Screenshot 2023-02-26 at 7 30 06 PM" src="https://user-images.githubusercontent.com/77321790/221467606-19aa4455-ba46-48dd-b9aa-26546a8495c2.png">

### Group Members
* Reilly Middlebrooks
* Tsion Fufa
* Viktoria Dolojan
* Rick Adams
* Calvin Beardemphl

### Sprint 1 Contributions
* Reilly Middlebrooks
  * Created `Frame.java`, and the frame and file menu. Fixed Checkstyle warnings and 
  added JavaDoc to the entire `view` package.
* Tsion Fufa
  * Created `Frame.java`, the file menu and the attached event handlers.
* Viktoria Dolojan
  * Created `NextPiece.java` and the next peice region. Got the GUI working with all of the reigions
  showing up in the correct areas in the entire `view` package, specifically in `NextPiece.java`, 
  `OtherInfo.java` and `createAndShowGUI()` in `Frame.java`.
* Rick Adams
  * Created `Application.java` to run the program. Created `BoardPanel.java` and the board region
  with the grid. Updated `Application.java` and `BoardPanel.java`.
* Calvin Beardemphl
  * Created `OtherInfo.java` and the other information region. Wrote `README.md`.
* All Group Members
  * Trouble shooted and debugged `Application.java` and `Frame.java` to get the GUI working correctly.


### Sprint 1 Comments
Our programm is ran from `Application.java` in the `view` package.

<img width="410" alt="Screenshot 2023-03-05 at 7 35 53 PM" src="https://user-images.githubusercontent.com/77321790/223014103-66f71c42-0b01-40d2-a0e6-b8dab6b2e5d3.png">


### Sprint 2 Contributions
* Reilly Middlebrooks
  * Updated `Frame.java` to include a `BoardInterface` object `myBoard`. Added inner class `BoardKeyListeners` to `Frame.java` to call the appropriate methods from `BoardInterface.java`. Found out how to make our GUI faster by calling `setResizable(false)` in the `Frame.java` constructor then in `BoardPanel.java` changed `getGridDimension()` to only return `BoardPanel.BOARD_SIZE`.
* Tsion Fufa
  * Updated `Frame.java` to include a `Swing Timer` that ticks according to `Frame.TIME_CONST`, and calls the `step()` from `Board.java`.
* Viktoria Dolojan
  *  Updated `NextPiece.java` to implement a `PropertyChangeListener`, added it to `myPCS`. Added `propertyChange()` to `NextPiece.java`. Included a drawing of a little face to `paintComponent()`in `NextPiece.java`.
* Rick Adams
  * Updated `BoardPanel.java` to implement a `PropertyChangeListener`, added it to `myPCS`. Added `propertyChange()` to `BoardPanel.java`.
* Calvin Beardemphl
  * Created `BoardInterface.java` with the help of group members. Added `PropertyChangeSupport myPCS` to `Board.java`, and fired property change when state changed. Kept track of meeting notes, and added to the `README.md`.
* All Group Members
  * Created `BoardInterface.java`, and identified where state changes happened in `Board.java`. Debugged and problem solved to fix our previously incredibly slow GUI.

### Sprint 2 Meetings
Link to minutes for [Daily's](https://docs.google.com/document/d/15rbKY0Ga7DSOc8l6EAj2nMc0TuU_ZQ-9Isekr8_9jbU/edit?usp=sharing).
We briefly met after class on Wednesday, March 1st, and Friday, March 3rd, in the hallway of the Dougan building to discuss `ProperyChangeSupport` and `PropertyChangeListeners`.
When we did not have formal meetings, we used a Discord server with all group members to communicate. 

### Sprint 2 Comments
We added a drawing of a face to the `NextPiece` region on the GUI, and changed the `Board` region on the GUI to UW Purple and Gold.
