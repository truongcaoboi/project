package com.bonsai.bill;

import com.bonsai.auth.AuthService;
import com.bonsai.bill.model.Bill;
import com.bonsai.common.Contants;
import com.bonsai.common.Response;
import com.bonsai.core.dao.ResultPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/manage/bill")
public class BillController {
    @Autowired
    private AuthService authService;

    @Autowired
    private BillService billService;

    @GetMapping("/getByUser/{id}")
    public Response getByUser(@PathVariable Long id, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForUser(request, "BILL:VIEW");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            List<Bill> result = billService.getBillForUser(id);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @GetMapping("/getByIdForUser/{id}")
    public Response getByIdForUser(@PathVariable Long id, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForUser(request, "BILL:VIEW");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            Bill result = billService.getBillById(id);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }

    @PostMapping("/createBillForUser")
    public Response createBillForUser(@RequestBody Bill bill, HttpServletRequest request){
        Response resultCheck = authService.checkSessionAndPermissionForUser(request, "BILL:CREATE");
        if(resultCheck.statusCode == Contants.StatusCode.OK){
            Bill result = billService.createBill(bill);
            if(result == null) return Response.createResponseServerError();
            return Response.createResponseSuccess(result);
        }else return resultCheck;
    }
}
