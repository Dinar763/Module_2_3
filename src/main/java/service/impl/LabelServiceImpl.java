package service.impl;

import exception.NotFoundException;
import exception.ServiceException;
import exception.ValidationException;
import lombok.AllArgsConstructor;
import model.Label;
import repository.LabelRepository;
import service.LabelService;

import java.util.List;

@AllArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelRepository repository;

    @Override
    public Label getByID(Long id) {
        if (id == null) {
            throw new ServiceException("ID must be not null");
        }
        return repository.getById(id);
    }

    @Override
    public List<Label> getAll() {
        List<Label> labels = repository.getAll();
        if (labels == null || labels.isEmpty()) {
            throw new NotFoundException("No labels found");
        }
        return labels;
    }

    @Override
    public Label save(Label entity) {
        if (entity == null) {
            throw new ServiceException("Label must be not null");
        }
        if (entity.getName() == null || entity.getName().isBlank()) {
            throw new ValidationException("Label name must be not empty");
        }
        return repository.save(entity);
    }

    @Override
    public Label update(Label entity) {
        if (entity == null) {
            throw new ServiceException("Label must be not null");
        }
        if (entity.getId() == null) {
            throw new ValidationException("Label ID must be not null for update");
        }
        if (entity.getName() == null || entity.getName().isBlank()) {
            throw new ValidationException("Label name must be not empty");
        }
        return repository.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new ServiceException("ID must be not null");
        }
        repository.deleteById(id);
    }
}
