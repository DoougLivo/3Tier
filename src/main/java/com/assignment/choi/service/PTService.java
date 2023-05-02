package com.assignment.choi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assignment.choi.domain.HobbyDto;
import com.assignment.choi.domain.UserDto;
import com.assignment.choi.domain.UserHDto;
import com.assignment.choi.domain.UserHDtoPK;

@Service
public class PTService {
	String url = "http://localhost:8082";
	RestTemplate restTemplate = new RestTemplate();
	
	public Map<String, Object> goUser() {
		String uri = "/user_PT";
//		System.out.println("1111111111111111111111 "+url);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ResponseEntity<Map> responseEntity = restTemplate.exchange(url+uri, HttpMethod.GET, null, new ParameterizedTypeReference<Map>() {
		});
		System.out.println("status : " + responseEntity.getStatusCode());
		System.out.println("body : " + responseEntity.getBody());
        
        return responseEntity.getBody();
        
//		URI uri = UriComponentsBuilder
//      .fromUriString("http://localhost:8082") //http://localhost에 호출
//      .path("/test")
//      .queryParam("depList", "steve")  // query parameter가 필요한 경우 이와 같이 사용
//      .queryParam("age", 10)
//      .encode()
//      .build()
//      .toUri();
	}
	
	public void insert(UserDto dto) {
		String uri = "/insert_user_PT";
		restTemplate.postForObject(url+uri, dto, UserDto.class);
	}
	
	public void insert_hobby(UserDto dto, UserHDto hDto, String h_code_id) {
		UserHDto new_hDto = new UserHDto();
		HobbyDto hobbyDto = new HobbyDto();
		UserDto new_userDto = new UserDto();
		String uri = "/insert_userHobby_PT";
		// hDto 에 아이디 담아서 보내기
//		hDto.setUserId(dto.getUserId());
//		new_hDto.setUserId(hDto.getUserId());
		
//		new_hDto.setUserId(dto.getUserId());
//		new_userDto.setUserId(dto.getUserId());
//		new_hDto.setUserDto(new_userDto);
//		System.out.println("이거 테스트 : "+dto.getUserId());
//		new_userDto.setUserId(dto.getUserId());
//		new_hDto.setUserDto(dto);
		hDto.setUserDto(dto);
//		System.out.println("이거 테스트 2 : "+new_hDto.getUserDto().getUserId());
		System.out.println("이거 테스트 2 : "+hDto.getUserDto().getUserId());
		
//		hobbyDto.setH_code_id(h_code_id);
//		new_hDto.setH_code_id(hobbyDto.getH_code_id());
		// hDto 에 취미코드 하나씩 담아서 보내기
		if(h_code_id.contains(",")) {
			String[] hic = h_code_id.split(",");
			for(int i=0; i<hic.length; i++) {
				// 임시 변수
				System.out.println("취미코드"+ (i+1) +": "+hic[i]);
//				hDto.getHobbyDto().setH_code_id(hic[i]);
//				new_hDto.setUserId(dto.getUserId());
				
				hobbyDto.setH_code_id(hic[i]);
//				new_hDto.setHobbyDto(hobbyDto);
				hDto.setHobbyDto(hobbyDto);
//				new_hDto.getHobbyDto().setH_code_id(hic[i]);
				
//				hDto.setH_code_id(hobbyDto.getH_code_id());
//				new_hDto.setH_code_id(hDto.getH_code_id());
				System.out.println("test @@@@@@@@@@@@@ : "+new_hDto);
//				System.out.println("PT 서비스 hDto : "+hic[i]);
				restTemplate.postForObject(url+uri, hDto, UserHDto.class);
			}
		} else {
//			new_hDto.setUserId(dto.getUserId());
			hobbyDto.setH_code_id(h_code_id);
			hDto.setHobbyDto(hobbyDto);
//			hDto.setH_code_id(hobbyDto.getH_code_id());
//			new_hDto.setH_code_id(hDto.getH_code_id());
//			hDto.getHobbyDto().setH_code_id(h_code_id);
			System.out.println("PT 서비스 hDto : "+new_hDto);
			restTemplate.postForObject(url+uri, hDto, UserHDto.class);
		}
	}
	
	public int idCheck(String userId) {
		String uri = "/idcheck_PT";
		int result = restTemplate.postForObject(url+uri, userId, int.class);
		return result;
	}
	
	public List<UserDto> adminList(String searchKeyword, String userId) {
		System.out.println("3");
		String uri="";
		if(searchKeyword != null) {
			uri = url + "/admin_PT?searchKeyword="+searchKeyword;
		} else {
			uri = url + "/admin_PT";
		}
//		ResponseEntity<Map> responseEntity = restTemplate.exchange(url+uri, HttpMethod.GET, null, new ParameterizedTypeReference<Map>() {});
//		System.out.println("status : " + responseEntity.getStatusCode());
//		System.out.println("@@@@@ body : " + responseEntity.getBody());
		
		ResponseEntity<List<UserDto>> responseEntity= restTemplate.exchange(uri, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<UserDto>>() {});
		
		return responseEntity.getBody();
	}
	
	public Map<?, ?> adminView(String userId, String searchKeyword) {
		String uri = "/admin/"+userId;
		ResponseEntity<Map> responseEntity = restTemplate.exchange(url+uri, HttpMethod.GET, null, new ParameterizedTypeReference<Map>() {});
		System.out.println("status : " + responseEntity.getStatusCode());
		System.out.println("body : " + responseEntity.getBody());
		return responseEntity.getBody();
	}
	
	public void deleteUser(UserDto dto) {
		String uri = "/admin/delete_PT";
		restTemplate.postForObject(url+uri, dto, UserDto.class);
	}
	
	public void deleteUserHobby(UserHDtoPK pk) {
		String uri = "/admin/deleteHobby_PT";
		restTemplate.postForObject(url+uri, pk, UserHDtoPK.class);
	}
}
