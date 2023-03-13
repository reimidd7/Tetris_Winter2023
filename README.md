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

<img width="489" alt="Screenshot 2023-03-12 at 10 19 44 PM" src="https://user-images.githubusercontent.com/77321790/224614500-16b35e2d-e81f-4bca-9370-09f0c22b9036.png">


### Sprint 3 Contributions
* Reilly Middlebrooks
  * Created `DrawPiece.java` in the view class. Drew the pieces on the board, with full functionality of `left()`, `right()`, `down()`, `drop()` and `rotateCW()` in `Board.java`. Worked on getting `FrozenPieces` drawn.
* Tsion Fufa
  * Added the ability for a user to change the difficulty level to the menu bar. Added an easter egg in start of the game for changing the board grid size.
* Viktoria Dolojan
  * Added file menu buttons for pausing, ending, and starting a game in `Frame`, added a `timer` as well. Got pieces to be drawn in `NextPiece.java`. Worked on getting `FrozenPieces` drawn.
* Rick Adams
  * Worked on the `Board`, and getting pieces drawn. Created functionality for the start and end game file menu buttons. Cleaned up code, and fixed warnings throughout the entire project.
* Calvin Beardemphl
  * Created `Score.java` in the model class. Fixed functionality creating `timer` in `Frame`. Added text displaying the controls to the user, and placeholders for score, level, and lines cleared. Kept track of Daily's and updated the `README.md`.

### Sprint 3 Meetings
Link to minutes for [Daily's](https://docs.google.com/document/d/1SU5WXWF8geYZqd7Kj9Kz6s9fAEKLXDZMzDAg5lB9qUU/edit?usp=sharing).
We met on Monday at 6:30 pm over Zoom, were we discussed what was left to finish for Sprint 3, and divided up the work.
On Wednesday we met at 6:40 pm over Zoom, and checked in on our progress, and assigned more work.
On Saturday we met again at 6:30 pm over Zoom. In this meeting we determined what we had left to do based on the given rubric for Sprint 3.
We then divided up the work to be completed by Sunday.
We frequently communicated over Discord about the project, specifically notifying other group members when we pushed code, and what branch it was pushed to.
We also communicated our problems with the code, asked for help, and provided help.

### Sprint 3 Comments
The statistics displayed on `OtherInfo` panel for the score, level, and lines cleared are all placeholders, and do not update with the game.
