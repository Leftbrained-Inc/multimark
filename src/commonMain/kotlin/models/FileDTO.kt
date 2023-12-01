package models

import kotlinx.datetime.LocalDateTime

/**
 * Модель для карточки
 * @property name Название файла
 * @property date Дата последнего обращения к файлу
 * @author Белоцерковский Марат (MIAPROT)
 */
data class FileDTO(val name: String, val date: LocalDateTime, val path: String)
