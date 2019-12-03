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
    		// GET request: ������ �߰� form ��û	
    		// ������ AddRecipeFormController�� ó���ϴ� �۾��� ���⼭ ����
    		String category_id = request.getParameter("category_id");

    		
    		log.debug("AddForm(Recipe) Request : {}", category_id);
    		
			// ���� �α����� ����� ID�� request�� �����Ͽ� ����
			request.setAttribute("curMemberId", 
					MemberSessionUtils.getLoginMemberId(request.getSession()));		
			request.setAttribute("memberName", MemberSessionUtils.getLoginMemberName(request.getSession()));
    		request.setAttribute("category_id", category_id); // recipe/addForm
    		return "/recipe/addForm.jsp";
	    }	
		
		/* ����ڷκ��� �Է¹޾ƿ� ��� ������ ���� ������ recipe��ü�� ��������� �°� */
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
		/*���Ͼ��ε� ����*/
//		String uploadPath = request.getRealPath("upload");
		boolean check = ServletFileUpload.isMultipartContent(request);
		//���۵� �������� ���ڵ� Ÿ���� multipart ���� ���θ� üũ�Ѵ�.
		//���� multipart�� �ƴ϶�� ���� ������ ó������ �ʴ´�.
		
		if(check) {//���� ������ ���Ե� ���°� �´ٸ�
			ServletContext context = request.getServletContext();
			path = context.getRealPath("upload");
			dir = new File(path);
			if(!dir.exists()) dir.mkdir();
			//���۵� ������ ������ ���� ��θ� �����.
			
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
                //���� ���ۿ� ���� �⺻���� ���� Factory Ŭ������ �����Ѵ�.
                factory.setSizeThreshold(10 * 1024);
                //�޸𸮿� �ѹ��� ������ �������� ũ�⸦ �����Ѵ�.
                //10kb �� �޸𸮿� �����͸� �о� ���δ�.
                factory.setRepository(dir);
                //���۵� �������� ������ ������ �ӽ� ������ �����Ѵ�.                
    
                ServletFileUpload upload = new ServletFileUpload(factory);
                //Factory Ŭ������ ���� ���� ���ε� �Ǵ� ������ ó���� ��ü�� �����Ѵ�.
                upload.setSizeMax(10 * 1024 * 1024);
                //���ε� �� ������ �ִ� �뷮�� 10MB���� ���� ����Ѵ�.
                upload.setHeaderEncoding("EUC-KR");
                //���ε� �Ǵ� ������ ���ڵ��� �����Ѵ�.
                                
                List items = (List)upload.parseRequest(request);
                //upload ��ü�� ���۵Ǿ� �� ��� �����͸� Collection ��ü�� ��´�.
                for(int j = 0; j < items.size(); j++) {
                	FileItem item = (FileItem)items.get(j);
                	//commons-fileupload�� ����Ͽ� ���۹����� 
                	//��� parameter�� FileItem Ŭ������ �ϳ��� ����ȴ�.
                	System.out.println("for");
                	String value = item.getString("utf-8");
                	//�Ѿ�� ���� ���� �ѱ� ó���� �Ѵ�.
                	
                	if(item.isFormField()) {//�Ϲ� �� �����Ͷ��...        
                		System.out.println("ss");
                		if(item.getFieldName().equals("category_id")) category_id = value;
//                		//key ���� name�̸� name ������ ���� �����Ѵ�.
                		else if(item.getFieldName().equals("rname")) rname = value;
                		else if(item.getFieldName().equals("quantity")) quantity.add(value);
                		else if(item.getFieldName().equals("proc_text")) procText.add(value);
                		else if(item.getFieldName().equals("iname")) iname.add(value);
                		else if(item.getFieldName().equals("proc_id")) procId.add(value);
                		else if(item.getFieldName().equals("img_url")) img_url.add(value);
//                		//key ���� id�̸� id ������ ���� �����Ѵ�.
                		else if(item.getFieldName().equals("time")) time = value;
//                		//key ���� pw�̸� pw ������ ���� �����Ѵ�.
                	}
                	else {//�����̶��...
                		System.out.println("file"+item.getFieldName());
                		if(item.getFieldName().equals("resultimg")) {
                		//key ���� picture�̸� ���� ������ �Ѵ�.
                			filename = item.getName();//���� �̸� ȹ�� (�ڵ� �ѱ� ó�� ��)
                			System.out.println("gg"+filename);
                			if(filename == null || filename.trim().length() == 0) continue;
                			//������ ���۵Ǿ� ���� �ʾҴٸ� �ǳ� �ڴ�.
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			//���� �̸��� ������ ��ü ��α��� �����ϱ� ������ �̸� �κи� �����ؾ� �Ѵ�.
                			//���� C:\Web_Java\aaa.gif��� �ϸ� aaa.gif�� �����ϱ� ���� �ڵ��̴�.
                			File file = new File(dir, filename);
                			item.write(file);
                			//������ upload ��ο� ������ �����Ѵ�.
                			//FileItem ��ü�� ���� �ٷ� ��� ������ �� �ִ�.
                		}
                	}
                }
                
			}catch(SizeLimitExceededException e) {
			//���ε� �Ǵ� ������ ũ�Ⱑ ������ �ִ� ũ�⸦ �ʰ��� �� �߻��ϴ� ����ó��
				e.printStackTrace();           
            }catch(FileUploadException e) {
            //���� ���ε�� ���õǾ� �߻��� �� �ִ� ���� ó��
                e.printStackTrace();
            }catch(Exception e) {            
                e.printStackTrace();
            }
		}
		
		/* POST (/recipe/addForm.jsp���� ������ ��Ϲ�ư ���� �� �� �Է� �� ���� request*/
		
		/* writer ���� ���� */
		MemberManager mManager = MemberManager.getInstance();
		String email_id = MemberSessionUtils.getLoginMemberId(request.getSession());
		int writerId = mManager.findMember(email_id).getMember_id();
		String writer = mManager.findMember(email_id).getMname();
		Date nowTime = new Date();
		
		
		
		IngredientManager imanager = IngredientManager.getInstance();
		List<Ingredient> iList = new ArrayList<>();
		for (int i = 0; i < iname.size(); i++) {
			Ingredient ingredient = new Ingredient();
			if (iname.get(i) == null || iname.get(i).trim().equals("")) {	// ""�� ���� ��츦 ����
				continue;
			}
			ingredient.setIngredient_id(imanager.findIdByName(iname.get(i)));
			ingredient.setQuantity(quantity.get(i));
			iList.add(ingredient);
		}

		/* ���� �������� �迭 */
		List<Procedure> pList = new ArrayList<>();
//		for (int i = 0; i < procText.length; i++) {
//			Procedure proc = new Procedure(); 
//			if (procId[i] == null || procId[i].trim().equals("")) {	// ""�� ���� ��츦 ����
//				continue;
//			}
//			proc.setProc_Id(Integer.valueOf(procId[i]));
//			proc.setText(procText[i]);
//			proc.setImg_url(img_url[i]);
//			pList.add(proc);
//		}
		for (int i = 0; i < procText.size(); i++) {
			Procedure proc = new Procedure(); 
			if (procId.get(i) == null || procId.get(i).trim().equals("")) {	// ""�� ���� ��츦 ����
				continue;
			}
			proc.setProc_Id(Integer.valueOf(procId.get(i)));
			proc.setText(procText.get(i));
			if(img_url.size()>i)
				proc.setImg_url(img_url.get(i));
			pList.add(proc);
		}
		
		/* ���� ������ proc_id�� �������� ������������ ����*/
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
		
		/* request�� �޾ƿ� parameter��� recipe ��ü ����*/
		Recipe recipe = new Recipe(
				0, //recipe_id�� DAO���� �������� ����. �׷��� �ʿ� X.
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
		return "/recipe/view.jsp"; // ���� �� �ۼ��� ������ ���� jsp�� redirect

	}
}
