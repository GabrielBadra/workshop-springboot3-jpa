package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository entityRepository;
	
	public List<User> findAll() {
		return entityRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> entity = entityRepository.findById(id);
		return entity.get();
	}
	
	public User insert(User entity) {
		return entityRepository.save(entity);
	}
	
	public void delete(Long id) {
		entityRepository.deleteById(id);
	}
	
	//ESSE ID É O ID DO OBJETO USER QUE QUER ATUALIZAR
	public User uptade(Long id, User entity) {
		User entity = entityRepository.getReferenceById(id);
		uptadeData(entity, entity);
		return entityRepository.save(entity);
	}
	
	private void uptadeData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
