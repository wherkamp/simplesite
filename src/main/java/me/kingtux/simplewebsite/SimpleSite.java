package me.kingtux.simplewebsite;

import io.javalin.Javalin;
import me.kingtux.javalinvc.JavalinVC;
import me.kingtux.javalinvc.WebsiteRules;
import me.kingtux.tuxorm.Dao;
import me.kingtux.tuxorm.TOConnection;
import org.simplejavamail.MailException;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * I promise you this is useful.
 */
public class SimpleSite {
    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleSite.class);
    private JavalinVC javalinVC;
    private TOConnection connection;
    private Mailer mailer;

    public SimpleSite(JavalinVC javalinVC, TOConnection connection) {
        this.javalinVC = javalinVC;
        this.connection = connection;
    }

    public SimpleSite(JavalinVC javalinVC, TOConnection connection, Mailer mailer) {
        this.javalinVC = javalinVC;
        this.connection = connection;
        this.mailer = mailer;
        if(mailer==null){
            LOGGER.warn("Mailer has been disabled. Due to lack of credentials");
        }
        if(connection==null){
            LOGGER.warn("TuxORM has been disabled. Due to lack of credentials");

        }
    }

    public JavalinVC registerController(Object controller) {
        return javalinVC.registerController(controller);
    }

    public Javalin getJavalin() {
        return javalinVC.getJavalin();
    }

    public WebsiteRules getRules() {
        return javalinVC.getRules();
    }

    public void stop() {
        javalinVC.stop();
    }

    public boolean isRunning() {
        return javalinVC.isRunning();
    }

    public String route(String path) {
        return javalinVC.route(path);
    }

    public JavalinVC getJavalinVC() {
        return javalinVC;
    }

    public TOConnection getConnection() {
        return connection;
    }

    public Mailer getMailer() {
        return mailer;
    }

    public <T, I> Dao<T, I> createDao(Class<T> type) {
        return connection.createDao(type);
    }

    public <T, I> Dao<T, I> createDao(T type) {
        return connection.createDao(type);
    }

    public void registerClass(Class<?> type) {
        connection.registerClass(type);
    }

    public void sendMail(Email email) {
        mailer.sendMail(email);
    }

    public void sendMail(Email email, boolean async) {
        mailer.sendMail(email, async);
    }

    public boolean validate(Email email) throws MailException {
        return mailer.validate(email);
    }
}
