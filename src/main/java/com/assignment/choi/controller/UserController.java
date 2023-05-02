package com.assignment.choi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.choi.domain.UserDto;
import com.assignment.choi.domain.UserHDto;
import com.assignment.choi.domain.UserHDtoPK;
import com.assignment.choi.service.PTService;

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
		model.addAttribute("getHobbyList", ptService.goUser().get("getHobbyList"));
		model.addAttribute("depList", ptService.goUser().get("depList"));
		return "user/user";
	}
	
	// 사용자 승인 요청
	@ResponseBody
	@PostMapping("/insert_user")
	Map<String, String> insert_user(UserDto dto, UserHDto hDto, String h_code_id) {
		Map<String, String> map = new HashMap<>();
		try {
			// 유저 등록
			ptService.insert(dto);
			// 취미 등록
			if(h_code_id != null) {
//				System.out.println("PT controller hDto : "+hDto);
				System.out.println("PT controller : "+h_code_id);
				ptService.insert_hobby(dto, hDto, h_code_id);
				System.out.println("와 드디어 서비스가 됐다 ~");
			}
			map.put("msg", "success");
			map.put("ok", "승인 요청 되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "fail");
		}
		return map;
	}
	
	// 관리자 포털
	@GetMapping("/admin")
	String getList(Model model, String searchKeyword, String userId) {
		Map<?, ?> resultMap = (Map<?, ?>) ptService.adminList(searchKeyword, userId);
		System.out.println("adminList : "+resultMap);
		// 검색 안했을 때
		if(searchKeyword == null) {
			model.addAttribute("list", resultMap.get("list"));
			model.addAttribute("depList", resultMap.get("depList"));
			model.addAttribute("getHobbyList", resultMap.get("getHobbyList"));
			model.addAttribute("hci", resultMap.get("hci"));
		} else {  //검색 했을 때
			model.addAttribute("searchKeyword", resultMap.get("searchKeyword"));
			if(userId != "") {  //상세정보 눌렀을 때
				model.addAttribute("view", resultMap.get("view"));
				model.addAttribute("depList", resultMap.get("depList"));
				model.addAttribute("getHobbyList", resultMap.get("getHobbyList"));
				model.addAttribute("hci", resultMap.get("hci"));
			}
		}
		return "admin/admin";
	}
	
	// 관리자 포털 상세보기
	@GetMapping("/admin/{userId}")
	String getView(Model model, @PathVariable("userId")String userId, String searchKeyword/*, @RequestParam(required = false, defaultValue = "0", value = "page") int page*/) {
		Map<?, ?> resultMap = (Map<?, ?>) ptService.adminView(userId, searchKeyword);
		System.out.println("adminList : "+resultMap);
		// 목록
		model.addAttribute("list", resultMap.get("list"));
		// 사용자 정보
		model.addAttribute("view", resultMap.get("view"));
		// 취미 목록
		model.addAttribute("getHobbyList", resultMap.get("getHobbyList"));
		// 사용자 취미, 나눠서 저장, 상세정보에 취미 불러올때 필요함
		model.addAttribute("hci", resultMap.get("hci"));
		// 부서 목록
		model.addAttribute("depList", resultMap.get("depList"));
		
		return "admin/admin";
	}
	
	// 사용자 정보 수정
		@ResponseBody
		@PostMapping("/admin/update")
		Map<String, String> updateUser(UserDto dto, UserHDto hDto, String h_code_id, UserHDtoPK pk) {
			Map<String, String> map = new HashMap<String, String>();
			
			
			
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
			return map;
		}
		
	// 사용자 삭제
	@ResponseBody
	@PostMapping("/admin/delete")
	Map<String, String> deleteUser(UserDto dto, UserHDtoPK pk, String h_code_id) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(h_code_id != null) {  // 취미가 있으면 
				pk.setH_code_id(h_code_id);
				// 취미 삭제
				ptService.deleteUserHobby(pk);
			}
			// 사용자 삭제
			ptService.deleteUser(dto);
		
		map.put("msg", "success");
		map.put("msg2", "삭제되었습니다.");
		} catch (Exception e) {
			map.put("msg", "fail");
			e.printStackTrace();
		}
		return map;
	}
	
	// 아이디 중복체크
	@ResponseBody
	@PostMapping("/idcheck")
	Map<String, String> idCheck(String userId) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			int result = ptService.idCheck(userId);
			System.out.println("중복된 아이디 : "+ result);
			if(result == 0) {
				map.put("msg", "success");
				map.put("use", "사용 가능한 아이디입니다.");
			} else {
				map.put("msg", "fail");
				map.put("use", "이미 사용중인 아이디입니다.");
			}
		} catch (Exception e) {
			map.put("msg", "fail");
			e.printStackTrace();
		}
		return map;
	}
}



