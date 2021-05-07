package com.example.demo.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ArrayTimeKeepings")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class TKArray {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aTKId;
	
	private Long staffId;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateInfo;
	
	private String one;
	
	private String two;
	
	private String three;
	
	private String four;
	
	private String five;
	
	private String six;
	
	private String seven;
	
	private String eight;
	
	private String nine;
	
	private String ten;
	
	private String eleven;
	
	private String twele;
	
	private String thirteen;
	
	private String fourteen;
	
	private String fifteen;
	
	private String sixteen;
	
	private String seventeen;
	
	private String eighteen;
	
	private String nineteen;
	
	private String twenty;
	
	private String twentyone;
	
	private String twentytwo;
	
	private String twentythree;
	
	private String twentyfour;
	
	private String twentyfive;
	
	private String twentysix;
	
	private String twentyseven;
	
	private String twentyeight;
	
	private String twentynine;
	
	private String thirty;
	
	private String thirtyone;

	public TKArray() {
		super();
	}

	public TKArray(Long aTKId, Long staffId, String firstName, String lastName, Date dateInfo, String one, String two,
			String three, String four, String five, String six, String seven, String eight, String nine, String ten,
			String eleven, String twele, String thirteen, String fourteen, String fifteen, String sixteen,
			String seventeen, String eighteen, String nineteen, String twenty, String twentyone, String twentytwo,
			String twentythree, String twentyfour, String twentyfive, String twentysix, String twentyseven,
			String twentyeight, String twentynine, String thirty, String thirtyone) {
		super();
		this.aTKId = aTKId;
		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateInfo = dateInfo;
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
		this.five = five;
		this.six = six;
		this.seven = seven;
		this.eight = eight;
		this.nine = nine;
		this.ten = ten;
		this.eleven = eleven;
		this.twele = twele;
		this.thirteen = thirteen;
		this.fourteen = fourteen;
		this.fifteen = fifteen;
		this.sixteen = sixteen;
		this.seventeen = seventeen;
		this.eighteen = eighteen;
		this.nineteen = nineteen;
		this.twenty = twenty;
		this.twentyone = twentyone;
		this.twentytwo = twentytwo;
		this.twentythree = twentythree;
		this.twentyfour = twentyfour;
		this.twentyfive = twentyfive;
		this.twentysix = twentysix;
		this.twentyseven = twentyseven;
		this.twentyeight = twentyeight;
		this.twentynine = twentynine;
		this.thirty = thirty;
		this.thirtyone = thirtyone;
	}

	public Long getaTKId() {
		return aTKId;
	}

	public void setaTKId(Long aTKId) {
		this.aTKId = aTKId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateInfo() {
		return dateInfo;
	}

	public void setDateInfo(Date dateInfo) {
		this.dateInfo = dateInfo;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

	public String getThree() {
		return three;
	}

	public void setThree(String three) {
		this.three = three;
	}

	public String getFour() {
		return four;
	}

	public void setFour(String four) {
		this.four = four;
	}

	public String getFive() {
		return five;
	}

	public void setFive(String five) {
		this.five = five;
	}

	public String getSix() {
		return six;
	}

	public void setSix(String six) {
		this.six = six;
	}

	public String getSeven() {
		return seven;
	}

	public void setSeven(String seven) {
		this.seven = seven;
	}

	public String getEight() {
		return eight;
	}

	public void setEight(String eight) {
		this.eight = eight;
	}

	public String getNine() {
		return nine;
	}

	public void setNine(String nine) {
		this.nine = nine;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getEleven() {
		return eleven;
	}

	public void setEleven(String eleven) {
		this.eleven = eleven;
	}

	public String getTwele() {
		return twele;
	}

	public void setTwele(String twele) {
		this.twele = twele;
	}

	public String getThirteen() {
		return thirteen;
	}

	public void setThirteen(String thirteen) {
		this.thirteen = thirteen;
	}

	public String getFourteen() {
		return fourteen;
	}

	public void setFourteen(String fourteen) {
		this.fourteen = fourteen;
	}

	public String getFifteen() {
		return fifteen;
	}

	public void setFifteen(String fifteen) {
		this.fifteen = fifteen;
	}

	public String getSixteen() {
		return sixteen;
	}

	public void setSixteen(String sixteen) {
		this.sixteen = sixteen;
	}

	public String getSeventeen() {
		return seventeen;
	}

	public void setSeventeen(String seventeen) {
		this.seventeen = seventeen;
	}

	public String getEighteen() {
		return eighteen;
	}

	public void setEighteen(String eighteen) {
		this.eighteen = eighteen;
	}

	public String getNineteen() {
		return nineteen;
	}

	public void setNineteen(String nineteen) {
		this.nineteen = nineteen;
	}

	public String getTwenty() {
		return twenty;
	}

	public void setTwenty(String twenty) {
		this.twenty = twenty;
	}

	public String getTwentyone() {
		return twentyone;
	}

	public void setTwentyone(String twentyone) {
		this.twentyone = twentyone;
	}

	public String getTwentytwo() {
		return twentytwo;
	}

	public void setTwentytwo(String twentytwo) {
		this.twentytwo = twentytwo;
	}

	public String getTwentythree() {
		return twentythree;
	}

	public void setTwentythree(String twentythree) {
		this.twentythree = twentythree;
	}

	public String getTwentyfour() {
		return twentyfour;
	}

	public void setTwentyfour(String twentyfour) {
		this.twentyfour = twentyfour;
	}

	public String getTwentyfive() {
		return twentyfive;
	}

	public void setTwentyfive(String twentyfive) {
		this.twentyfive = twentyfive;
	}

	public String getTwentysix() {
		return twentysix;
	}

	public void setTwentysix(String twentysix) {
		this.twentysix = twentysix;
	}

	public String getTwentyseven() {
		return twentyseven;
	}

	public void setTwentyseven(String twentyseven) {
		this.twentyseven = twentyseven;
	}

	public String getTwentyeight() {
		return twentyeight;
	}

	public void setTwentyeight(String twentyeight) {
		this.twentyeight = twentyeight;
	}

	public String getTwentynine() {
		return twentynine;
	}

	public void setTwentynine(String twentynine) {
		this.twentynine = twentynine;
	}

	public String getThirty() {
		return thirty;
	}

	public void setThirty(String thirty) {
		this.thirty = thirty;
	}

	public String getThirtyone() {
		return thirtyone;
	}

	public void setThirtyone(String thirtyone) {
		this.thirtyone = thirtyone;
	}

	@Override
	public String toString() {
		return "TKArray [aTKId=" + aTKId + ", staffId=" + staffId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dateInfo=" + dateInfo + ", one=" + one + ", two=" + two + ", three=" + three + ", four="
				+ four + ", five=" + five + ", six=" + six + ", seven=" + seven + ", eight=" + eight + ", nine=" + nine
				+ ", ten=" + ten + ", eleven=" + eleven + ", twele=" + twele + ", thirteen=" + thirteen + ", fourteen="
				+ fourteen + ", fifteen=" + fifteen + ", sixteen=" + sixteen + ", seventeen=" + seventeen
				+ ", eighteen=" + eighteen + ", nineteen=" + nineteen + ", twenty=" + twenty + ", twentyone="
				+ twentyone + ", twentytwo=" + twentytwo + ", twentythree=" + twentythree + ", twentyfour=" + twentyfour
				+ ", twentyfive=" + twentyfive + ", twentysix=" + twentysix + ", twentyseven=" + twentyseven
				+ ", twentyeight=" + twentyeight + ", twentynine=" + twentynine + ", thirty=" + thirty + ", thirtyone="
				+ thirtyone + "]";
	}

	
}
