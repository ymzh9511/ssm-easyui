package ssm.controller;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.domain.PageBean;
import ssm.domain.QueryVo;
import ssm.domain.User;
import ssm.service.UserService;
import ssm.utils.ResponseUtil;
import ssm.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/list")
    private @ResponseBody
    QueryVo list(@RequestParam(value = "page", required = false) String page,
                 @RequestParam(value = "rows", required = false) String rows,
                 User s_user, HttpServletResponse res) throws Exception {
        if (page == null || rows == null) {
            page = "1";
            rows = "1";
        }
        PageBean pagebean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", StringUtil.formatLike(s_user.getName()));
        map.put("start", pagebean.getStart());
        map.put("size", pagebean.getPageSize());
        List<User> list = userService.find(map);
        Long count = userService.getTotal(map);
//        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(list);
        QueryVo vo = new QueryVo();
        vo.setRows(jsonArray);
        vo.setTotal(count);
//        jsonObject.put("rows", jsonArray);
//        jsonObject.put("total", count);
//        ResponseUtil.write(res, jsonObject);
//        List<User> list = userService.list();
        return vo;
    }

    @RequestMapping("/listone")
    private String findById() {
        User user = userService.findById(1);
        System.out.println("user");
        return "";
    }
    @RequestMapping("save")
    public @ResponseBody QueryVo save(User user,HttpServletResponse response) throws Exception {
         userService.save(user);
            QueryVo vo = new QueryVo();
            vo.setSuccess("success");
//         JSONObject json = new JSONObject();
//         json.put("success","success");
//         ResponseUtil.write(response,json);
         return vo;
    }
    //修改有问题
    @RequestMapping("update")
    public @ResponseBody QueryVo update(User user,HttpServletResponse response) throws  Exception{
        User user2 = userService.findById(user.getId());
        QueryVo vo = new QueryVo();
        if (user2!=null){
            user2.setName(user.getName());
            user2.setDes(user.getDes());
            user2.setUsername(user.getUsername());
            user2.setPassword(user.getPassword());
        }
        int count = userService.update(user2);
        if (count>0){
                vo.setSuccess("success");
//            JSONObject json = new JSONObject();
//            json.put("success", "success");
//            ResponseUtil.write(response,json);
        }
        return vo;
    }
    @RequestMapping("delete")
    public @ResponseBody QueryVo delete(int[] ids,HttpServletResponse res) throws  Exception{
        userService.delete(ids);
        QueryVo vo = new QueryVo();
        vo.setResult(true);
//        JSONObject json = new JSONObject();
//        json.put("result",true);
//        ResponseUtil.write(res,json);
        return vo;
    }

    @RequestMapping("/login")
    public @ResponseBody QueryVo login(User user, HttpServletRequest request,HttpServletResponse response) throws Exception {
        User resultUser = userService.login(user);
        JSONObject json = new JSONObject();
        QueryVo vo = new QueryVo();
        if (resultUser == null) {
            request.setAttribute("user", resultUser);
            request.setAttribute("errorMsg", "用户名或密码错误");

            vo.setResult(false);
//            json.put("result",false);
//            ResponseUtil.write(response,json);

        } else {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", resultUser);

            vo.setResult(true);
//            json.put("result",true);
//            ResponseUtil.write(response,json);

        }
        return vo;
    }
//    @RequestMapping("/logout")

    @RequestMapping("index")
    public String index(){
        return "user/user";

    }

}
