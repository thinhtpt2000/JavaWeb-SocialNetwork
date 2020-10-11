/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.listener;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;

/**
 * Web application lifecycle listener.
 *
 * @author ThinhTPT
 */
public class MyServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // initialize log4j here
        ServletContext context = sce.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

        PropertyConfigurator.configure(fullPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
