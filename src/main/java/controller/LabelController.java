package controller;


import lombok.AllArgsConstructor;
import model.Label;
import service.LabelService;

import java.util.List;

@AllArgsConstructor
public class LabelController {
    private final LabelService labelService;

    public Label getByID(Long id) {
        return labelService.getByID(id);
    }

    public List<Label> getAll() {
        return labelService.getAll();
    }

    public Label save(Label label) {
        return labelService.save(label);
    }

    public Label update(Label label) {
        return labelService.update(label);
    }

    public void deleteById(Long id) {
        if (id == null) throw new RuntimeException("ID must be not null");
        labelService.deleteById(id);
    }
}
