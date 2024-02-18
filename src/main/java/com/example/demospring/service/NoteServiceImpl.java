package com.example.demospring.service;

import com.example.demospring.entity.Note;
import com.example.demospring.db.FakeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class NoteServiceImpl implements NoteService{
    @Autowired
    private FakeDb fakeDb;
    @Override
    public List<Note> listAll() {
        return fakeDb.getAllNotes();
    }

    @Override
    public Note add(Note note) {
        return fakeDb.createNote(note);
    }

    @Override
    public void deleteById(UUID id) throws NotDeleteException {
        if(!(fakeDb.deleteNoteById(id))){
            throw new NotDeleteException("Deleting error");
        };
    }

    @Override
    public void update(Note note) {
        fakeDb.updateNote(note);
    }
    static class NotDeleteException extends Exception {
        public NotDeleteException(String code) {
            super(code);
        }
    }

}
