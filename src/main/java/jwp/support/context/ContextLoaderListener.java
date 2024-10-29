package jwp.support.context;

import core.jdbc.ConnectionManager;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    //웹 시작 시 jwp.sql 스크립트를 실행하여 db를 초기화하는 리스터
    private static final Logger logger = Logger.getLogger(ContextLoaderListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));     //해당 sql문을 모두 실행 > db 초기화
        ConnectionManager.getDataSource();
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());

        logger.info("Completed Load ServletContext!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}