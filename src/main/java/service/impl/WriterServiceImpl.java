package service.impl;

import exception.NotFoundException;
import exception.ServiceException;
import exception.ValidationException;
import lombok.AllArgsConstructor;
import model.Label;
import model.Writer;
import repository.WriterRepository;
import service.WriterService;

import java.util.Collections;
import java.util.List;

import static util.HibernateUtil.*;

@AllArgsConstructor
public class WriterServiceImpl implements WriterService {

    private final WriterRepository repository;

    @Override
    public Writer getByID(Long id) {
        if (id == null) {
            throw new ServiceException("ID must be not null");
        }
        beginTransaction();
        try {
            Writer getedrWriter = repository.getById(id);
            commitTransaction();
            return getedrWriter;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to get writer with id: " + id, e);
        }
    }

    @Override
    public List<Writer> getAll() {
        beginTransaction();
        try {
            List<Writer> writers = repository.getAll();
            commitTransaction();

            return writers != null ? writers : Collections.emptyList();
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to get all writers: " +  e);
        }
    }

    @Override
    public Writer save(Writer entity) {
        validateWriter(entity);
        beginTransaction();
        try {
            Writer savedWriter = repository.save(entity);
            commitTransaction();
            return savedWriter;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to save writer", e);
        }
    }

    @Override
    public Writer update(Writer entity) {
        validateWriter(entity);
        beginTransaction();
        try {
            Writer updatedWriter = repository.update(entity);
            commitTransaction();
            return updatedWriter;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to update writer", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new ServiceException("ID must be not null");
        }
        beginTransaction();
        try {
            repository.deleteById(id);
            commitTransaction();
        } catch (ServiceException e) {
            rollbackTransaction();
            throw e;
        }
    }

    private void validateWriter(Writer writer) {
        if (writer == null) {
            throw new ValidationException("writer must not be null");
        }
        if (writer.getFirstname() == null || writer.getLastname() == null
                || writer.getFirstname().isBlank() || writer.getLastname().isBlank()) {
            throw new ValidationException("writer name must not be empty");
        }
    }
}
