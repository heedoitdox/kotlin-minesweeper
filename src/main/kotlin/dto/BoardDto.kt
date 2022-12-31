package dto

import domain.Blank
import domain.Board
import domain.Cell
import domain.Mine
import domain.Status

data class BoardDto(
    val cells: List<String>
) {
    companion object {
        fun from(board: Board): BoardDto {
            return BoardDto(
                getSortedCells(board).map {
                    toString(it)
                }
            )
        }

        private fun toString(cell: Cell): String {
            if (cell.status == Status.CLOSE) return "C"

            return when (cell) {
                is Mine -> "*"
                is Blank -> cell.minesAroundCount.toString()
            }
        }

        private fun getSortedCells(board: Board): List<Cell> {
            return board.cells.sortedWith(compareBy({ it.coordinate.x.value }, { it.coordinate.y.value }))
        }
    }
}
