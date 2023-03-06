package com.capgemini.datle.borrowingservicev2.query.api.handler;

import com.capgemini.datle.borrowingservicev2.command.api.data.Borrowing;
import com.capgemini.datle.borrowingservicev2.command.api.data.BorrowingRepository;
import com.capgemini.datle.borrowingservicev2.query.api.model.BorrowingRestModel;
import com.capgemini.datle.borrowingservicev2.query.api.queries.GetBorrowingByEmployee;
import com.capgemini.datle.commonservice.model.BorrowingCommonModel;
import com.capgemini.datle.commonservice.queries.GetListBorrowingByEmployee;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BorrowingQueryHandler {
    private BorrowingRepository borrowingRepository;
    public BorrowingQueryHandler(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }
    @QueryHandler
    public List<BorrowingRestModel> handle(GetBorrowingByEmployee query){
        List<BorrowingRestModel> list=new ArrayList<>();
        List<Borrowing> borrowingList=
                borrowingRepository
                        .findBorrowingByEmployeeIdAndReturnDateIsNull(query.getEmplyeeId());
        for (int i=0; i<borrowingList.size(); i++){
            if(borrowingList.get(i).getStatus().equals("BORROWED")){
                BorrowingRestModel borrowingRestModel=new BorrowingRestModel();
                BeanUtils.copyProperties(borrowingList.get(i), borrowingRestModel);
                list.add(borrowingRestModel);
            }
        }
        return list;
    }
    @QueryHandler
    public List<BorrowingCommonModel> handle(GetListBorrowingByEmployee query){
        List<BorrowingCommonModel> list=new ArrayList<>();
        List<Borrowing> borrowingList=
                borrowingRepository
                        .findBorrowingByEmployeeIdAndReturnDateIsNull(query.getEmployeeId());
        for (int i=0; i<borrowingList.size(); i++){
            if(borrowingList.get(i).getStatus().equals("BORROWED")){
                BorrowingCommonModel borrowingCommonModel=new BorrowingCommonModel();
                BeanUtils.copyProperties(borrowingList.get(i), borrowingCommonModel);
                list.add(borrowingCommonModel);
            }
        }
        return list;
    }
}
