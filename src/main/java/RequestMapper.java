import controller.*;

import java.net.URL;

public class RequestMapper {

    public static Controller map(String url) {
        if (url.equals(URLs.HOME.getUrl())) {
            return new HomeController();
        }
        if (url.equals(URLs.SIGNUP.getUrl())) {
            return new CreateUserController();
        }
        if (url.equals(URLs.LOGIN.getUrl())) {
            return new LoginController();
        }
        if (url.equals(URLs.LOGOUT.getUrl())) {
            return new LogoutController();
        }
        if (url.equals(URLs.LIST.getUrl())) {
            return new ListUserController();
        }
        if (url.equals(URLs.UPDATE.getUrl())) {
            return new UpdateUserFormController();
        }
        return null;
    }
}
