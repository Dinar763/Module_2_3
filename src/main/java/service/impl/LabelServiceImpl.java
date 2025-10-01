package service.impl;

import exception.ServiceException;
import exception.ValidationException;
import lombok.AllArgsConstructor;
import model.Label;
import repository.LabelRepository;
import service.LabelService;

import java.util.Collections;
import java.util.List;

import static util.HibernateUtil.*;

@AllArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelRepository repository;

    @Override
    public Label getByID(Long id) {
        if (id == null) {
            throw new ServiceException("ID must be not null");
        }
        beginTransaction();
        try {
            Label getedLabel = repository.getById(id);
            commitTransaction();
            return getedLabel;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to get label with id: " + id, e);
        }
    }

    @Override
    public List<Label> getAll() {
        beginTransaction();
        try {
            List<Label> labels = repository.getAll();
            commitTransaction();

            return labels != null ? labels : Collections.emptyList();
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to get all labels: " +  e);
        }
    }

    @Override
    public Label save(Label entity) {
        validateLabel(entity);
        beginTransaction();
        try {
            Label savedLabel = repository.save(entity);
            commitTransaction();
            return savedLabel;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to save label", e);
        }
    }

    @Override
    public Label update(Label label) {
        validateLabel(label);
        beginTransaction();
        try {
            Label updatedLabel = repository.update(label);
            commitTransaction();
            return updatedLabel;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to update label", e);
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

    private void validateLabel(Label label) {
        if (label == null) {
            throw new ValidationException("Label must not be null");
        }
        if (label.getName() == null || label.getName().isBlank()) {
            throw new ValidationException("Label name must not be empty");
        }
    }
}
