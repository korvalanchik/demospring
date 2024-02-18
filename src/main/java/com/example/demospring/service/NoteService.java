package com.example.demospring.service;

import com.example.demospring.entity.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    List<Note> listAll();
    Note add(Note note);
    void deleteById(UUID id) throws NoteServiceImpl.NotDeleteException;
    void update(Note note);
}
