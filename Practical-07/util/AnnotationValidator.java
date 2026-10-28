package util;
import model.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

public class AnnotationValidator {
    public static String[] validate(Object obj) throws IllegalAccessException {
        List<String> errors = new ArrayList<>();
        Class<?> cls = obj.getClass();
        while (cls != null && cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                Object val = field.get(obj);
                if (field.isAnnotationPresent(Positive.class) && val instanceof Number num) {
                    if (num.longValue() <= 0) {
                        errors.add(field.getAnnotation(Positive.class).message());
                    }
                }
                if (field.isAnnotationPresent(MaxLength.class) && val instanceof String s) {
                    int max = field.getAnnotation(MaxLength.class).value();
                    if (s.length() > max) {
                        errors.add("exceeds max length " + max);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return errors.toArray(new String[0]);
    }
}
