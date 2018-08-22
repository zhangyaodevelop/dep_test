package com.forezp.eurekaclient;

import com.forezp.eurekaclient.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class EurekaclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaclientApplication.class, args);
	}



	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${server.port}")
	String port;
	@RequestMapping("/hi")
	public String home(@RequestParam String name) {
		student student=getStudent(1);
		System.out.println(student.getStudentName()+":"+student.getStudentWork());
		return "hi "+name+",i am from port:" +port+">>>>["+student.getStudentName()+"]+["+student.getStudentWork()+"]";
	}

	public student getStudent(Integer id) {
		String sql="select * from student where id="+id;
		List<student> studentList = jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<student>(student.class));
		student student=null;
		if(null!=studentList&&studentList.size()>0){
			student = studentList.get(0);
		}
		return student;
	}

	//jdbcTemplate.update  支持insert update delete
	//jdbcTemplate.query    支持selete
}