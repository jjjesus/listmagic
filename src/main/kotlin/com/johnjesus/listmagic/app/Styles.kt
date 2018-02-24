package com.johnjesus.listmagic.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val dropzone by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        dropzone {
            borderColor += box(
                    top = Color.RED,
                    right = Color.DARKGREEN,
                    left = Color.ORANGE,
                    bottom = Color.RED
            )
            padding = box(40.px)
        }
    }
}
