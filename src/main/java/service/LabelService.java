package service;

import model.Label;

public interface LabelService extends GenericService <Label>{
    Label save(Label label);
    Label update(Label label);
}
