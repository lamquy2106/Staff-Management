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
public class SalaryInMonthController {
	@Autowired
	SalaryRepository salaryRepo;
	
	@Autowired
	StaffRepository staffRepo;
	 
	@Autowired
	ArrayTKRepository tkaRepo;
	
	@Autowired
	SalaryInMonthRepository simRepo;
	
	@PostMapping("/salaryinmonth/{month}/{year}")
	public ResponseEntity<Map<String, Object>> allSalary(@PathVariable("month") int month , @PathVariable("year") int year){
		try {
			List<Salary> salaryData = salaryRepo.findAll();
			List<TKArray> tkArray = tkaRepo.findByMonthAndYear(month, year);
			List<SalaryInMonth> listSim = simRepo.findAll();
			for(int i = 0 ; i < listSim.size() ; i++) {
				
				if(listSim.get(i).getDateInfo().toString().substring(5, 7).equals(Integer.toString(month)) && listSim.get(i).getDateInfo().toString().substring(0, 4).equals(Integer.toString(year))) {
					simRepo.deleteById(listSim.get(i).getSalaryId());
				}
				else if(listSim.get(i).getDateInfo().toString().substring(6, 7).equals(Integer.toString(month))  && listSim.get(i).getDateInfo().toString().substring(0, 4).equals(Integer.toString(year))) {
					simRepo.deleteById(listSim.get(i).getSalaryId());
				}
			}
			
			for(int i = 0 ; i < salaryData.size() ; i++) {
				SalaryInMonth simData = new SalaryInMonth();
				double total = 0;
				int co = 0;
				int khac = 0;
				int nuaNgayCong = 0;
				for(int j = 0 ; j < tkArray.size() ; j++) {
					if(salaryData.get(i).getStaff().getStaffId() == tkArray.get(j).getStaffId()) {
						if(tkArray.get(j).getOne() == null) {
							
						}
						else if(tkArray.get(j).getOne().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getOne().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getOne().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getOne().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						
						if(tkArray.get(j).getTwo() == null) {
							
						}
						else if(tkArray.get(j).getTwo().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwo().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwo().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwo().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getThree() == null) {
							
						}
						else if(tkArray.get(j).getThree().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getThree().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getThree().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getThree().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getFour() == null) {
							
						}
						else if(tkArray.get(j).getFour().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getFour().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getFour().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getFour().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getOne() == null) {
							
						}
						else if(tkArray.get(j).getFive().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getFive().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getFive().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getFive().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getSix() == null) {
							
						}
						else if(tkArray.get(j).getSix().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getSix().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getSix().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getSix().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getSeven() == null) {
							
						}
						else if(tkArray.get(j).getSeven().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getSeven().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getSeven().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getSeven().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getEight() == null) {
							
						}
						else if(tkArray.get(j).getEight().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getEight().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getEight().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getEight().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getNine() == null) {
							
						}
						else if(tkArray.get(j).getNine().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getNine().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getNine().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getNine().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTen() == null) {
							
						}
						else if(tkArray.get(j).getTen().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTen().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTen().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTen().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getEleven() == null) {
							
						}
						else if(tkArray.get(j).getEleven().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getEleven().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getEleven().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getEleven().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwele() == null) {
							
						}
						else if(tkArray.get(j).getTwele().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwele().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwele().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwele().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getThirteen() == null) {
							
						}
						else if(tkArray.get(j).getThirteen().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getThirteen().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getThirteen().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getThirteen().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getFourteen() == null) {
							
						}
						else if(tkArray.get(j).getFourteen().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getFourteen().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getFourteen().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getFourteen().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getFifteen() == null) {
							
						}
						else if(tkArray.get(j).getFifteen().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getFifteen().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getFifteen().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getFifteen().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getSixteen() == null) {
							
						}
						else if(tkArray.get(j).getSixteen().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getSixteen().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getSixteen().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getSixteen().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getSeventeen() == null) {
							
						}
						else if(tkArray.get(j).getSeventeen().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getSeventeen().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getSeventeen().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getSeventeen().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getEighteen() == null) {
							
						}
						else if(tkArray.get(j).getEighteen().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getEighteen().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getEighteen().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getEighteen().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getNineteen() == null) {
							
						}
						else if(tkArray.get(j).getNineteen().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getNineteen().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getNineteen().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getNineteen().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwenty() == null) {
							
						}
						else if(tkArray.get(j).getTwenty().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwenty().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwenty().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwenty().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentyone() == null) {
							
						}
						else if(tkArray.get(j).getTwentyone().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwentyone().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentyone().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwentyone().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentytwo() == null) {
							
						}
						else if(tkArray.get(j).getTwentytwo().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwentytwo().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentytwo().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwentytwo().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentythree() == null) {
							
						}
						else if(tkArray.get(j).getTwentythree().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwentythree().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentythree().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwentythree().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentyfour() == null) {
							
						}
						else if(tkArray.get(j).getTwentyfour().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwentyfour().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentyfour().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwentyfour().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentyfive() == null) {
							
						}
						else if(tkArray.get(j).getTwentyfive().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwentyfive().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentyfive().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwentyfive().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentysix() == null) {
							
						}
						else if(tkArray.get(j).getTwentysix().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwentysix().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentysix().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwentysix().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentyseven() == null) {
							
						}
						else if(tkArray.get(j).getTwentyseven().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwentyseven().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentyseven().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwentyseven().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentyeight() == null) {
							
						}
						else if(tkArray.get(j).getTwentyeight().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwentyeight().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentyeight().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwentyeight().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentynine() == null) {
							
						}
						else if(tkArray.get(j).getTwentynine().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getTwentynine().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentynine().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getTwentynine().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getThirty() == null) {
							
						}
						else if(tkArray.get(j).getThirty().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getThirty().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getThirty().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getThirty().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getThirtyone() == null) {
							
						}
						else if(tkArray.get(j).getThirtyone().equals("Có")) {
							co++;
						}
						else if(tkArray.get(j).getThirtyone().equals("Làm_nữa_ngày_công")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getThirtyone().equals("Nghĩ_nữa_ngày_không_lương") || tkArray.get(j).getThirtyone().equals("Nghĩ_không_lương")) {
							
						}
						else {
							khac++;
						}
						
					}
				}
				simData.setFirstName(salaryData.get(i).getStaff().getStaffFirstName());
				simData.setLastName(salaryData.get(i).getStaff().getStaffLastName());
				simData.setDateInfo(tkArray.get(i).getDateInfo());
				simData.setBasicSalary(salaryData.get(i).getBasicSalary());
				simData.setPrepayMoney(salaryData.get(i).getPrepayMoney());
				simData.setRewardMoney(salaryData.get(i).getRewardMoney());
				simData.setSubsidize(salaryData.get(i).getSubsidize());
				if(co > 21) {
					total = ((salaryData.get(i).getBasicSalary()/30) * co + (salaryData.get(i).getBasicSalary()/30) * khac 
							+ ((salaryData.get(i).getBasicSalary()/30)/2 * nuaNgayCong)
							+ salaryData.get(i).getRewardMoney() - salaryData.get(i).getPrepayMoney() + salaryData.get(i).getSubsidize())*1.3;
				}
				else {
					total = ((salaryData.get(i).getBasicSalary()/30) * co + (salaryData.get(i).getBasicSalary()/30) * khac 
							+ ((salaryData.get(i).getBasicSalary()/30)/2 * nuaNgayCong)
							+ salaryData.get(i).getRewardMoney() - salaryData.get(i).getPrepayMoney() + salaryData.get(i).getSubsidize());
				}
				total = Math.round(total * 10)/10;
				simData.setTotalSalary(total);
				simRepo.save(simData);
			}
			List<SalaryInMonth> simData = simRepo.findSalaryByMonthAndYear(month, year);
			Map<String, Object> response = new HashMap<>();
			response.put("SalaryInMonth", simData);
			response.put("errorCode", 1);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
	    	response.put("errorCode", 0);
	      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/salaryinmonth/{month}/{year}")
	public ResponseEntity<Map<String, Object>> findSalaryByMonthYear(@PathVariable("month") int month , @PathVariable("year") int year){
		try {
			List<SalaryInMonth> simData = simRepo.findSalaryByMonthAndYear(month, year);
			Map<String, Object> response = new HashMap<>();
			response.put("SalaryInMonth", simData);
			response.put("errorCode", 1);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> response = new HashMap<>();
	    	response.put("errorCode", 0);
	      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
