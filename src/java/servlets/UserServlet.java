package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.UserService;
import services.RoleService;
import models.User;
import models.Role;
/**
 * @author Chris Wang
 * @version 1.0
 * @date 2022-10-23
 */
public class UserServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        User user = null;
        String action = request.getParameter("action");
        try{

            if (action!=null && action.equals("edit")){

                String email = request.getParameter("email").replace(" ", "+");
                user = userService.get(email);
                if (user == null) System.out.println("I am null");
                request.setAttribute("edit_email",user.getEmail());
                request.setAttribute("edit_firstname",user.getFirst_name());
                request.setAttribute("edit_lastname",user.getLast_name());
                request.setAttribute("edit_password",user.getPassword());
            }
            if (action!=null && action.equals("delete")){
                String email = request.getParameter("email").replace(" ", "+");
                userService.delete(email);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            List<User> users = userService.getAll();
            request.setAttribute("users",users);
            List<Role> roles = roleService.getAll();
            request.setAttribute("roles",roles);
        }catch (Exception e){
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        String action = request.getParameter("action");

        if (action != null && action.equals("add")){
            String email = request.getParameter("add_email");
            Boolean.parseBoolean(request.getParameter("add_active"));
            String firstname = request.getParameter("add_firstname");
            String lastname = request.getParameter("add_lastname");
            String password = request.getParameter("add_password");
            String role = request.getParameter("add_role");

            int role_id = 0;
            try {
                if (role.equals("system admin")){
                    role_id = 1;
                }else if (role.equals("regular user")) {
                    role_id = 2;
                }else if (role.equals("company admin")){
                    role_id = 3;
                }
            }catch (Exception e){
                e.printStackTrace();
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                return;
            }

            boolean active = request.getParameter("add_active") != null;

            try {
                if (userService.get(email) != null) {
                    request.setAttribute("err_msg", "User already exists");
                    List<User> users = userService.getAll();
                    request.setAttribute("users",users);
                    List<Role> roles = roleService.getAll();
                    request.setAttribute("roles",roles);
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                userService.insert(email,active,firstname,lastname,password,role_id);
                List<User> users = userService.getAll();
                request.setAttribute("users",users);
                List<Role> roles = roleService.getAll();
                request.setAttribute("roles",roles);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if (action != null && action.equals("edit")) {

            String email = request.getParameter("edit_email");
            Boolean.parseBoolean(request.getParameter("edit_active"));
            String firstname = request.getParameter("edit_firstname");
            String lastname = request.getParameter("edit_lastname");
            String password = request.getParameter("edit_password");
            String role = request.getParameter("edit_role");

            int role_id = 0;
            try {
                if (role.equals("system admin")){
                    role_id = 1;
                }else if (role.equals("regular user")) {
                    role_id = 2;
                }else if (role.equals("company admin")){
                    role_id = 3;
                }else{
                    request.setAttribute("edit_err_msg","Please select a role");
                }
            }catch (Exception e){
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                return;
            }

            boolean active = request.getParameter("edit_active") != null;

            try {
                if (userService.get(email) == null) {
                    request.setAttribute("edit_err_msg", "User does not exist");
                    List<User> users = userService.getAll();
                    request.setAttribute("users",users);
                    List<Role> roles = roleService.getAll();
                    request.setAttribute("roles",roles);
                    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                userService.update(email,active,firstname,lastname,password,role_id);
                List<User> users = userService.getAll();
                request.setAttribute("users",users);
                List<Role> roles = roleService.getAll();
                request.setAttribute("roles",roles);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
    }
}

