package com.johnjesus.listmagic.app

import com.johnjesus.listmagic.view.MainView
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.input.DragEvent
import javafx.scene.input.TransferMode
import tornadofx.*
import java.util.*


class MainViewModel(mainView: MainView) {
    val mainView = mainView

    var leftLineList: List<String> = emptyList()
    var rightLineList: List<String> = emptyList()

    val leftFileLinesProperty = SimpleStringProperty(this, "leftLines", "initial")
    var leftLines by leftFileLinesProperty
    val leftLineCountProperty = SimpleIntegerProperty(this, "leftLineCount", 0)
    var leftLineCount by leftLineCountProperty

    val rightFileLinesProperty = SimpleStringProperty(this, "rightLines", "initial")
    var rightLines by rightFileLinesProperty
    val rightLineCountProperty = SimpleIntegerProperty(this, "rightLineCount", 0)
    var rightLineCount by rightLineCountProperty

    val whichSideProperty = SimpleStringProperty(this, "whichSide", "One Side Only")
    var whichSide by whichSideProperty

    val combinedLinesProperty = SimpleStringProperty(this, "combinedLines", "initial")
    var combinedLines by combinedLinesProperty
    val combinedLineCountProperty = SimpleIntegerProperty(this, "combinedLineCount", 0)
    var combinedLineCount by combinedLineCountProperty

    val intersectLinesProperty = SimpleStringProperty(this, "intersectLines", "initial")
    var intersectLines by intersectLinesProperty
    val intersectLineCountProperty = SimpleIntegerProperty(this, "intersectLineCount", 0)
    var intersectLineCount by intersectLineCountProperty

    fun handleLeftDragOver(event: DragEvent) {
        event.acceptTransferModes(TransferMode.MOVE)
        event.consume()
    }
    fun handleLeftDragEntered(event: DragEvent) {
        println("Entered")
        event.consume()
    }
    fun handleLeftDragExited(event: DragEvent) {
        println("Exited")
        event.consume()
    }
    fun handleLeftDragDropped(event: DragEvent) {
        println("Dropped")
        val db = event.dragboard
        if (db.hasFiles())
            db.files.map { processLeftFile(it.absolutePath) }
        event.consume()
    }

    private fun processLeftFile(filename: String) {
        leftLineList = FileService.getFileLines(filename)
        val count = leftLineList.count()
        leftLines = leftLineList.joinToString(separator = "\n")
        leftLineCount = count
    }

    fun handleRightDragOver(event: DragEvent) {
        event.acceptTransferModes(TransferMode.MOVE)
        event.consume()
    }
    fun handleRightDragEntered(event: DragEvent) {
        println("Entered")
        event.consume()
    }
    fun handleRightDragExited(event: DragEvent) {
        println("Exited")
        event.consume()
    }
    fun handleRightDragDropped(event: DragEvent) {
        println("Dropped")
        val db = event.dragboard
        if (db.hasFiles())
            db.files.map { processRightFile(it.absolutePath) }
        event.consume()
    }

    private fun processRightFile(filename: String) {
        rightLineList = FileService.getFileLines(filename)
        val count = rightLineList.count()
        rightLines = rightLineList.joinToString(separator = "\n")
        rightLineCount = count
    }

    fun handleLeftOnly() {
        println("LEFT ONLY")
        whichSide = "LEFT side only"
        buildCombinedLines(leftLineList, rightLineList)
    }

    fun handleRightOnly() {
        println("RIGHT ONLY")
        whichSide = "RIGHT side only"
        buildCombinedLines(rightLineList, leftLineList)
    }

    fun handleInBoth() {
        println("IN BOTH")
        buildIntersectLines(leftLineList, rightLineList)
    }

    private fun buildCombinedLines(haystackList: List<String>, needleList: List<String>) {
        // Much better performance by using HashSet and Except
        //
        val haystack = HashSet<String>(haystackList)
        val needles = HashSet<String>(needleList)

        haystack.removeAll(needles)
        combinedLineCount = haystack.count()
        combinedLines = haystack.joinToString(separator = "\n")
    }

    private fun buildIntersectLines(haystackList: List<String>, needleList: List<String>) {
        // Much better performance by using HashSet and Except
        //
        val haystack = HashSet<String>(haystackList)
        val needles = HashSet<String>(needleList)

        val result = haystack.intersect(needles)
        intersectLineCount = result.count()
        intersectLines = result.joinToString(separator = "\n")
    }
}
