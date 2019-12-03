package controller.recipe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.Ingredient;
import model.Procedure;
import model.Recipe;
import model.service.IngredientManager;
import model.service.MemberManager;
import model.service.RecipeManager;

public class AddRecipeController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(AddRecipeController.class);
	String filename = "";
	String filename1 = "";
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {	
    		// GET request: 레시피 추가 form 요청	
    		// 원래는 AddRecipeFormController가 처리하던 작업을 여기서 수행
    		String category_id = request.getParameter("category_id");

    		
    		log.debug("AddForm(Recipe) Request : {}", category_id);
    		
			// 현재 로그인한 사용자 ID를 request에 저장하여 전달
			request.setAttribute("curMemberId", 
					MemberSessionUtils.getLoginMemberId(request.getSession()));		
			request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
    		request.setAttribute("category_id", category_id); // recipe/addForm
    		return "/recipe/addForm.jsp";
	    }	
		
		/* 사용자로부터 입력받아온 재료 정보와 조리 과정을 recipe객체의 멤버변수에 맞게 */
//		String[] rimg = request.getParameterValues("resultimg");
//		String[] iname = request.getParameterValues("iname");
//		String[] quantity = request.getParameterValues("quantity");
//		String[] procText = request.getParameterValues("proc_text");
//		String[] procId = request.getParameterValues("proc_id");
//		String[] img_url = request.getParameterValues("img_url");
		List<String> iname = new ArrayList<>();
		List<String> quantity = new ArrayList<>();
		List<String> procText = new ArrayList<>();
		List<String> procId = new ArrayList<>();
		List<String> img_url = new ArrayList<>();
		String category_id = "";
		String rname = "";
		String time = "";
		String path = "";
		
		File dir = null;
		/*파일업로드 과정*/
//		String uploadPath = request.getRealPath("upload");
		boolean check = ServletFileUpload.isMultipartContent(request);
		//전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크한다.
		//만약 multipart가 아니라면 파일 전송을 처리하지 않는다.
		
		if(check) {//파일 전송이 포함된 상태가 맞다면
			ServletContext context = request.getServletContext();
			path = context.getRealPath("upload");
			dir = new File(path);
			if(!dir.exists()) dir.mkdir();
			//전송된 파일을 저장할 실제 경로를 만든다.
			
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
                //파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
                factory.setSizeThreshold(10 * 1024);
                //메모리에 한번에 저장할 데이터의 크기를 설정한다.
                //10kb 씩 메모리에 데이터를 읽어 들인다.
                factory.setRepository(dir);
                //전송된 데이터의 내용을 저장할 임시 폴더를 지정한다.                
    
                ServletFileUpload upload = new ServletFileUpload(factory);
                //Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
                upload.setSizeMax(10 * 1024 * 1024);
                //업로드 될 파일의 최대 용량을 10MB까지 전송 허용한다.
                upload.setHeaderEncoding("EUC-KR");
                //업로드 되는 내용의 인코딩을 설정한다.
                                
                List items = (List)upload.parseRequest(request);
                //upload 객체에 전송되어 온 모든 데이터를 Collection 객체에 담는다.
                for(int j = 0; j < items.size(); j++) {
                	FileItem item = (FileItem)items.get(j);
                	//commons-fileupload를 사용하여 전송받으면 
                	//모든 parameter는 FileItem 클래스에 하나씩 저장된다.
                	System.out.println("for");
                	String value = item.getString("utf-8");
                	//넘어온 값에 대한 한글 처리를 한다.
                	
                	if(item.isFormField()) {//일반 폼 데이터라면...        
                		System.out.println("ss");
                		if(item.getFieldName().equals("category_id")) category_id = value;
//                		//key 값이 name이면 name 변수에 값을 저장한다.
                		else if(item.getFieldName().equals("rname")) rname = value;
                		else if(item.getFieldName().equals("quantity")) quantity.add(value);
                		else if(item.getFieldName().equals("proc_text")) procText.add(value);
                		else if(item.getFieldName().equals("iname")) iname.add(value);
                		else if(item.getFieldName().equals("proc_id")) procId.add(value);
                		else if(item.getFieldName().equals("img_url")) img_url.add(value);
//                		//key 값이 id이면 id 변수에 값을 저장한다.
                		else if(item.getFieldName().equals("time")) time = value;
//                		//key 값이 pw이면 pw 변수에 값을 저장한다.
                	}
                	else {//파일이라면...
                		System.out.println("file"+item.getFieldName());
                		if(item.getFieldName().equals("resultimg")) {
                		//key 값이 picture이면 파일 저장을 한다.
                			filename = item.getName();//파일 이름 획득 (자동 한글 처리 됨)
                			System.out.println("gg"+filename);
                			if(filename == null || filename.trim().length() == 0) continue;
                			//파일이 전송되어 오지 않았다면 건너 뛴다.
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			//파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
                			//실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드이다.
                			File file = new File(dir, filename);
                			item.write(file);
                			//파일을 upload 경로에 실제로 저장한다.
                			//FileItem 객체를 통해 바로 출력 저장할 수 있다.
                		}
                	}
                }
                
			}catch(SizeLimitExceededException e) {
			//업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
				e.printStackTrace();           
            }catch(FileUploadException e) {
            //파일 업로드와 관련되어 발생할 수 있는 예외 처리
                e.printStackTrace();
            }catch(Exception e) {            
                e.printStackTrace();
            }
		}
		
		/* POST (/recipe/addForm.jsp에서 레시피 등록버튼 누른 후 폼 입력 값 전송 request*/
		
		/* writer 설정 위해 */
		MemberManager mManager = MemberManager.getInstance();
		String email_id = MemberSessionUtils.getLoginMemberId(request.getSession());
		int writerId = mManager.findMember(email_id).getMember_id();
		String writer = mManager.findMember(email_id).getMname();
		Date nowTime = new Date();
		
		
		
		IngredientManager imanager = IngredientManager.getInstance();
		List<Ingredient> iList = new ArrayList<>();
		for (int i = 0; i < iname.size(); i++) {
			Ingredient ingredient = new Ingredient();
			if (iname.get(i) == null || iname.get(i).trim().equals("")) {	// ""만 들어올 경우를 방지
				continue;
			}
			ingredient.setIngredient_id(imanager.findIdByName(iname.get(i)));
			ingredient.setQuantity(quantity.get(i));
			iList.add(ingredient);
		}

		/* 조리 과정들의 배열 */
		List<Procedure> pList = new ArrayList<>();
//		for (int i = 0; i < procText.length; i++) {
//			Procedure proc = new Procedure(); 
//			if (procId[i] == null || procId[i].trim().equals("")) {	// ""만 들어올 경우를 방지
//				continue;
//			}
//			proc.setProc_Id(Integer.valueOf(procId[i]));
//			proc.setText(procText[i]);
//			proc.setImg_url(img_url[i]);
//			pList.add(proc);
//		}
		for (int i = 0; i < procText.size(); i++) {
			Procedure proc = new Procedure(); 
			if (procId.get(i) == null || procId.get(i).trim().equals("")) {	// ""만 들어올 경우를 방지
				continue;
			}
			proc.setProc_Id(Integer.valueOf(procId.get(i)));
			proc.setText(procText.get(i));
			if(img_url.size()>i)
				proc.setImg_url(img_url.get(i));
			pList.add(proc);
		}
		
		/* 조리 과정을 proc_id를 기준으로 오름차순으로 정렬*/
		pList.sort(new Comparator<Procedure>() {

			@Override
			public int compare(Procedure arg0, Procedure arg1) {
				// TODO Auto-generated method stub
				 int age0 = arg0.getProc_Id();
                 int age1 = arg1.getProc_Id();
                 if (age0 == age1)
                       return 0;
                 else if (age0 > age1)
                       return 1;
                 else
                       return -1;
			}
			
		});
		
		String p = path+"\\"+filename;
		p = p.replace("\\", "/");
		System.out.println(p);
		
		/* request로 받아온 parameter들로 recipe 객체 생성*/
		Recipe recipe = new Recipe(
				0, //recipe_id는 DAO에서 시퀀스로 설정. 그래서 필요 X.
//				Integer.parseInt(request.getParameter("category_id")),
				Integer.parseInt(category_id),
//				request.getParameter("rname"),
				rname,
//				Integer.parseInt(request.getParameter("time")),
				Integer.parseInt(time),
//				request.getParameter("resultimg"),
				"../upload/"+filename,
				0,
				pList,
				iList,
				null,
				writer,
				nowTime
		);

		log.debug("Create Recipe : {}", recipe);

		RecipeManager rmanager = RecipeManager.getInstance();
		int recipe_id = rmanager.create(recipe, writerId);
		
		recipe = rmanager.findRecipe(recipe_id);
		request.setAttribute("recipe", recipe);
		request.setAttribute("memberName", writer);
		request.setAttribute("curMemberId", email_id);	
		return "/recipe/view.jsp"; // 성공 시 작성한 레시피 보기 jsp로 redirect

	}
}
