package core.extensions

import java.io.File
import javax.swing.JFileChooser

/** Выбор файла с установленным расширением
 *
 * @param extension Расширение файла
 * @return Выбранный файл или директория
 * @author Сергей Рейнн (bulkabuka)
 */
fun pickFile(extension: String = ""): File? {
    val fileChooser = JFileChooser()
    fileChooser.fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES
    fileChooser.fileFilter = object : javax.swing.filechooser.FileFilter() {
        override fun accept(f: File): Boolean {
            return f.isDirectory || f.name.endsWith(".$extension")
        }

        override fun getDescription(): String {
            return "*.$extension"
        }
    }
    val result = fileChooser.showOpenDialog(null)
    return if (result == JFileChooser.APPROVE_OPTION) {
        fileChooser.selectedFile
    } else {
        null
    }
}