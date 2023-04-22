import kotlin.random.Random
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
    println("1 Against Easy Computer")
    println("2 Against Player")
    println("3 Exit Program")
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
            showBoard()
            playerPrompt("X")
            checkWinner()
            if (running){
                computerPrompt()
                checkWinner()
            }
        }
        // The Player BS Player Mode
        "2" -> while (running){
            showBoard()
            playerPrompt("X")
            checkWinner()
            if (running){
                showBoard()
                playerPrompt("O")
                checkWinner()
            }
        }
        "3" -> exitProcess(0)
        // For invalid input restart the playIntro function
        else -> playIntro()
    }

}

// This function displays the board with any changes made
fun showBoard(){
    println()
    println("  "+boardData[0]+" |  "+boardData[1]+" |  "+boardData[2]+" ")
    println("--------------")
    println("  "+boardData[3]+" |  "+boardData[4]+" |  "+boardData[5]+" ")
    println("--------------")
    println("  "+boardData[6]+" |  "+boardData[7]+" |  "+boardData[8]+" ")

}

// This function makes the Player move and marks it on the board
fun playerPrompt(playerSymbol : String){

    // Prompts which player turn it is with associating if they are X or O
    println("Submit Q to quit program")
    println("Submit a Letter - Player $playerSymbol: ")

    // Get input from the user
    val input = readln()

    // User able to force close the program
    if (input == "Q") {
        exitProcess(0)
    }

    // Check that input is a valid move for the board
    if (input == "A" || input == "B" || input == "C" || input == "D" || input == "E" || input == "F" || input == "G" || input == "H" || input == "I") {
        try {

            // Place player symbol and place it on according spot
            val index = boardData.indexOf(input)
            if (boardData[index] != "X" || boardData[index] != "O") {
                boardData[index] = playerSymbol
            } else {
                println("Already X or an O")
            }
        } catch (e: IndexOutOfBoundsException){
            playerPrompt(playerSymbol)
        }
    } else {
        playerPrompt(playerSymbol)
    }
}
// Easy Computer function to randonly place a O on a valid spot
fun computerPrompt(){
    val randomNum = Random.nextInt(0,8)
    if (boardData[randomNum] == "X" || boardData[randomNum] == "O") {
        computerPrompt()
    } else {
        boardData[randomNum] = "O"
    }


}

fun checkWinner(){
    // check for a horizontal win
    for (i in 0..6 step 3) {
        if (boardData[i] == boardData[i+1] && boardData[i+1] == boardData[i+2] && boardData[i].isNotBlank()) {
            showBoard()
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
            showBoard()
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
        showBoard()
        println("${boardData[0]} wins diagonal!")

        // Reset board and start intro
        boardData = mutableListOf("A","B","C","D","E","F","G","H","I")
        println()
        playIntro()
        return
    }

    // check for other diagonal win
    if (boardData[2] == boardData[4] && boardData[4] == boardData[6] && boardData[2].isNotBlank()) {
        showBoard()
        println("${boardData[2]} wins diagonal!")

        // Reset board and start intro
        boardData = mutableListOf("A","B","C","D","E","F","G","H","I")
        println()
        playIntro()
        return
    }

    // check if there is a stalemate
    var containsOnlyXandO = boardData.all { it == "X" || it == "O" }
    if (containsOnlyXandO) {
        showBoard()
        println("Stalemate no one wins!")

        // Reset board and start intro
        boardData = mutableListOf("A","B","C","D","E","F","G","H","I")
        println()
        playIntro()
        return
    }
}
