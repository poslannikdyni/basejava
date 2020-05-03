package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage{
    public static final int STORAGE_LIMIT = 10000;
    private int size = 0;
    private Resume[] storage = new Resume[STORAGE_LIMIT];

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("storage.ArrayStorage [get] ERROR: storage no resume with uuid " + uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("storage.ArrayStorage [delete] ERROR: storage no resume with uuid " + uuid);
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("storage.ArrayStorage [save] ERROR: storage contains resume with uuid call update() method " + r.getUuid());
        }else if (size >= storage.length) {
            System.out.println("storage.ArrayStorage [save] ERROR: storage overflow");
        }else {
            storage[size] = r;
            size++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++)
            if (storage[i].getUuid().equals(uuid))
                return i;
        return -1;
    }

    public void clear() {
        Arrays.fill(storage,0, size, null);
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("storage.ArrayStorage [update] ERROR: no resume with uuid " + resume.getUuid());
            return;
        }

        storage[index] = resume;
    }
}
