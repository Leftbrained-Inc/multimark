package core.configuration

/**
 * Абстракция нативных компонентов
 * @author Василий Панков (pank-su)
 */
expect abstract class ConfigurationPlatform() : Configuration {

    /**
     * Абстракция для desktop
     * @author Василий Панков (pank-su)
     */
    open fun render()
}