package my.jes.web.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.jes.web.dao.MemberDAO;
import my.jes.web.dao.OrderDAO;
import my.jes.web.vo.MemberVO;
import my.jes.web.vo.OrderVO;

@Service
public class OrderService {
	@Autowired
	OrderDAO orderDAO;
	
	public long ordersInsert(ArrayList<OrderVO> list) throws Exception{
		return orderDAO.ordersInsert(list);
	}
}
