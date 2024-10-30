import org.apache.catalina.startup.Tomcat;


import java.io.File;
import java.util.logging.Logger;

public class WebServerLauncher {
    private static final Logger logger = Logger.getLogger(WebServerLauncher.class.getName());

    public static void main(String[] args) throws Exception {
        String webappDirLocation = "./webapp/"; // 웹 애플리케이션의 루트 디렉토리를 지정 - 이 폴더에 JSP 파일, 서블릿, 정적 파일 등을 배치
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();  // Tomcat 서버의 커넥터를 초기화 - 커넥터는 클라이언트와 서버 간의 요청과 응답을 관리

        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());    // 웹 애플리케이션을 추가 - ""은 애플리케이션의 컨텍스트 경로를 의미. 빈 문자열을 사용하면 애플리케이션이 루트 경로(/)에 배치
        logger.info("configuring app with basedir: " + new File(webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await(); // 서버가 종료될 때까지 대기 상태에 있도록 함
    }
}
