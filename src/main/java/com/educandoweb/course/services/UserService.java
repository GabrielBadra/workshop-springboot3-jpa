package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository entityRepository;

	public List<User> findAll() {
		return entityRepository.findAll();
	}

	public User findById(Long id) {
		Optional<User> entity = entityRepository.findById(id);
		return entity.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User insert(User entity) {
		return entityRepository.save(entity);
	}

	public void delete(Long id) {
		try {
			entityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	// ESSE ID É O ID DO OBJETO USER QUE QUER ATUALIZAR
	public User uptade(Long id, User obj) {
		try {
			User entity = entityRepository.getReferenceById(id);
			uptadeData(entity, obj);
			return entityRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void uptadeData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
