package core.configuration

/**
 * Абстракция нативных компонентов
 * @author Панков Вася (pank-su)
 */
expect abstract class ConfigurationPlatform() : Configuration {


    /**
     * Абстракция для desktop
     * @author Панков Вася (pank-su)
     */
    open fun render()
}