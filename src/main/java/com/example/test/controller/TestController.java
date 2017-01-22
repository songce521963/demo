package com.example.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.model.Todo;
import com.example.test.model.User;

@RestController
public class TestController {

	
	
	@GetMapping("/api/todos")
	public List<Todo> getTodoList() {
		List<Todo> todos = new ArrayList<Todo>();
		Todo todo = new Todo();
		for (int i = 0; i < 10; i ++) {
			todo = new Todo();
			todo.setId(i + "");
			todo.setComplete(false);
			todo.setCommont("随便说点吧");
			todo.setLanguage("java");
			todos.add(todo);
		}
		return todos;
	}

	@GetMapping("/api/todo/{id}")
	public List<Todo> getTodo(@PathVariable("id") String id) {
		List<Todo> todos = new ArrayList<Todo>();
		Todo todo = new Todo();
		for (int i = 0; i < 10; i ++) {
			todo.setId(i + "");
			todo.setComplete(false);
			todo.setCommont("随便说点吧");
			todo.setLanguage("java");
			todos.add(todo);
		}
		return todos;
	}

	@GetMapping("/api/userInfo")
	public Map<String, Object> getUserInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "小王");
		map.put("role", "系统管理员");
		return map;
	}

	@GetMapping("/api/users")
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setId("1");
		user.setName("小王");
		user.setRole("admin");
		user.setDate("2016-05-02");
		user.setAddress("上海市普陀区金沙江路 1518 弄");
		users.add(user);

		User user2 = new User();
		user2.setId("2");
		user2.setName("王小虎");
		user2.setRole("admin");
		user2.setDate("2016-05-02");
		user2.setAddress("上海市普陀区金沙江路 1518 弄");
		users.add(user2);

		User user3 = new User();
		user3.setId("3");
		user3.setName("小王");
		user3.setRole("system");
		user3.setDate("2016-05-02");
		user3.setAddress("上海市普陀区金沙江路 1518 弄");
		users.add(user3);

		User user4 = new User();
		user4.setId("4");
		user4.setName("小王");
		user4.setRole("system");
		user4.setDate("2016-05-02");
		user4.setAddress("上海市普陀区金沙江路 1518 弄");
		users.add(user4);
		return users;
	}

	@DeleteMapping("/api/user/{id}")
	public String deleteUser(@PathVariable("id") String id) {
		return id;
	}

	@PutMapping("/api/user/{id}")
	public User updateUser(@PathVariable("id") String id, @RequestBody User user) {
		return user;
	}

	@GetMapping("/api/user/{id}")
	public User getUser() {
		User user = new User();
		user.setId("1");
		user.setName("小王");
		user.setRole("admin");
		user.setDate("2016-05-02");
		user.setAddress("上海市普陀区金沙江路 1518 弄");
		return user;
	}

	@PostMapping("/api/user")
	public User addUser(@RequestBody User user) {
		return user;
	}

}
