package com.bonsai.bill;

import com.bonsai.bill.model.Bill;
import com.bonsai.bill.model.BillDetail;
import com.bonsai.core.RepositoryFactory;
import com.bonsai.core.dao.BonsaiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BillService {
    @Autowired
    private BillDetailService billDetailService;

    private BonsaiDao<Bill> billDao;

    @Autowired
    public BillService(RepositoryFactory repositoryFactory){
        this.billDao = repositoryFactory.createDao(Bill.class);
    }

    public Bill createBill(Bill bill){
        try {
            bill.code = "BILL"+ UUID.randomUUID().toString().replaceAll("-","").substring(0,12);
            bill.created = System.currentTimeMillis();
            bill.updated = System.currentTimeMillis();
            bill = billDao.insert(bill);
            if(bill != null){
                billDetailService.createBillDetailForBill(bill);
                return bill;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Bill updateBill(Bill bill){
        try {
            bill.updated = System.currentTimeMillis();
            return billDao.update(bill);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Bill> getBillForUser(Long userId){
        try {
            String where = String.format("receiver = %d", userId);
            return billDao.find(where);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Bill getBillById(Long id){
        try {
            Bill bill = billDao.findById(id);
            if(bill != null){
                bill.billDetail = billDetailService.getBillDetailByBillId(bill.id);
                return bill;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void deleteBill(Long id){
        try {
            List<BillDetail> details = billDetailService.getBillDetailByBillId(id);
            billDetailService.deletes(details.stream().map(n -> n.id).collect(Collectors.toList()));
            billDao.delete(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
