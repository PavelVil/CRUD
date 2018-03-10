package contacts.utils;

import java.util.*;

import com.sun.istack.internal.*;


public class CollectionsUtil {

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static <T> boolean isHasOneEntity(List<T> list) {
        return !isEmpty(list) && list.size() == 1;
    }

    public static <T> T getOneFrom(List<T> list) {
        return isHasOneEntity(list) ? list.iterator().next() : null;
    }

}
