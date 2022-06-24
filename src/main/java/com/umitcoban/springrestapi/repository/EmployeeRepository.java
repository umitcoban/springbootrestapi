package com.umitcoban.springrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umitcoban.springrestapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	/*
	 * Jpa ile bu şekilde hazır sorgu oluşturabiliyoruz. Sorgu methodunun başı her zaman findBy ile başlamalıdır. Ardından Field ismimizi yazmamız gerekiyor.
	   Select * from tbl_employee where name = '' şeklinde bir sorgu üretir bize */
	
	List<Employee> findByName(String name);
	
	/*
	 * Bu şekilde de jpa ile çoklu kolona göre filtreleme işlemi gerçekleştirebiliyoruz
	 * Bu birden fazla parametre ile filtreleme yapmak istediğimizde arasına AND operatörü ekleyebiliriz.
	 * Sorgunun çıktısı ise şu şekildedir.
	 * 
	 * Select * from tbl_employee where name='' and location=''
	 *  */
	
	List<Employee> findByNameAndLocation(String name, String location);
}
