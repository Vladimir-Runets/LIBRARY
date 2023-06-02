package by.fpmibsu.LIBRARY.service;

import by.fpmibsu.LIBRARY.dao.LiteratureDAO;
import by.fpmibsu.LIBRARY.dto.LiteratureDto;
import by.fpmibsu.LIBRARY.entity.Literature;

import java.util.List;
import java.util.stream.Collectors;

public class LiteratureService {
    private static final LiteratureService INSTANCE = new LiteratureService();
    private final LiteratureDAO literatureDAO = LiteratureDAO.getInstance();

    public LiteratureService(){}

    public List<LiteratureDto> findAll(){
        return literatureDAO.findAll().stream()
                .map(literature -> new LiteratureDto(
                        literature.getLiteratureID(), literature.getText()
                )).collect(Collectors.toList());
    }

    public static LiteratureService getInstance(){
        return INSTANCE;
    }
    public static Literature getAll(){
        return new Literature();
    }
}
