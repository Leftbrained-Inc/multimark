# Описание конфигурации и примеры

```kt
fun main() {
    config {
        logo {
            Image(painterResource("Good Tick.svg"), null, modifier = it, contentScale = ContentScale.FillHeight)
        }

        scale = 1f
        fontScale = 1f

        window {
            icon = useResource("Good Tick.svg") { loadSvgPainter(it, Density(100f)) }
            title = "Test"
        }

        keyMap {
            KeyMap.metaKey = { it.isCtrlPressed }
            testShortcut = Shortcut({ keyEvent -> keyEvent.isAltPressed }) {
                println("Test")
            }
        }

        launchScreen {
            filesMode = FilesMode.Both

            recentFilter {
                true
            }
        }
    }
}
```

## Параметры

* `logo` - `@Composable`-элемент, представляющий логотип на главном экране.
  Также можно добавить `Modifier` для изменения размеров и прочего.
* `scale` и `fontScale` - стандартное масштабирование элементов, которое влияет на все экраны.
  Измеряется в о относительных единицах, где `1f` - 100% масштаба, стандартное значение.
* `title` и `icon` - параметры окна (название и логотип)
* `keyMap` - параметры раскладки клавиатуры для приложение
* `metaKey` - клавиша, являющаяся основной сочетаний горячих клавиш, может быть любым элементом класса `Key`.

> В дальнейшем конфигурация будет расширена, следите за изменениями
