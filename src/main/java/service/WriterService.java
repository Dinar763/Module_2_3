package service;

import model.Writer;

public interface WriterService extends GenericService <Writer> {

    Writer save(Writer writer);
    Writer update(Writer writer);
}
