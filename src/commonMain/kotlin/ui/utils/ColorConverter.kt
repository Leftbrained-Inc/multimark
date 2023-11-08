package ui.utils

inline val ULong.hex: String
    get() = String.format("#%06X", 0xFFFFFF and this.toInt())