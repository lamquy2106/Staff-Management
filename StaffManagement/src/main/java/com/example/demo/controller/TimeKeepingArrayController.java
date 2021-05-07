package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.*;
import com.example.demo.repository.*;


@RestController
@RequestMapping(value = "/api")
public class TimeKeepingArrayController {
	@Autowired
	TimeKeepingRepository tkRepo;
	
	@Autowired
	StaffRepository staffRepo;
	
	@Autowired
	ArrayTKRepository tkaRepo;
	
	@PostMapping("/bangchamcong/{month}/{year}")
	public ResponseEntity<Map<String, Object>> allTimeKeeping(@PathVariable("month") int month , @PathVariable("year") int year){
		try {
			List<TimeKeeping> tkData = tkRepo.findByLike(month,year);
			List<Staff> staffData = staffRepo.findAll();
			List<TKArray> listTKA = tkaRepo.findAll();
			for(int i = 0 ; i < listTKA.size() ; i++) {
				
				if(listTKA.get(i).getDateInfo().toString().substring(5, 7).equals(Integer.toString(month)) && listTKA.get(i).getDateInfo().toString().substring(0, 4).equals(Integer.toString(year))) {
					tkaRepo.deleteById(listTKA.get(i).getaTKId());
				}
				else if(listTKA.get(i).getDateInfo().toString().substring(6, 7).equals(Integer.toString(month))  && listTKA.get(i).getDateInfo().toString().substring(0, 4).equals(Integer.toString(year))) {
					tkaRepo.deleteById(listTKA.get(i).getaTKId());
				}
			}
			
			for(int i = 0 ; i < staffData.size() ; i++) {
				TKArray tkArray = new TKArray();
				tkArray.setFirstName(staffData.get(i).getStaffFirstName());
				tkArray.setLastName(staffData.get(i).getStaffLastName());
				tkArray.setDateInfo(tkData.get(0).getTkDay());
				tkArray.setStaffId(staffData.get(i).getStaffId());
				
				for(int j = 0 ; j < tkData.size() ; j++){
					if(staffData.get(i).getStaffId() == tkData.get(j).getStaff().getStaffId()) {
						if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("01")) {
							tkArray.setOne(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("02")){
							tkArray.setTwo(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("03")){
							tkArray.setThree(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("04")){
							tkArray.setFour(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("05")){
							tkArray.setFive(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("06")){
							tkArray.setSix(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("07")){
							tkArray.setSeven(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("08")){
							tkArray.setEight(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("09")){
							tkArray.setNine(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("10")){
							tkArray.setTen(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("11")){
							tkArray.setEleven(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("12")){
							tkArray.setTwele(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("13")){
							tkArray.setThirteen(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("14")){
							tkArray.setFourteen(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("15")){
							tkArray.setFifteen(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("16")){
							tkArray.setSixteen(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("17")){
							tkArray.setSeventeen(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("18")){
							tkArray.setEighteen(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("19")){
							tkArray.setNineteen(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("20")){
							tkArray.setTwenty(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("21")){
							tkArray.setTwentyone(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("22")){
							tkArray.setTwentytwo(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("23")){
							tkArray.setTwentythree(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("24")){
							tkArray.setTwentyfour(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("25")){
							tkArray.setTwentyfive(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("26")){
							tkArray.setTwentysix(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("27")){
							tkArray.setTwentyseven(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("28")){
							tkArray.setTwentyeight(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("29")){
							tkArray.setTwentynine(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("30")){
							tkArray.setThirty(tkData.get(j).getTkStatus().toString());
						}
						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("31")){
							tkArray.setThirtyone(tkData.get(j).getTkStatus().toString());
						}
					}
					else {
						tkaRepo.save(tkArray);
						if(j+1 == tkData.size()) {
							break;
						}
					}
				}
				tkaRepo.save(tkArray);
			}
			List<TKArray> list =  tkaRepo.findByMonthAndYear(month, year);
			Map<String, Object> response = new HashMap<>();
			response.put("chamcong", list);
			response.put("errorCode", 1);
			 return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
	    	response.put("errorCode", 0);
	      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/xuatchamcong/{month}/{year}")
	public ResponseEntity<Map<String, Object>> xuatChamCong(@PathVariable("month") int month , @PathVariable("year") int year){
		try {
			List<TKArray> tkaData = tkaRepo.findByMonthAndYear(month, year);
			Map<String, Object> response = new HashMap<>();
			response.put("TimeKeepingArray", tkaData);
			response.put("errorCode", 1);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
	    	response.put("errorCode", 0);
	      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
