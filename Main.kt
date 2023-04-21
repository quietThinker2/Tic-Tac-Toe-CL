import kotlin.random.Random

val boardData = mutableListOf("A","B","C","D","E","F","G","H","I")
var running = true

fun main() {
    intro()
}

fun intro(){
    println("Tic Tac Toe")
    println("1 Against Easy Computer")
    println("2 Against Player")
    println("Press a Number: ")

    when (readln()){
        "1" -> while (running){
            board()
            playerPrompt("X")
            checkWinner()
            if (running){
                computerPrompt()
                checkWinner()
            }
        }
        "2" -> while (running){
            board()
            playerPrompt("X")
            checkWinner()
            if (running){
                board()
                playerPrompt("O")
                checkWinner()
            }
        }
        else -> intro()
    }

}

//
fun board(){
    println()
    println("  "+boardData[0]+" |  "+boardData[1]+" |  "+boardData[2]+" ")
    println("--------------")
    println("  "+boardData[3]+" |  "+boardData[4]+" |  "+boardData[5]+" ")
    println("--------------")
    println("  "+boardData[6]+" |  "+boardData[7]+" |  "+boardData[8]+" ")

}

fun playerPrompt(playerSymbol : String){
    println("Submit a Letter - $playerSymbol: ")
    val input = readln()
    if (input == "A" || input == "B" || input == "C" || input == "D" || input == "E" || input == "F" || input == "G" || input == "H" || input == "I") {
        try {

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
            println("${boardData[i]} wins!")
            board()
            println("${boardData[i]} wins!")
            running = false
            return
        }
    }

    // check for a vertical win
    for (i in 0..2) {
        if (boardData[i] == boardData[i+3] && boardData[i+3] == boardData[i+6] && boardData[i].isNotBlank()) {
            println("${boardData[i]} wins!")
            board()
            println("${boardData[i]} wins!")
            running = false
            return
        }
    }

    // check for a diagonal win
    if (boardData[0] == boardData[4] && boardData[4] == boardData[8] && boardData[0].isNotBlank()) {
        println("${boardData[0]} wins!")
        board()
        println("${boardData[0]} wins!")
        running = false
        return
    }
    if (boardData[2] == boardData[4] && boardData[4] == boardData[6] && boardData[2].isNotBlank()) {
        println("${boardData[2]} wins!")
        board()
        println("${boardData[2]} wins!")
        running = false
        return
    }
}

