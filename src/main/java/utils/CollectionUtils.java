package utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {

    public static <T> List<T> listOf(final T... entries) {
	final List<T> list = new ArrayList<T>();

	for (final T t : entries) {
	    list.add(t);
	}

	return list;
    }
}
