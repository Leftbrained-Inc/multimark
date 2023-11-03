package ui.utils

class ColorConverter {
    fun longToHex(color: Long): String {
        return String.format("#%06X", 0xFFFFFF and color.toInt())
    }
}