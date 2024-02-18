package com.example.demospring.db;

import com.example.demospring.entity.Note;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Data
public class FakeDb {
    private List<Note> notes = new ArrayList<>();
    public Note createNote(Note note){
        note.setId(UUID.randomUUID());
        this.notes.add(note);
        return note;
    }

    public Note findNoteById(UUID id){
        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    public boolean deleteNoteById(UUID id){
//        for (Iterator<Note> iterator = notes.listIterator(); iterator.hasNext(); ) {
//            Note note = iterator.next();
//            if (note.getId().equals(id)) {
//                iterator.remove();
//            }
//        }
        Iterator<Note> iterator = notes.iterator();
        while (iterator.hasNext()) {
            Note note = iterator.next();
            if (note.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Note> getAllNotes() {
        return this.notes;
    }

    public boolean updateNote(Note note){
        if(Objects.isNull(findNoteById(note.getId()))){
            return false;
        }
        this.findNoteById(note.getId()).setTitle(note.getTitle());
        this.findNoteById(note.getId()).setContent(note.getContent());
        return true;
    }
}
