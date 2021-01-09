package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Item;
import logic.ShopService;
public class DetailController {
	private ShopService shopService;
	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}
	//매개변수의 이름과 파라미터의 이름이 같으면 값을 저장함.
	@RequestMapping  //detail.shop 요청시 호출되는 메서드
	// id = Integer.parseInt(request.getParameter("id"));
	public ModelAndView detail(Integer id) {
		//item : id에 해당하는 item 테이블의 레코드값 
		Item item = shopService.getItemById(id);
		 //view의 이름이 없는 경우 detail.shop은 detail로 결정함.
		ModelAndView mav = new ModelAndView();
		mav.addObject("item",item);
		return mav;
	}
}
