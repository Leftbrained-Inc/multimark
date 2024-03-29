# Дорожная карта и размышления

## 2023 год

- Базовый редактор и система превью (без стилизации и подсветки синтаксиса)
- Система вкладок и проектов 
- Сохранение резервной копии каждый определенный промежуток времени
- Проверка репозитория при пуше (Actions)

## 2024 год

- Кроссплатформенность
- Поддержка LaTeX и Mermaid/PlantUML
- Экспорт через Pandoc или самопис
- Оптимизация, улучшение UX
- Подсветка синтаксиса

## Система превью

Предполагается сначала реализовать предпросмотр через `WebView`, позже сделать самописную систему с пользовательской стилизацией.

## Проекты и вкладки

- Вкладки внутри проекта, допускается открыть несколько проектов в разных окнах.
- Таб может быть создан внутри папки проекта, а также сделан как `scratch`: сохраняется в любом месте (внутри или вне проекта) при нажатии сочетания клавиш.

## Подсветка синтаксиса

Система должна быть реализована как выделение разными цветами различных блоков синтаксиса, без лишних элементов предпросмотра.

## Кроссплатформенность

Реализация функционала редактора как в десктоп так и в мобильной версии

## Экспорт через Pandoc или самопис

Система должна предоставлять возможность экспорта файла через Pandoc или самопис

## Поддержка LaTeX и Mermaid/PlantUML

В системе должна быть реализована поддержка диаграмм

## Оптимизация, улучшение UX

После реализации функционала система обязана быть оптимизирована должным образом и интерфейс должен быть обновлён в соответствии с новым функцианалом
