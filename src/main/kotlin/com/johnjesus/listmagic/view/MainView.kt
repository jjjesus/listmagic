package com.johnjesus.listmagic.view

import com.johnjesus.listmagic.app.MainViewModel
import com.johnjesus.listmagic.app.Styles
import tornadofx.*


class MainView : View("ListMagic") {
    override val root = vbox()

    val mainViewModel = MainViewModel(this)

    init {
        with(root) {
            minWidth = 900.0
            minHeight = 800.0

            addStylesheet((Styles::class))

            label(title) {
                addClass(Styles.heading)
            }
            hbox {
                // Left side
                //
                vbox {
                    hbox {
                        addClass(Styles.dropzone)
                        setOnDragOver { event -> mainViewModel.handleLeftDragOver(event) }
                        setOnDragEntered { event -> mainViewModel.handleLeftDragEntered(event) }
                        setOnDragExited { event -> mainViewModel.handleLeftDragExited(event) }
                        setOnDragDropped { event -> mainViewModel.handleLeftDragDropped(event) }
                        text {
                            text = "Drop LEFT file here"
                        }
                    }
                    text {
                        bind(mainViewModel.leftLineCountProperty)
                    }
                    scrollpane {
                        minHeight = 320.0
                        textarea {
                            minHeight = 320.0
                            bind(mainViewModel.leftFileLinesProperty)
                        }
                    }
                }

                // Right side
                //
                vbox {
                    hbox {
                        addClass(Styles.dropzone)
                        setOnDragOver { event -> mainViewModel.handleRightDragOver(event) }
                        setOnDragEntered { event -> mainViewModel.handleRightDragEntered(event) }
                        setOnDragExited { event -> mainViewModel.handleRightDragExited(event) }
                        setOnDragDropped { event -> mainViewModel.handleRightDragDropped(event) }
                        text {
                            text = "Drop RIGHT file here"
                        }
                    }
                    text {
                        bind(mainViewModel.rightLineCountProperty)
                    }
                    scrollpane {
                        minHeight = 320.0
                        textarea {
                            minHeight = 320.0
                            bind(mainViewModel.rightFileLinesProperty)
                        }
                    }
                }
            }

            // Bottom
            //
            hbox {
                paddingTop = 30.0
                button {
                    text = "Left Only"
                    action { mainViewModel.handleLeftOnly() }
                }
                button {
                    text = "Right Only"
                    action { mainViewModel.handleRightOnly() }
                }
                button {
                    text = "In BOTH"
                    action { mainViewModel.handleInBoth() }
                }
            }
            hbox {
                vbox {
                    label {
                        addClass(Styles.heading)
                        bind(mainViewModel.whichSideProperty)
                    }
                    vbox {
                        text {
                            bind(mainViewModel.combinedLineCountProperty)
                        }
                        scrollpane {
                            minHeight = 320.0
                            textarea {
                                minHeight = 320.0
                                bind(mainViewModel.combinedLinesProperty)
                            }
                        }
                    }
                }
                vbox {
                    label("In BOTH") {
                        addClass(Styles.heading)
                    }
                    text {
                        bind(mainViewModel.intersectLineCountProperty)
                    }
                    scrollpane {
                        minHeight = 320.0
                        textarea {
                            minHeight = 320.0
                            bind(mainViewModel.intersectLinesProperty)
                        }
                    }
                }
            }
        }
    }
}
