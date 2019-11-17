package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.member.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
//    
//    // �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
    private Map<String, Controller> mappings = new HashMap<String, Controller>();
//
    public void initMapping() {
//    	// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
//        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/member/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/member/login", new LoginController());
        mappings.put("/member/logout", new LogoutController());
        mappings.put("/member/list", new ListUserController());
        mappings.put("/member/view", new ViewUserController());
        mappings.put("/member/register/form", new ForwardController("/user/registerForm.jsp"));
        mappings.put("/member/register", new RegisterUserController());
        mappings.put("/member/update/form", new UpdateUserFormController());
        mappings.put("/member/update", new UpdateUserController());
        mappings.put("/member/delete", new DeleteUserController());
//        
//        logger.info("Initialized Request Mapping!");
    }
//
    public Controller findController(String uri) {	
//    	// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
        return mappings.get(uri);
    }
}
