package com.assignment.choi.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UriComponentsBuilder;

import com.assignment.choi.domain.DepDto;
import com.assignment.choi.domain.UserDto;
import com.assignment.choi.domain.UserHDto;
import com.assignment.choi.service.PTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class UserController {
	//@Autowired
	//UserService userService;
	
	@Autowired
	private final PTService ptService;
	
	public UserController(PTService ptService) {
		this.ptService = ptService;
	}
	
	@GetMapping("/")
	String goMainPg() {
		return "index";
	}
	
	// 사용자 포털
	@GetMapping("/user")
	String goUser(Model model) {
		System.out.println("??????????????????????"+ptService.goUser().get("getHobbyList"));
		model.addAttribute("getHobbyList", ptService.goUser().get("getHobbyList"));
		model.addAttribute("depList", ptService.goUser().get("depList"));
		
//		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//		factory.setConnectTimeout(5000); // 타임아웃 설정 5초
//		factory.setReadTimeout(5000); // 타임아웃 설정 5초
//		
//		RestTemplate restTemplete = new RestTemplate();
//		
//		URI url = UriComponentsBuilder
//                .fromUriString("http://localhost:8082") //http://localhost에 호출
//                .path("/user_PT")
////                .queryParam("name", "steve")  // query parameter가 필요한 경우 이와 같이 사용
////                .queryParam("age", 10)
//                .encode()
//                .build()
//                .toUri();
//		
//		
//
//       ResponseEntity<String> response = restTemplete.exchange(url.toString(), HttpMethod.GET, entity, String.class);
        // entity로 데이터를 가져오겠다(Get)~~
//        System.out.println(resultList.getStatusCode());
//        System.out.println(resultList.getBody());
//
//        return resultList.getBody();
		
		
		
//		System.out.println(uri.toString());
		
//		return "redirect:http://localhost:8082/user_PT";
		return "user/user";
	}
	
	
	@GetMapping("/userview")
	String goUserView(Model model, HttpServletRequest request/*, @ModelAttribute Map<String, Object> mo*/) {
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+depList);
		System.out.println("이게 왜 안들어오지?");
//		System.out.println(mo);
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		 if(flashMap!=null) {
			 List<DepDto> depList = (List<DepDto>) flashMap.get("depList");
			 System.out.println("제발!!!!!!!!!!!!!!@@@@@@@@@ "+depList);
			 model.addAttribute("depList", depList);
	     } else {
	    	 System.out.println("저리가");
	     }
		
//		System.out.println("!!!!!!!!!!!!!!!!!!!!! " +depList);
//		model.addAttribute("getHobbyList", usermodel);
		
		return "user/user";
//		return new ModelAndView("redirect:user/user", "depList", depList);

	}
	
	// 사용자 승인 요청
	@ResponseBody
	@PostMapping("/insert_user")
	String insert_user(UserDto dto, UserHDto hDto, String h_code_id) {
//		Map<String, String> map = new HashMap<>();
//		System.out.println("취미코드 : "+ h_code_id);
//		try {
//			// 사용자 추가
//			userService.insert(dto);
//
//			if(h_code_id != null) {
//				// 취미 등록
//				// 임시로 저장하기 위해 만듬
//				UserHDto newUHDto = new UserHDto();
//				HobbyDto newH_Dto = new HobbyDto();
//				UserDto newU_Dto = new UserDto();
//				System.out.println("전체 취미코드 : " + h_code_id);
//				if(h_code_id.contains(",")) {
//					String[] hic = h_code_id.split(",");
//					
//					for(int i=0; i<hic.length; i++) {
//						// 임시 변수
//						System.out.println("취미코드"+ (i+1) +": "+hic[i]);
//						hDto.setH_code_id(hic[i]);
//						hDto.setUserId(dto.getUserId());
//						
//						// h_code_id
//						newH_Dto.setH_code_id(hDto.getH_code_id());
//						newUHDto.setHobbyDto(newH_Dto);
//
//						// user_id
//						newU_Dto.setUserId(hDto.getUserId());
//						newUHDto.setUserDto(newU_Dto);
//						
//						// 사용자 취미 추가
//						userService.insertHobby(newUHDto);
//					}
//				} else {
//					// 임시 변수
//					System.out.println(h_code_id);
//					hDto.setH_code_id(h_code_id);
//					hDto.setUserId(dto.getUserId());
//					
//					// h_code_id
//					newH_Dto.setH_code_id(hDto.getH_code_id());
//					newUHDto.setHobbyDto(newH_Dto);
//
//					// user_id
//					newU_Dto.setUserId(hDto.getUserId());
//					newUHDto.setUserDto(newU_Dto);
//					
//					// 사용자 취미 추가
//					userService.insertHobby(newUHDto);
//				}
//			}
//			
//			map.put("msg", "success");
//			map.put("ok", "승인 요청 되었습니다.");
//		} catch (Exception e) {
//			map.put("msg", "fail");
//			e.printStackTrace();
//		}
//		return map;
		return "redirect:http://localhost:8082/insert_user_PT";
	}
//	
//	// 관리자 포털
//	@GetMapping("/admin")
//	String getList(Model model, String searchKeyword, String userId/*, @RequestParam(required = false, defaultValue = "0", value = "page") int page*/) {
//		// 목록
//		if(searchKeyword == null) {  //검색 안했을 때
//			// 페이지 0부터 시작
//			List<UserDto> list = userService.getList();
//			
//			// 총 페이지 수
//			int totalPage = list.getTotalPages();
//			
//			model.addAttribute("list", list);
//			model.addAttribute("list", list.getContent());
//			model.addAttribute("totalPage", totalPage);
//			
//			model.addAttribute("userId", user_id);
//			
//			//검색 안했을 때
//			
//			// 부서 목록
//			List<DepDto> depList = userService.getDepList();
//			model.addAttribute("depList", depList);
//			// 취미 목록
//			List<HobbyDto> getHobbyList = userService.getHobbyList();
//			model.addAttribute("getHobbyList", getHobbyList);
//			System.out.println("취미목록 : "+ getHobbyList.size());
//			// 사용자 취미
//			List<UserHDto> viewHList = userService.getHobby(userId);
//			System.out.println("취미리스트 갯수 : " + viewHList.size());
//			// 나눠서 저장
//			String hci="";
//			for(int i=0; i < viewHList.size(); i++) {
//				hci += viewHList.get(i).getHobbyDto().getH_code_id();
//			} // viewHList1   viewHList2  
//			model.addAttribute("hci", hci);
//			System.out.println("hci : "+ hci);
//		} else {  //검색 했을 때
//			String searchKeyword_no_s = searchKeyword.replaceAll("\\s", ""); // 공백 제거
//			// 검색 목록
//			List<UserDto> searchList = userService.searchUser(searchKeyword_no_s);
//			
//			int totalPage = searchList.getTotalPages();
//			
//			model.addAttribute("searchKeyword", searchKeyword_no_s);
//			System.out.println("키워드명 : "+ searchKeyword_no_s);
//			System.out.println("검색된 목록 갯수 : " + searchList.size());
//			model.addAttribute("searchList", searcChList);
//			model.addAttribute("searchList", searchList.getContent());
//			model.addAttribute("totalPage", totalPage);
//			
//			model.addAttribute("userId", user_id);
//			
//			// 사용자 정보
//			if(userId != "") {  //상세정보 눌렀을 때
//				// 사용자 부서
//				UserDto view = userService.getView(userId);
//				model.addAttribute("view", view);
//				System.out.println("부서 : " + view.getDepDto().getDep_id());
//				// 부서 목록
//				List<DepDto> depList = userService.getDepList();
//				model.addAttribute("depList", depList);
//				// 취미 목록
//				List<HobbyDto> getHobbyList = userService.getHobbyList();
//				model.addAttribute("getHobbyList", getHobbyList);
//				System.out.println("취미목록 : "+ getHobbyList.size());
//				// 사용자 취미
//				List<UserHDto> viewHList = userService.getHobby(userId);
//				System.out.println("취미리스트 갯수 : " + viewHList.size());
//				// 나눠서 저장
//				String hci="";
//				for(int i=0; i < viewHList.size(); i++) {
//					hci += viewHList.get(i).getHobbyDto().getH_code_id();
//				} // viewHList1   viewHList2  
//				model.addAttribute("hci", hci);
//				System.out.println("hci : "+ hci);
//			}
//		}
//
//		return "admin/admin";
//	}
//	
//	// 관리자 포털 상세보기
//	@GetMapping("/admin/{userId}")
//	String getView(Model model, @PathVariable("userId")String userId, String searchKeyword/*, @RequestParam(required = false, defaultValue = "0", value = "page") int page*/) {
//		// 목록
//		List<UserDto> list = userService.getList();
//		
//		int totalPage = list.getTotalPages();
//		
//		model.addAttribute("list", list);
//		model.addAttribute("list", list.getContent());
//		model.addAttribute("totalPage", totalPage);
//		
//		model.addAttribute("userId", user_id);
//		
//		// 사용자 정보
//		UserDto view = userService.getView(userId);
//		model.addAttribute("view", view);
//		System.out.println("부서 : " + view.getDepDto().getDep_id());
//		// 취미 목록
//		List<HobbyDto> getHobbyList = userService.getHobbyList();
//		model.addAttribute("getHobbyList", getHobbyList);
//		System.out.println("취미목록 : "+ getHobbyList.size());
//		// 사용자 취미
//		List<UserHDto> viewHList = userService.getHobby(userId);
//		System.out.println("취미리스트 갯수 : " + viewHList.size());
//		// 나눠서 저장, 상세정보에 취미 불러올때 필요함
//		String hci="";
//		for(int i=0; i < viewHList.size(); i++) {
//			hci += viewHList.get(i).getHobbyDto().getH_code_id();
//		} // viewHList1   viewHList2  
//		model.addAttribute("hci", hci);
//		System.out.println("hci : "+ hci);
//		
//		// 부서 목록
//		List<DepDto> depList = userService.getDepList();
//		model.addAttribute("depList", depList);
//		
//		return "admin/admin";
//	}
//	
//	// 사용자 정보 수정
//		@ResponseBody
//		@PostMapping("/admin/update")
//		Map<String, String> updateUser(UserDto dto, UserHDto hDto, String h_code_id, UserHDtoPK pk) {
//			Map<String, String> map = new HashMap<String, String>();
//			try {
//				// 사용자 정보 수정
//				userService.updateUser(dto);
//				
//				if(h_code_id == null) {
//					// 취미 삭제
//					userService.deleteHobby(pk);
//					System.out.println("취미 삭제됨1");
//				}
//				
//				// 취미 수정
//				if (h_code_id != null) {  // 취미가 있으면
//					// 취미 삭제
//					userService.deleteHobby(pk);
//					System.out.println("취미 삭제됨2");
//					
//					System.out.println("취미코드 : "+h_code_id);
//					System.out.println("@@@@@@@@@@@@ hDto : "+hDto);
//					
//					// 임시로 저장하기 위해 만듬
//					UserHDto newUHDto = new UserHDto();
//					HobbyDto newH_Dto = new HobbyDto();
//					UserDto newU_Dto = new UserDto();
//
//					if (h_code_id.contains(",")) {
//						String[] hic = h_code_id.split(",");
//
//						for (int i = 0; i < hic.length; i++) {
//							// 임시 변수
//							System.out.println("취미코드 : " + hic[i]);
//							hDto.setH_code_id(hic[i]);
//							hDto.setUserId(dto.getUserId());
//
//							// h_code_id
//							newH_Dto.setH_code_id(hDto.getH_code_id());
//							newUHDto.setHobbyDto(newH_Dto);
//
//							// user_id
//							newU_Dto.setUserId(hDto.getUserId());
//							newUHDto.setUserDto(newU_Dto);
//
//							// 사용자 취미 수정
//							userService.insertHobby(newUHDto);
//							//userService.updateUserHobby(dto.getUser_id(), newUHDto);
//						}
//					} else {
//						// 임시 변수
//						System.out.println("취미코드 : " + h_code_id);
//						hDto.setH_code_id(h_code_id);
//						hDto.setUserId(dto.getUserId());
//
//						// h_code_id
//						newH_Dto.setH_code_id(hDto.getH_code_id());
//						newUHDto.setHobbyDto(newH_Dto);
//						
//						// user_id
//						newU_Dto.setUserId(hDto.getUserId());
//						newUHDto.setUserDto(newU_Dto);
//
//						// 사용자 취미 수정
//						userService.insertHobby(newUHDto);
//						//userService.updateUserHobby(dto.getUser_id(), newUHDto);
//					}
//				}
//				
//				map.put("msg", "success");
//				map.put("msg2", "저장되었습니다.");
//			} catch (Exception e) {
//				map.put("msg", "fail");
//				e.printStackTrace();
//			}
//			return map;
//		}
//	
//	// 사용자 삭제
//	@ResponseBody
//	@PostMapping("/admin/delete")
//	Map<String, String> deleteUser(UserDto dto, UserHDtoPK pk, String h_code_id) {
//		Map<String, String> map = new HashMap<String, String>();
//		System.out.println("아이디 : " + dto.getUserId());
//		System.out.println("부서 : " + dto.getDepDto().getDep_id());
//		System.out.println("취미코드 : "+h_code_id);
//		System.out.println("pk ttttttt: "+ pk);
//		//pk.getHobbyDto().setH_code_id(h_code_id);
//		try {
//			if(h_code_id != null) {  // 취미가 있으면 
//				// 취미 삭제
//				userService.deleteHobby(pk);
//				System.out.println("취미 삭제됨");
//			}
//
//			// 사용자 삭제
//			userService.deleteUser(dto);
//			
//			map.put("msg", "success");
//			map.put("msg2", "삭제되었습니다.");
//		} catch (Exception e) {
//			map.put("msg", "fail");
//			e.printStackTrace();
//		}
//		return map;
//	}
//	
//	// 아이디 중복체크
//	@ResponseBody
//	@PostMapping("/idcheck")
//	Map<String, String> idCheck(String userId) {
//		Map<String, String> map = new HashMap<String, String>();
//		try {
//			int result = userService.idCheck(userId);
//			if(result == 0) {
//				map.put("msg", "success");
//				map.put("use", "사용 가능한 아이디입니다.");
//			} else {
//				map.put("msg", "fail");
//				map.put("use", "이미 사용중인 아이디입니다.");
//			}
//		} catch (Exception e) {
//			map.put("msg", "fail");
//			e.printStackTrace();
//		}
//		return map;
//	}
}



