package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;


@RestController
@RequestMapping(value = "/api")
public class TimeKeepingController {
	
	@Autowired
	TimeKeepingRepository tkRepo;
	
	@Autowired
	StaffRepository staffRepo;
	
	@Autowired
	ArrayTKRepository tkaRepo;
	
	@PostMapping("staff/{staffId}/timekeeping")
	public ResponseEntity<Map<String, Object>> createTimeKeeping(@PathVariable Long staffId, @RequestBody @Valid TimeKeeping timeKeeping, BindingResult result) {
		if(result.hasErrors()) {
			StringBuilder devErrorMsg = new StringBuilder();
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError objectError : allErrors) {
				devErrorMsg.append(objectError.getDefaultMessage() + "\n");
			}
			ErrorDetails errorDetails = new ErrorDetails();
			errorDetails.setErrorCode("ERR-1400");
			errorDetails.setErrorMessage("Invalid TimeKeeping data received");
			errorDetails.setDevErrorMessage(devErrorMsg.toString());
			
			Map<String, Object> response = new HashMap<>();
			response.put("errorCode", errorDetails);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		if(!staffRepo.existsById(staffId) || !staffRepo.existsById(staffId)) {
			throw new ResourceNotFoundException("No are found with staff id= " + staffId );
		}
		else {
			Staff _staff = staffRepo.findById(staffId).orElse(null);
			timeKeeping.setStaff(_staff);
		}
		TimeKeeping saveTimeKeeping = tkRepo.save(timeKeeping);
		Map<String, Object> response = new HashMap<>();
		response.put("errorCode", 1);
		response.put("TimeKeeping", saveTimeKeeping);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/timekeeping")
	public ResponseEntity<Map<String, Object>> listTimeKeeping(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size){
		try {
			List<TimeKeeping> timeKeeping = new ArrayList<TimeKeeping>();
			Pageable paging = PageRequest.of(page, size);
			
			Page<TimeKeeping> pageTuts;
			pageTuts = tkRepo.findAll(paging);
			
			timeKeeping = pageTuts.getContent();
			
			Map<String, Object> response = new HashMap<>();
			response.put("timeKeeping", timeKeeping);
			response.put("currentPage", pageTuts.getNumber());
		    response.put("totalItems", pageTuts.getTotalElements());
		    response.put("totalPages", pageTuts.getTotalPages());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/timekeeping/{id}")
	public ResponseEntity<Map<String, Object>> getTimeKeeping(@PathVariable("id") Long id) {
		try {
			Long staffId;
			Optional<TimeKeeping> tkData = tkRepo.findById(id);
				if (tkData.isPresent()) 
					
				{
					TimeKeeping _tk = tkData.get();
					staffId = _tk.getStaff().getStaffId();
					Map<String, Object> response = new HashMap<>();
				      response.put("timeKeeping", tkData);
				      response.put("staffId", staffId);
				      response.put("errorCode", 1);
				      return new ResponseEntity<>(response, HttpStatus.OK);
				}
				else {
					throw new ResourceNotFoundException("Invalid timeKeeping data");
				}
		      
		    } catch (Exception e) {
		    	Map<String, Object> response = new HashMap<>();
		    	response.put("errorCode", 0);
		      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PutMapping("/staff/{staffId}/timekeeping/{tkId}")
	public ResponseEntity<Map<String, Object>> updateTimeKeeping(@PathVariable Long staffId, @PathVariable("tkId") Long tkId, @RequestBody @Valid TimeKeeping timeKeeping, BindingResult result){
		if(result.hasErrors()) {
			throw new IllegalArgumentException("Invalod TimeKeeping data");
		}
		if(staffRepo.existsById(staffId)) {
			Optional<TimeKeeping> timeKeepingData = tkRepo.findById(tkId);
			if(timeKeepingData.isPresent())
			{
				TimeKeeping _timeKeeping = timeKeepingData.get();
//				_timeKeeping.setTkDay(timeKeeping.getTkDay());
				_timeKeeping.setTkStatus(timeKeeping.getTkStatus());

				TimeKeeping saveTimeKeeping = tkRepo.save(_timeKeeping);
				
				Map<String, Object> response = new HashMap<>();
				response.put("timeKeeping", saveTimeKeeping);
				response.put("errorCode", 1);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			else
			{
				throw new ResourceNotFoundException("Invalid TimeKeeping data");
			}
		}
		throw new ResourceNotFoundException("Invalid Staff id");
	}
	
	@DeleteMapping("/timekeeping/{id}")
	public ResponseEntity<Map<String, Object>> deletetTimeKeeping(@PathVariable("id") Long id) {
//		TimeKeeping timeKeeping = tkRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("No TimeKeeping found with id=" + id));
		try {
			tkRepo.deleteById(id);
			Map<String, Object> response = new HashMap<>();  
			response.put("message", "Delete Success");
			response.put("errorCode", 1);
			return  new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new PostDeletionException("TimeKeeping with id=" + id + " can't be deleted");
		}
	}
//	@PostMapping("/bangchamcong/{month}/{year}")
//	public ResponseEntity<Map<String, Object>> allTimeKeeping(@PathVariable("month") int month , @PathVariable("year") int year){
//		try {
//			List<TimeKeeping> tkData = tkRepo.findByLike(month,year);
//			List<Staff> staffData = staffRepo.findAll();
//			List<TKArray> listTKA = tkaRepo.findAll();
//			for(int i = 0 ; i < listTKA.size() ; i++) {
//				
//				if(listTKA.get(i).getDateInfo().toString().substring(5, 7).equals(Integer.toString(month)) && listTKA.get(i).getDateInfo().toString().substring(0, 4).equals(Integer.toString(year))) {
//					tkaRepo.deleteById(listTKA.get(i).getaTKId());
//				}
//				else if(listTKA.get(i).getDateInfo().toString().substring(6, 7).equals(Integer.toString(month))  && listTKA.get(i).getDateInfo().toString().substring(0, 4).equals(Integer.toString(year))) {
//					tkaRepo.deleteById(listTKA.get(i).getaTKId());
//				}
//			}
//			
//			for(int i = 0 ; i < staffData.size() ; i++) {
//				TKArray tkArray = new TKArray();
//				tkArray.setFirstName(staffData.get(i).getStaffFirstName());
//				tkArray.setLastName(staffData.get(i).getStaffLastName());
//				tkArray.setDateInfo(tkData.get(0).getTkDay());
//				tkArray.setStaffId(staffData.get(i).getStaffId());
//				
//				for(int j = 0 ; j < tkData.size() ; j++){
//					if(staffData.get(i).getStaffId() == tkData.get(j).getStaff().getStaffId()) {
//						if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("01")) {
//							tkArray.setOne(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("02")){
//							tkArray.setTwo(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("03")){
//							tkArray.setThree(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("04")){
//							tkArray.setFour(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("05")){
//							tkArray.setFive(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("06")){
//							tkArray.setSix(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("07")){
//							tkArray.setSeven(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("08")){
//							tkArray.setEight(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("09")){
//							tkArray.setNine(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("10")){
//							tkArray.setTen(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("11")){
//							tkArray.setEleven(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("12")){
//							tkArray.setTwele(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("13")){
//							tkArray.setThirteen(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("14")){
//							tkArray.setFourteen(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("15")){
//							tkArray.setFifteen(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("16")){
//							tkArray.setSixteen(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("17")){
//							tkArray.setSeventeen(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("18")){
//							tkArray.setEighteen(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("19")){
//							tkArray.setNineteen(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("20")){
//							tkArray.setTwenty(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("21")){
//							tkArray.setTwentyone(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("22")){
//							tkArray.setTwentytwo(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("23")){
//							tkArray.setTwentythree(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("24")){
//							tkArray.setTwentyfour(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("25")){
//							tkArray.setTwentyfive(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("26")){
//							tkArray.setTwentysix(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("27")){
//							tkArray.setTwentyseven(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("28")){
//							tkArray.setTwentyeight(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("29")){
//							tkArray.setTwentynine(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("30")){
//							tkArray.setThirty(tkData.get(j).getTkStatus().toString());
//						}
//						else if(tkData.get(j).getTkDay().toString().substring(8, 10).equals("31")){
//							tkArray.setThirtyone(tkData.get(j).getTkStatus().toString());
//						}
//					}
//					else {
//						tkaRepo.save(tkArray);
//						if(j+1 == tkData.size()) {
//							break;
//						}
//					}
//				}
//				tkaRepo.save(tkArray);
//			}
//			List<TKArray> list =  tkaRepo.findByMonthAndYear(month, year);
//			Map<String, Object> response = new HashMap<>();
//			response.put("chamcong", list);
//			response.put("errorCode", 1);
//			 return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			Map<String, Object> response = new HashMap<>();
//	    	response.put("errorCode", 0);
//	      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//	@GetMapping("/xuatchamcong/{month}/{year}")
//	public ResponseEntity<Map<String, Object>> xuatChamCong(@PathVariable("month") int month , @PathVariable("year") int year){
//		try {
//			List<TKArray> tkaData = tkaRepo.findByMonthAndYear(month, year);
//			Map<String, Object> response = new HashMap<>();
//			response.put("SalaryInMonth", tkaData);
//			response.put("errorCode", 1);
//			return new ResponseEntity<>(response, HttpStatus.OK);
//		} catch (Exception e) {
//			Map<String, Object> response = new HashMap<>();
//	    	response.put("errorCode", 0);
//	      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
