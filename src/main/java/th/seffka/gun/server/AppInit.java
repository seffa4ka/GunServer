package th.seffka.gun.server;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import th.seffka.gun.server.config.WebConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInit implements WebApplicationInitializer {

    private final static String DISPATCHER = "dispatcher";

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext gunContext = new AnnotationConfigWebApplicationContext();
        gunContext.register(WebConfig.class);
        servletContext.addListener(new ContextLoaderListener(gunContext));

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER, new DispatcherServlet(gunContext));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

}
