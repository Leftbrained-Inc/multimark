# Пример возможной конфигурации

```kotlin
fun main(){
    configuration{
        this.theme = MaterialTheme()
        logo{
            
        }
        name{
            
        }
        markdown{
            theme{
                
            }
            
            image{
                
            }
            exportToPdf{
                
            }
            ui{
                
            }
        }
        startScreen = StartScreen.Menu
        menuScreen{
            MenuScreen{
                buttons{
                    
                }
            }
        }
        recentScreen{
            RecentScreen{
                recentSettings{
                    recentFilter{
                        file.contains(".md")
                    }
                }
            }
        }
        keymap {
            keymap = "GNOME"
            meta = Key.L
        }
    }
}
```

