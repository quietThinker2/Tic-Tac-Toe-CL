import kotlin.system.exitProcess

// This Class creates a Human player and marks their choice on the board
class Human (symbol: String){
    private val humanSymbol: String = symbol

    fun getHumanInput(){
        // Prompts Human their turn and if they are X or O
        println("Submit Q to quit program")
        println("Submit a Letter - Player $humanSymbol: ")

        // Get input from the Human
        val input = readln()
        makeHumanMove(input)
    }


    private fun makeHumanMove(input: String){
        // Human able to force close the program
        if (input == "Q") {
            exitProcess(0)
        }

        // Check that input is a valid move for the board
        if (input == "A" || input == "B" || input == "C" || input == "D" || input == "E" || input == "F" || input == "G" || input == "H" || input == "I") {
            try {

                // Place Human symbol and place it on according square
                val index = boardData.indexOf(input)
                if (boardData[index] != "X" || boardData[index] != "O") {
                    boardData[index] = humanSymbol
                } else {
                    println("Already X or an O")
                }
            } catch (e: IndexOutOfBoundsException){
                getHumanInput()
            }
        } else {
            getHumanInput()
        }
    }
}