import kotlin.random.Random

class Computer(symbol: String) {
    private val computerSymbol: String = symbol
    private val humanSymbol: String = if (computerSymbol == "X") "O" else "X"

    // Define the constant list of winning paths
    private val winningPaths = listOf(
        listOf(0, 1, 2), // Top row
        listOf(3, 4, 5), // Middle row
        listOf(6, 7, 8), // Bottom row
        listOf(0, 3, 6), // Left column
        listOf(1, 4, 7), // Middle column
        listOf(2, 5, 8), // Right column
        listOf(0, 4, 8), // Top-left to bottom-right diagonal
        listOf(2, 4, 6) // Top-right to bottom-left diagonal
    )

    // Easy Computer function to randomly make a move on a valid square
    fun easyMode(){
        val randomNum = Random.nextInt(0,8)
        if (boardData[randomNum] == humanSymbol || boardData[randomNum] == computerSymbol) {
            easyMode()
        } else {
            boardData[randomNum] = computerSymbol
        }
    }


    fun hardMode() {
        // Check if the computer can Win
        for (path in winningPaths) {
            val count = path.count { boardData[it] == computerSymbol }
            if (count == 2) {
                // Find the missing square
                val missingIndex = getMissingIndex(path)
                if (missingIndex != null) {
                    // Make the winning move
                    boardData[missingIndex] = computerSymbol
                    return
                }
            }
        }
        // Check if the computer can Block
        for (path in winningPaths) {
            val count = path.count { boardData[it] == humanSymbol }
            if (count == 2) {
                // Find the missing square
                val missingIndex = getMissingIndex(path)
                if (missingIndex != null) {
                    // Make the winning move
                    boardData[missingIndex] = computerSymbol
                    return
                }
            }
        }

        // Have the Computer take the center if possible
        if (boardData[4] != humanSymbol && boardData[4] != computerSymbol){
            boardData[4] = computerSymbol
            return
        }

        // If the computer can't win/block/take center, make a random move
        val emptyIndexes = boardData.indices.filter { boardData[it] != humanSymbol && boardData[it] != computerSymbol }
        val randomIndex = emptyIndexes.random()
        boardData[randomIndex] = computerSymbol
    }

    private fun getMissingIndex(path: List<Int>): Int? {
        for (index in path) {
            if (boardData[index] != humanSymbol && boardData[index] != computerSymbol) {
                return index
            }
        }
        return null
    }

}