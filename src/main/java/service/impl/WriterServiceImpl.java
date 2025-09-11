package service.impl;

import exception.NotFoundException;
import exception.ServiceException;
import exception.ValidationException;
import lombok.AllArgsConstructor;
import model.Writer;
import repository.WriterRepository;
import service.WriterService;

import java.util.List;

@AllArgsConstructor
public class WriterServiceImpl implements WriterService {

    private final WriterRepository repository;

    @Override
    public Writer getByID(Long id) {
        if (id == null) {
            throw new ServiceException("ID must be not null");
        }
        return repository.getById(id);
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = repository.getAll();
        if (writers == null || writers.isEmpty()) {
            throw  new NotFoundException("No writers found");
        }
        return writers;
    }

    @Override
    public Writer save(Writer entity) {
        if (entity == null) {
            throw new ServiceException("Writer must be not null");
        }
        return repository.save(entity);
    }

    @Override
    public Writer update(Writer entity) {
        if (entity == null) {
            throw new ServiceException("Writer must be not null");
        }
        if (entity.getId() == null) {
            throw new ValidationException("Writer ID must be not null for update");
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
