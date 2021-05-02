import generator.ItemPriceGenerator
import model.ItemPrice
import kotlin.math.absoluteValue
import kotlin.system.exitProcess

class App {
    private val items = ItemPriceGenerator.getItems()
    private lateinit var selectedItemPrice: ItemPrice
    private var inputPlayerOne = 0
    private var inputPlayerTwo = 0

    companion object {
        const val PRICE_EQUAL = 0
        const val PRICE_MORE_THAN = 1
        const val PRICE_LESS_THAN = 2

        @JvmStatic
        fun main(args: Array<String>) {
            App().runGame()
        }
    }

    fun runGame() {
        println("""
        ==========================================
        =========== Game Tebak Harga  ============
        ==========================================
        Start Game? (Y/N)
        """.trimIndent())
        if (readLine().equals("Y", ignoreCase = true)) {
            startGame()
        } else {
            println("Game Closed")
            exitProcess(0)
        }
    }

    private fun startGame() {
        // mengambil salah satu item dari list dengan sistem index
        // 0 in item.size, contoh item.size = 5
        // 0,1,2,3,4
        selectedItemPrice = items[(0 until items.size).random()]
        //print info name item
        printInfoItem(selectedItemPrice)
        //input user price
        inputPriceUser()
        //melakukan pengecekan nilai price
        checkWinner(checkPrice(inputPlayerOne),checkPrice(inputPlayerTwo))
    }

    private fun inputPriceUser() {
        println("Masukkan Harga Pemain Pertama = ")
        readLine()?.toInt()?.let {
            inputPlayerOne = it
        }
        println("Masukkan Harga Pemain Kedua = ")
        readLine()?.toInt()?.let {
            inputPlayerTwo = it
        }
    }

    private fun printInfoItem(selectedItemPrice: ItemPrice) {
        println("""
    **********************************************
    Tebaklah harga dari = ${selectedItemPrice.itemName}
    **********************************************
    """.trimIndent())
    }

    private fun checkPrice(userInput: Int): Int {
        when {
            userInput == selectedItemPrice.price -> {
                return PRICE_EQUAL
            }
            userInput > selectedItemPrice.price -> {
                return PRICE_MORE_THAN
            }
            else -> {
                return PRICE_LESS_THAN
            }
        }
    }

    /*
    * inputPlayerOne -> checkPrice(inputPlayerOne) -> integer value of PRICE_EQUAL, PRICE_MORE_THAN, PRICE_LESS_THAN
    * inputPlayerOne -> checkPrice(inputPlayerTwo) -> integer value of PRICE_EQUAL, PRICE_MORE_THAN, PRICE_LESS_THAN
    * */

    private fun checkWinner(resultPlayerOne: Int, resultPlayerTwo: Int) {
        println("""
        ==================================================================
        Harga untuk barang ${selectedItemPrice.itemName}, adalah ${selectedItemPrice.price}
        Hasilnya adalah . . . .
        ==================================================================
    """.trimIndent())
        if (resultPlayerOne == PRICE_EQUAL) {
            if (resultPlayerTwo == PRICE_EQUAL) {
                //both result is PRICE_EQUAL, means gak ada yang menang
                println("Keduanya Benar, Tidak ada yang Menang")
            } else {
                //player two, PRICE_MORE_THAN atau PRICE_LESS_THAN
                println("Player 1 Menang")
            }
        } else {
            //result player one, PRICE_MORE_THAN atau PRICE_LESS_THAN
            if (resultPlayerTwo == PRICE_EQUAL) {
                println("Player 2 Menang")
            } else {
                val diffOne = (inputPlayerOne - selectedItemPrice.price).absoluteValue
                val diffTwo = (inputPlayerTwo - selectedItemPrice.price).absoluteValue
                when {
                    (diffOne < diffTwo) -> {
                        println("Player 1 Mendekati Benar, Player 1 Menang")
                    }
                    (diffOne > diffTwo) -> {
                        println("Player 2 Mendekati Benar, Player 1 Menang")
                    }
                    else -> {
                        //diffOne == diffTwo
                        println("Keduanya hampir benar, Tidak ada yang menang")
                    }
                }
            }
        }
    }
}