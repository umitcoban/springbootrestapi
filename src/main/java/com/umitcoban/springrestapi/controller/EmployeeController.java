package com.umitcoban.springrestapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.umitcoban.springrestapi.model.Employee;
import com.umitcoban.springrestapi.service.EmployeeService;
import com.umitcoban.springrestapi.service.EmployeeServiceImp;

//Maven => Clean Install ile spring boot projemizi jar dosyası formatında getirebilir ve istediğimiz zaman çalıştırabiliriz. 

//@Controller

@RestController // @Controller + @ResponseBody
//@RequestMapping("/api/v1") // Endpoint adreslerimiz değişti. http://localhost:8080/api/v1/controllerlar
public class EmployeeController {

	@Autowired
	EmployeeService service;
	// application.properties dosyamızda bulunan değerlere bu şekilde ulaşabiliyoruz.
	//Property erişimi sağlanamaz ise default value verebiliriz. app.name: - sonrasında yazılan değerler gösterilecektir.
	@Value("${app.name: Employee Tracker}") 
	private String appName;
	
	@Value("${app.version: Version v1}")
	private String appVersion;
	
	@GetMapping("/version")
	public ResponseEntity<String> getAppDetail() {
		return new ResponseEntity<String>(appName + " - " + appVersion,HttpStatus.OK);
	}
	
	//@RequestMapping(value="/employees", method = RequestMethod.GET) Bunu spring bizim için Her bir Http methoduna özgün Annotation ile sağlamaktadır.
	//@ResponseBody Bunu @Controller annotation kullanırsak yazmak zorundayız fakat @RestController de gerek kalmamakta
	@GetMapping("/employees")
	public ResponseEntity<List<Employee> > getEmployees() { // Http status kodu ile birlikte mesajımızı çeviriyoruz.
			return new ResponseEntity<List<Employee>>(service.getEmployees(),HttpStatus.OK); // Liste ile birlikte http200 kodunu göndereceğiz
		
	}
	
	//url = localhost:8080/employees/12
	@GetMapping("/employees/{id}")
	// PathVariable adı ile method parametre adı aynı ise @PathVariable yeterli. @PathVariable("id") demeye gerek yok
	public ResponseEntity<Employee> getEmployee(@PathVariable long id) { 
		return new ResponseEntity<Employee>(service.getEmployeeById(id),HttpStatus.OK);
	}
	
	@PostMapping("/employees") // Validation sayesinde bize json body ile gönderilen değerleri kontrol ediyoruz, validasyona uymayan durumlarda exception fırlatılmakta.
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {	
		return new ResponseEntity<Employee>(service.saveEmployee(employee),HttpStatus.CREATED);// http 201 kodunu göndererek bir nesnenin başarıyla yaratıldığını gönderiyoruz.
	}
	
	@PutMapping("/employee/{id}")						  //İstek de gönderilen nesneyi alıyoruz
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
		employee.setId(id);
		return new ResponseEntity<Employee>(service.updateEmployee(employee),HttpStatus.OK);
	}
	
	//url = localhost:8080/employees?id=15
	@DeleteMapping("/employees")
	// Request parameter adı ve method değişkeni adı aynı ise @RequestParam yazmamız yeterlidir
	public  ResponseEntity<HttpStatus> deleteEmployee(@RequestParam long id) { // Sadece HTTP status gönderiyoruz
		service.deleteEmployee(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT); // http 204 kodu göndererek veritabanından verinin silindiğini belirtiyoruz.
	}
	
	@GetMapping("/employees/filterByName")
	public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name){
		return new ResponseEntity<List<Employee>>(service.getEmployeesByName(name),HttpStatus.OK);
	}
	
	@GetMapping("/employees/filterByNameAndLocation")
	public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name, @RequestParam String location){
		return new ResponseEntity<List<Employee>>(service.getEmployeesByNameAndLocation(name, location),HttpStatus.OK);
	}
}
