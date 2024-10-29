package jwp.util;

import jwp.model.User;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    //세션에서 사용자 정보를 얻고 로그인 여부를 확인하는 유틸리티
    public static final String USER_SESSION_KEY = "user";

    public static User getUserFromSession(HttpSession session){     //세션에서 사용자 객체를 반환
        Object user = session.getAttribute(USER_SESSION_KEY);
        if(user == null){
            return null;
        }
        return (User) user;
    }

    public static boolean isLogined(HttpSession session){           //사용자가 로그인했는지 여부를 확인
        return getUserFromSession(session) != null;
    }
}
