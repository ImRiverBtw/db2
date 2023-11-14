package nl.hva.ict.data;

import java.util.List;

/**
 * Interface voor alle database classes
 * @param <T> Het model wat je gebruikt
 * @author HBO-ICT
 */
public interface Data<T> {

    List<T> getAll();

    T get();

    void add(T object);

    void update(T object);

    void remove(T object);
}
