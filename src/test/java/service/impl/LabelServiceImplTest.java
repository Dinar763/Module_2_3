package service.impl;

import model.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.LabelRepository;
import service.LabelService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LabelServiceImplTest {

    @Mock
    private LabelRepository mockRepository;

    private LabelService labelService;

    private final Label testLabel1 = new Label(1L, "Label Test1", new ArrayList<>());
    private final Label testLabel2 = new Label(2L, "Label Test1", new ArrayList<>());
    private final List<Label> labels = List.of(testLabel1, testLabel2);

    @BeforeEach
    void setup() {
        labelService = new LabelServiceImpl(mockRepository);
    }

    @Test
    void testGetByID() {
        when(mockRepository.getById(1L)).thenReturn(testLabel1);
        Label result = mockRepository.getById(1L);
        assertEquals("Label Test1", result.getName());
        verify(mockRepository).getById(1L);
    }

    @Test
    void testGetAll() {
        when(mockRepository.getAll()).thenReturn(labels);
        List<Label> result = labelService.getAll();

        assertEquals(2, result.size());
        verify(mockRepository).getAll();
    }


    @Test
    void testSave() {
        labelService.save(new Label(3L, "History", new ArrayList<>()));
        verify(mockRepository).save(new Label(3L, "History", new ArrayList<>()));
    }

    @Test
    void testUpdate() {
        Label updatedLabel = new Label(1L, "New Name", new ArrayList<>());

        when(mockRepository.update(updatedLabel)).thenReturn(updatedLabel);

        Label resultLabel = labelService.update(updatedLabel);
        assertEquals("New Name", resultLabel.getName());
        verify(mockRepository).update(updatedLabel);
    }

    @Test
    void testDeleteByID() {
        labelService.deleteById(1l);
        verify(mockRepository).deleteById(1l);
    }
}