package models

/**
 * Элемент дерева
 * @param name Название элемента
 * @param treeList Список дочерних элементов
 * @author Белоцерковский Марат (MIAPROT)
 * @author Михаил Куриличев (Qwan-Chi)
 */
data class TreeDTO(var name: String, var treeList: List<TreeDTO>)