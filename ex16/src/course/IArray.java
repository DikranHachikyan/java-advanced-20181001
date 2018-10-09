package course;

/**
 *
 * @author wizard
 */
public interface IArray<T extends Comparable<T> > {
    public T min();
    public T max();
}
