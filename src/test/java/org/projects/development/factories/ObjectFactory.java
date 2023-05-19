package org.projects.development.factories;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.projects.development.pages.LoginPage;

import java.lang.reflect.Constructor;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ObjectFactory {

    private static class DropboxFactoryHolder {
        private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();
    }

    private final ConcurrentHashMap<Class<?>, Object> CACHE;

    private ObjectFactory() {
        this.CACHE = new ConcurrentHashMap<>();
        System.setProperty("webdriver.gecko.drivers", "src/test/resources/drivers/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        this.CACHE.put(WebDriver.class, driver);
        this.CACHE.put(LoginPage.class, new LoginPage(driver));
        log.info("ObjectFactory initialized");
    }

    public static void init() {
        log.info("Initializing ObjectFactory");
    }

    public static <T> T getObject(Class<T> clazz) {
        if (!DropboxFactoryHolder.OBJECT_FACTORY.CACHE.containsKey(clazz)) {
            log.error("Object of class {} is not registered", clazz.getName());
            Constructor<?>[] constructors = clazz.getConstructors();
            checkIfTheObjectCanBeCreatedAndCreate(clazz, constructors);
        }
        log.info("Returning object of class {}", clazz.getName());
        return clazz.cast(DropboxFactoryHolder.OBJECT_FACTORY.CACHE.get(clazz));
    }

    private static <T> void checkIfTheObjectCanBeCreatedAndCreate(Class<T> clazz, Constructor<?>[] constructors) {
        if (constructors.length == 0) {
            log.error("Class {} has no public constructors", clazz.getName());
        } else {
            log.error("Class {} has following public constructors:", clazz.getName());
            for (Constructor<?> constructor : constructors) {
                log.error("{}", constructor);
            }
            if (constructors.length > 1) {
                log.error("Class {} has more than one public constructor", clazz.getName());
                throw new RuntimeException("Class " + clazz.getName() + " has more than one public constructor");
            }
            createNewObject(clazz, constructors);
        }
    }

    @SneakyThrows
    private static <T> void createNewObject(Class<T> clazz, Constructor<?>[] constructors) {
        log.error("Class {} has only one public constructor, so it will be used", clazz.getName());
        Constructor<?> constructor = constructors[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        log.error("Constructor {} has following parameters:", constructor);
        Object[] parameters;
        log.error("Constructor {} has {} parameters", constructor, parameterTypes.length);
        parameters = Arrays.stream(parameterTypes)
                .parallel()
                .map(ObjectFactory::getObject)
                .toArray();
        DropboxFactoryHolder.OBJECT_FACTORY.CACHE.put(clazz, constructor.newInstance(parameters));
    }

    public static void addObjectOrReplaceExisting(Class<?> clazz, Object object) {
        log.info("Adding object of class {} to the cache", clazz.getName());
        DropboxFactoryHolder.OBJECT_FACTORY.CACHE.put(clazz, object);
    }

    public static void closeDriver() {
        log.info("Closing driver");
        WebDriver driver = (WebDriver) DropboxFactoryHolder.OBJECT_FACTORY.CACHE.get(WebDriver.class);
        driver.quit();
    }
}
