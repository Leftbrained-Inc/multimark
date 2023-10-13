![Баннер проекта](images/Banner.png)

# Multimark

**Multimark** — особенный редактор Markdown, находящийся в разработке. Он предлагает полную поддержку различных
тегов из CommonMark, а также расширения от GitHub и других сервисов. При этом он не ограничивается
только ими, вы сможете добавить собственные теги и реализации с помощью встроенной конфигурации.

Редактор пишется с помощью `Compose Multiplatform + Kotlin`. Это позволяет использовать один и тот
же код для разнообразных платформ, сейчас он разрабатывается в первую очередь как настольное
приложение.

Скрафчен с :heart: в `Leftbrained`!

[comment]: <> (Необходимо обновлять по степени изменения во время разработки)

## Структура проекта

> Обновляется по мере разработки

```plantuml
@startuml
package "Native Startup"{
folder desktopApp{
[build.gradle.kts] --> [main.kt]
}
}
folder "Shared"{
folder commonMain {
[main.desktop.kt]
folder core
folder ui
folder resources
folder models

     core --> [main.desktop.kt]
     ui --> [main.desktop.kt]
     resources --> [main.desktop.kt]
     models --> [main.desktop.kt]
}
folder desktopMain{
[App.kt]
}
desktopMain --> commonMain :  осуществляет
}

Shared --> "Native Startup"

legend
В commonMain - код который работает на всех системах.
При этом для каждой системы можно одни и теже
функции реализовать по разному в своих файлах Shared.
end legend
@enduml
```

![Диаграмма структуры проекта в PlantUML](images/structure.jpg)

<figcaption>Диаграмма структуры проекта в PlantUML</figcaption>

<br/>
<br/>

## Дорожная карта

![Дорожная карта](images/roadmap.png)

## Контрибьютинг

Последовательность действий и правила представлены в [CONTRIBUTING](CONTRIBUTING.md)
