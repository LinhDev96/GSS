package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
import org.apache.commons.lang3.ObjectUtils;


import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonUtil {
	
	public static Pageable buildPageable(Map<String, ? extends Object> pageParams) {
//		int page = ObjectUtils.isNotEmpty(pageParams.get("page")) ? Integer.parseInt((String) pageParams.get("page"))
//				: EmployeeResourceConstant.DEFAULT_PAGE;
//		int size = ObjectUtils.isNotEmpty(pageParams.get("size")) ? Integer.parseInt((String) pageParams.get("size"))
//				: EmployeeResourceConstant.DEFAULT_PAGE_SIZE;
		int page = ObjectUtils.isNotEmpty(pageParams.get("page")) ? Integer.parseInt((String) pageParams.get("page"))
				: 0;
		int size = ObjectUtils.isNotEmpty(pageParams.get("pageSize")) ? Integer.parseInt((String) pageParams.get("pageSize"))
				: 100;
		String sort = ObjectUtils.isNotEmpty(pageParams.get("sort")) ? (String) pageParams.get("sort") : "name,asc";
		String[] sorts = sort.split(";");
        List<Sort.Order> orders = new ArrayList<>();

		for(String it : sorts) {
			String[] subsorts = it.split(",");
			
			if (subsorts[0] != null && subsorts[1] != null) {//Sort.Direction.ASC
				if(subsorts[0].equalsIgnoreCase("unitPrice")) {
	            	Order orderByPrice = new Order(subsorts[1].equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC , "unitPrice");
	    			orders.add(orderByPrice);
	            }else {
		            orders.add(subsorts[1].equalsIgnoreCase("desc") ? Sort.Order.desc(subsorts[0]) : Sort.Order.asc(subsorts[0]));
	            }
	        }
			
		}
		return PageRequest.of(page, size, Sort.by(orders));
	}
}
