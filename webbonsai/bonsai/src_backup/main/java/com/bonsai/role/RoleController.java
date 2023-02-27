package com.bonsai.role;

import com.bonsai.auth.AuthService;
import com.bonsai.common.Contants;
import com.bonsai.common.Response;
import com.bonsai.core.dao.ResultPaging;
import com.bonsai.role.model.RequestSearchRole;
import com.bonsai.role.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/manage/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public Response getAll(HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "ROLE:VIEW");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            List<Role> result = roleService.getAll();
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @GetMapping("/getById/{id}")
    public Response getById(@PathVariable Long id, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "ROLE:VIEW");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            Role result= roleService.getById(id);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @GetMapping("/search")
    public Response search(@RequestParam RequestSearchRole requestSearch, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "ROLE:VIEW");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            ResultPaging<Role> result = roleService.search(requestSearch);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @PostMapping("/create")
    public Response createRole(@RequestBody Role role, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "ROLE:CREATE");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            Role result = roleService.createRole(role);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @PutMapping ("/update")
    public Response updateRole(@RequestBody Role role, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "ROLE:UPDATE");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            Role result = roleService.updateRole(role);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "ROLE:DELETE");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            roleService.delete(id);
            return Response.createResponseSuccess(null);
        }else return resultCheck;
    }
}
