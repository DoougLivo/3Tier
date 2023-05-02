package com.assignment.choi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assignment.choi.domain.DepDto;
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
		String uri = "/insert_userHobby_PT";
		// hDto 에 아이디 담아서 보내기
		hDto.setUserId(dto.getUserId());
		// hDto 에 취미코드 하나씩 담아서 보내기
		if(h_code_id.contains(",")) {
			String[] hic = h_code_id.split(",");
			for(int i=0; i<hic.length; i++) {
				// 임시 변수
				System.out.println("취미코드"+ (i+1) +": "+hic[i]);
				hDto.setH_code_id(hic[i]);
				System.out.println("PT 서비스 hDto : "+hic[i]);
				restTemplate.postForObject(url+uri, hDto, UserHDto.class);
			}
		} else {
			hDto.setH_code_id(h_code_id);
			System.out.println("PT 서비스 hDto : "+hDto);
			restTemplate.postForObject(url+uri, hDto, UserHDto.class);
		}
	}
	
	public int idCheck(String userId) {
		String uri = "/idcheck_PT";
		int result = restTemplate.postForObject(url+uri, userId, int.class);
		return result;
	}
	
	public Map<?, ?> adminList(String searchKeyword, String userId) {
		String uri = "/admin_PT";
		ResponseEntity<Map> responseEntity = restTemplate.exchange(url+uri, HttpMethod.GET, null, new ParameterizedTypeReference<Map>() {});
		System.out.println("status : " + responseEntity.getStatusCode());
		System.out.println("body : " + responseEntity.getBody());
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
