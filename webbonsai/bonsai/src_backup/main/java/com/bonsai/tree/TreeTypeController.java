package com.bonsai.tree;

import com.bonsai.auth.AuthService;
import com.bonsai.common.Contants;
import com.bonsai.common.Response;
import com.bonsai.core.dao.ResultPaging;
import com.bonsai.tree.model.RequestSearchTreeType;
import com.bonsai.tree.model.TreeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/manage/treetype")
public class TreeTypeController {
    @Autowired
    private TreeTypeService treeTypeService;

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public Response getAll(HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "TYPETREE:VIEW");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            List<TreeType> result = treeTypeService.getAll();
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @GetMapping("/getAllFree")
    public Response getAllFree(HttpServletRequest request){
        List<TreeType> result = treeTypeService.getAll();
        if(result == null) return Response.createResponseServerError();
        return Response.createResponseSuccess(result);
    }

    @GetMapping("/getById/{id}")
    public Response getById(@PathVariable Long id, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "TYPETREE:VIEW");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            TreeType result = treeTypeService.getById(id);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @GetMapping("/search")
    public Response search(@RequestParam RequestSearchTreeType requestSearch, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "TYPETREE:VIEW");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            ResultPaging<TreeType> result = treeTypeService.search(requestSearch);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @PostMapping("/create")
    public Response createTreeType(@RequestBody TreeType treeType, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "TYPETREE:CREATE");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            TreeType result = treeTypeService.addTreeType(treeType);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @PutMapping("/update")
    public Response updateTreeType(@RequestBody TreeType treeType, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "TYPETREE:UPDATE");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            TreeType result = treeTypeService.updateTreeType(treeType);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteTreeType(@PathVariable Long id, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForAdmin(request, "TYPETREE:DELETE");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            treeTypeService.delete(id);
            return Response.createResponseSuccess(null);
        }else return resultCheck;
    }
}
