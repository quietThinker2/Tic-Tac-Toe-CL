import kotlin.system.exitProcess

var boardData = mutableListOf("A","B","C","D","E","F","G","H","I")
var running = true



// This function is to start up the intro function of the Tic Tac Toe Program
fun main() {
    playIntro()
}

// This function is to display game modes to the user and have them pick a mode
fun playIntro(){
    println("Tic Tac Toe")
    println("1 Player VS Easy Computer")
    println("2 Player VS Hard Computer")
    println("3 Player VS Player")
    println("4 Exit Program")
    println("Press a Number: ")


    // Verify the user input and direct the flow
    when (readln()){
        /* The flow for when the game is active:
             -1. Display the board
             -2. Player X will go
             -3. Check if any winning moves were made
             -4. Repeat the above for player O/computer
             -5. Go back to 1                           */

        // The Player VS Easy Computer Mode
        "1" -> while (running){
            displayBoard()
            val human1 = Human("X")
            human1.getHumanInput()
            checkWinner()
            if (running){
                val computer = Computer("O")
                computer.easyMode()
                checkWinner()
            }
        }
        // The Player VS Hard Computer Mode
        "2" -> while (running){
            displayBoard()
            val human1 = Human("X")
            human1.getHumanInput()
            checkWinner()
            if (running){
                val computer = Computer("O")
                computer.hardMode()
                checkWinner()
            }
        }
        // The Player VS Player Mode
        "3" -> while (running){
            displayBoard()
            val human1 = Human("X")
            human1.getHumanInput()
            checkWinner()
            if (running){
                displayBoard()
                val human2 = Human("O")
                human2.getHumanInput()
                checkWinner()
            }
        }
        "4" -> exitProcess(0)
        // For invalid input restart the playIntro function
        else -> playIntro()
    }

}

// This function displays the board with any changes made
fun displayBoard(){
    println()
    println("  "+boardData[0]+" |  "+boardData[1]+" |  "+boardData[2]+" ")
    println("--------------")
    println("  "+boardData[3]+" |  "+boardData[4]+" |  "+boardData[5]+" ")
    println("--------------")
    println("  "+boardData[6]+" |  "+boardData[7]+" |  "+boardData[8]+" ")

}



fun checkWinner(){
    // check for a horizontal win
    for (i in 0..6 step 3) {
        if (boardData[i] == boardData[i+1] && boardData[i+1] == boardData[i+2] && boardData[i].isNotBlank()) {
            displayBoard()
            println("${boardData[i]} wins horizontal!")

            // Reset board and start intro
            boardData = mutableListOf("A","B","C","D","E","F","G","H","I")
            println()
            playIntro()
            return
        }
    }

    // check for a vertical win
    for (i in 0..2) {
        if (boardData[i] == boardData[i+3] && boardData[i+3] == boardData[i+6] && boardData[i].isNotBlank()) {
            displayBoard()
            println("${boardData[i]} wins vertical!")

            // Reset board and start intro
            boardData = mutableListOf("A","B","C","D","E","F","G","H","I")
            println()
            playIntro()
            return
        }
    }

    // check for a diagonal win
    if (boardData[0] == boardData[4] && boardData[4] == boardData[8] && boardData[0].isNotBlank()) {
        displayBoard()
        println("${boardData[0]} wins diagonal!")

        // Reset board and start intro
        boardData = mutableListOf("A","B","C","D","E","F","G","H","I")
        println()
        playIntro()
        return
    }

    // check for other diagonal win
    if (boardData[2] == boardData[4] && boardData[4] == boardData[6] && boardData[2].isNotBlank()) {
        displayBoard()
        println("${boardData[2]} wins diagonal!")

        // Reset board and start intro
        boardData = mutableListOf("A","B","C","D","E","F","G","H","I")
        println()
        playIntro()
        return
    }

    // check if there is a stalemate
    val containsOnlyXandO = boardData.all { it == "X" || it == "O" }
    if (containsOnlyXandO) {
        displayBoard()
        println("Stalemate no one wins!")

        // Reset board and start intro
        boardData = mutableListOf("A","B","C","D","E","F","G","H","I")
        println()
        playIntro()
        return
    }
}
