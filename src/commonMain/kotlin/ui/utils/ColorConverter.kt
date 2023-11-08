package ui.utils

@Deprecated("Сделать круто")
inline val ULong.hex: String
    get() = String.format("#%06X", 0xFFFFFF and this.toInt())