package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.member.*;
import controller.recipe.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
//    
//    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();
//
    public void initMapping() {
//    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
//        mappings.put("/main", new ForwardController("/main.jsp"));
        mappings.put("/main", new ForwardController("/member/loginForm.jsp"));
        
        mappings.put("/member/login/form", new ForwardController("/member/loginForm.jsp"));
        mappings.put("/member/login", new LoginController());
        mappings.put("/member/logout", new LogoutController());
        mappings.put("/member/list", new ListMemberController());
        mappings.put("/member/view", new ViewMemberController());
        mappings.put("/member/register/form", new ForwardController("/member/registerForm.jsp"));
        mappings.put("/member/register", new RegisterMemberController());
        mappings.put("/member/update", new UpdateMemberController());
        mappings.put("/member/update/form", new UpdateMemberController());
        mappings.put("/member/delete", new DeleteMemberController());
        
        mappings.put("/recipe/list", new ListRecipeController());
        mappings.put("/recipe/view", new ViewRecipeController());
        mappings.put("/recipe/update", new UpdateRecipeController());
//        mappings.put("", value);
//        
//        logger.info("Initialized Request Mapping!");
    }
//
    public Controller findController(String uri) {	
//    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
