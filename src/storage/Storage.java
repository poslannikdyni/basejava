package storage;

import model.Resume;

public interface Storage {
    Resume get(String uuid);
    void save(Resume r);
    void update(Resume resume);
    void delete(String uuid);
    void clear();
    int size();
    Resume[] getAll();
}
