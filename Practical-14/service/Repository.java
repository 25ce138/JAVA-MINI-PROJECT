package service;
import model.annotation.Id;
import java.lang.reflect.Field;
import java.util.*;

public class Repository<T> {
    private final Map<String, T> store = new HashMap<>();

    private String extractId(T entity) {
        Class<?> cls = entity.getClass();
        while (cls != null && cls != Object.class) {
            for (Field f : cls.getDeclaredFields()) {
                if (f.isAnnotationPresent(Id.class)) {
                    f.setAccessible(true);
                    try {
                        return String.valueOf(f.get(entity));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        throw new IllegalArgumentException("No @Id field found on " + entity.getClass().getName());
    }

    public T save(T entity) {
        store.put(extractId(entity), entity);
        return entity;
    }

    public T findById(String id) { return store.get(id); }
    public ArrayList<T> findAll() { return new ArrayList<>(store.values()); }
    public boolean delete(String id) { return store.remove(id) != null; }
}
