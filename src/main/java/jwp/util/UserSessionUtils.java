package jwp.util;

import jwp.model.User;

import javax.servlet.http.HttpSession;

// 세션에 로그인된 사용자가 있는지 확인 / 로그인된 사용자 정보 가져오기

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "user";
    // 현재 로그인 된 세션을 가져오는 메소드...?
    public static User getUserFromSession(HttpSession session){
        Object user = session.getAttribute(USER_SESSION_KEY);
        if(user == null){
            return null;
        }
        return (User) user;
    }

    // 로그인 되어있는지 확인
    public static boolean isLogined(HttpSession session){
        return getUserFromSession(session) != null;
    }
}
