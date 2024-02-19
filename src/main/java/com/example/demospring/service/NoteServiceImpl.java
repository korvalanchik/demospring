package com.example.demospring.service;

import com.example.demospring.entity.Note;
import com.example.demospring.db.FakeDb;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService{
    private FakeDb fakeDb;
    @Autowired
    public void fakeDb(FakeDb fakeDb) {
        this.fakeDb = fakeDb;
    }
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
        }
    }

    @Override
    public void update(Note note) {
        fakeDb.updateNote(note);
    }

    @Override
    public Note getById(UUID id) {
        return fakeDb.findNoteById(id);
    }

    static class NotDeleteException extends Exception {
        public NotDeleteException(String code) {
            super(code);
        }
    }

    @PostConstruct
    public void init() throws NotDeleteException {
        System.out.println("Start");
        Note note1 = new Note();
        note1.setTitle("Libero iaculis equidem inciderint dicta.");
        note1.setContent("Postea proin equidem deserunt voluptatum dicunt utroque. Arcu laudem dictum dicat.");
        add(note1);
        Note note2 = new Note();
        note2.setTitle("Commune vero reque te lacus cubilia agam.");
        note2.setContent("Antiopam perpetua intellegat euismod solum. Epicuri vehicula urbanitas lobortis.");
        add(note2);
        UUID uuid = note2.getId();

        List<Note> noteList = listAll();
        System.out.println("All notes: " + noteList);

        System.out.println("Note by Id " + uuid + ": " + getById(uuid));
        note2.setContent("Morbi porta conceptam sollicitudin error.");
        update(note2);
        System.out.println("After update: " + getById(uuid));
        Note deleteNote = noteList.get(0);
        deleteById(deleteNote.getId());
        System.out.println("All notes: " + noteList);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Stop");
    }
}
