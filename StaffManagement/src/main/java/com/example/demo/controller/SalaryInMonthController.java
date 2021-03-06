package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
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
						else if(tkArray.get(j).getOne().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getOne().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getOne().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getOne().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						
						if(tkArray.get(j).getTwo() == null) {
							
						}
						else if(tkArray.get(j).getTwo().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwo().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwo().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwo().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getThree() == null) {
							
						}
						else if(tkArray.get(j).getThree().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getThree().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getThree().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getThree().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getFour() == null) {
							
						}
						else if(tkArray.get(j).getFour().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getFour().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getFour().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getFour().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getOne() == null) {
							
						}
						else if(tkArray.get(j).getFive().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getFive().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getFive().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getFive().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getSix() == null) {
							
						}
						else if(tkArray.get(j).getSix().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getSix().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getSix().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getSix().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getSeven() == null) {
							
						}
						else if(tkArray.get(j).getSeven().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getSeven().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getSeven().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getSeven().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getEight() == null) {
							
						}
						else if(tkArray.get(j).getEight().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getEight().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getEight().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getEight().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getNine() == null) {
							
						}
						else if(tkArray.get(j).getNine().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getNine().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getNine().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getNine().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTen() == null) {
							
						}
						else if(tkArray.get(j).getTen().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTen().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTen().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTen().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getEleven() == null) {
							
						}
						else if(tkArray.get(j).getEleven().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getEleven().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getEleven().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getEleven().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwele() == null) {
							
						}
						else if(tkArray.get(j).getTwele().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwele().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwele().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwele().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getThirteen() == null) {
							
						}
						else if(tkArray.get(j).getThirteen().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getThirteen().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getThirteen().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getThirteen().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getFourteen() == null) {
							
						}
						else if(tkArray.get(j).getFourteen().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getFourteen().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getFourteen().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getFourteen().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getFifteen() == null) {
							
						}
						else if(tkArray.get(j).getFifteen().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getFifteen().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getFifteen().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getFifteen().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getSixteen() == null) {
							
						}
						else if(tkArray.get(j).getSixteen().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getSixteen().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getSixteen().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getSixteen().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getSeventeen() == null) {
							
						}
						else if(tkArray.get(j).getSeventeen().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getSeventeen().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getSeventeen().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getSeventeen().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getEighteen() == null) {
							
						}
						else if(tkArray.get(j).getEighteen().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getEighteen().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getEighteen().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getEighteen().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getNineteen() == null) {
							
						}
						else if(tkArray.get(j).getNineteen().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getNineteen().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getNineteen().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getNineteen().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwenty() == null) {
							
						}
						else if(tkArray.get(j).getTwenty().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwenty().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwenty().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwenty().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentyone() == null) {
							
						}
						else if(tkArray.get(j).getTwentyone().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwentyone().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentyone().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwentyone().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentytwo() == null) {
							
						}
						else if(tkArray.get(j).getTwentytwo().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwentytwo().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentytwo().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwentytwo().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentythree() == null) {
							
						}
						else if(tkArray.get(j).getTwentythree().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwentythree().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentythree().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwentythree().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentyfour() == null) {
							
						}
						else if(tkArray.get(j).getTwentyfour().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwentyfour().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentyfour().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwentyfour().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentyfive() == null) {
							
						}
						else if(tkArray.get(j).getTwentyfive().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwentyfive().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentyfive().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwentyfive().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentysix() == null) {
							
						}
						else if(tkArray.get(j).getTwentysix().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwentysix().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentysix().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwentysix().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentyseven() == null) {
							
						}
						else if(tkArray.get(j).getTwentyseven().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwentyseven().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentyseven().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwentyseven().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentyeight() == null) {
							
						}
						else if(tkArray.get(j).getTwentyeight().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwentyeight().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentyeight().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwentyeight().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getTwentynine() == null) {
							
						}
						else if(tkArray.get(j).getTwentynine().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getTwentynine().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getTwentynine().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getTwentynine().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getThirty() == null) {
							
						}
						else if(tkArray.get(j).getThirty().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getThirty().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getThirty().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getThirty().equals("Ngh??_kh??ng_l????ng")) {
							
						}
						else {
							khac++;
						}
						if(tkArray.get(j).getThirtyone() == null) {
							
						}
						else if(tkArray.get(j).getThirtyone().equals("C??")) {
							co++;
						}
						else if(tkArray.get(j).getThirtyone().equals("L??m_n???a_ng??y_c??ng")){
							nuaNgayCong++;
						}
						else if(tkArray.get(j).getThirtyone().equals("Ngh??_n???a_ng??y_kh??ng_l????ng") || tkArray.get(j).getThirtyone().equals("Ngh??_kh??ng_l????ng")) {
							
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
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
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
