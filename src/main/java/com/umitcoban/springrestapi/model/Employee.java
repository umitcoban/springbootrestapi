package com.umitcoban.springrestapi.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity //Bunu ekleyerek database de bu sınıfla bağlantılı bir tablo olduğunu belirtiyor ve eşliyoruz.
@Table(name = "tbl_employee") // Database üzerinde bağlantılı olan tablo adını yazarak eşleyebiliyoruz.
public class Employee {

	@Id//Bu propertynin primary column olduğunu belirtiyoruz
	//(strategy=GenerationType.AUTO)Veritabanın da kolonumuzun otomatik arttığını belirtiyoruz. Fakat bu bazı hybernate sürümlerinde hata veriyor.
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Veritabanın da kolonumuzun Identitiy olduğunu belirtiyoruz.
	//(strategy=GenerationType.AUTO)Veritabanın da kolonumuzun otomatik arttığını belirtiyoruz.
	@Column(name="id")
	private long id;

	//Bir nedenden ötürü property ismimizi farklı göstermek istiyorsak jsonProperty özelliği ile adını değiştirebiliriz
	//@JsonProperty("full_name")
	//@NotNull (message = "Name should be not null") // Validation sayesinde null olamayacağını belirtiyoruz
	//@NotEmpty(message="Name should be not empty") Validation sayesinde boş olamayacağını belirtiyoruz.
	@NotBlank(message="Name should be not empty or null")//Hem boş olamayacağını hem de null olamayacağını belirtiyoruz.
	@Column(name="name")//Tablodaki sutun adını belirtiyoruz.
	private String name;
	
	//Json ignore annotation sayesinde geri dönen cevap da istediğimiz bilgileri gizleyebiliriz.
	//@JsonIgnore
	@Column(name="age")
	private int age;
	
	@Column(name="location")
	private String location;
	
	@Email(message = "Please enter the valid email adress") // Email annotation sayesinde email adresi validasyonu gerçekleştirebiliyoruz.
	@Column(name="email")
	private String email;
	
	@NotBlank(message="Department should be not empty or null") // Hata mesajını spesifik hale getirebiliyoruz.
	@Column(name="department")
	private String department;
	
	@CreationTimestamp
	@Column(name="created_at",nullable=false,updatable=false)
	private Date createdAt;

	@UpdateTimestamp
	@Column(name="updated_at")
	private Date updatedAt;
}
