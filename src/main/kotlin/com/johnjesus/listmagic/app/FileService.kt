package com.johnjesus.listmagic.app

import java.io.File

class FileService {
    companion object {
        fun getFileLines(filename: String): List<String> {
            val lineList = mutableListOf<String>()
            File(filename).useLines { lines -> lines.forEach { lineList.add(it) }}
            return lineList
        }
    }
}
