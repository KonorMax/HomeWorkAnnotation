package HomeWorkAnnotation;

        /*Создать собственную аннотацию - доступ к аннотациям во времени исполнения.
        Аннотация с двумя полями - типа int (уровень доступа) и String (сообщение),
        целевое использование TYPE, аннотировать класс, создать статический метод по получению данных об этой аннотации,
        используя внутри стандартный метод по получению данных об используемой
        аннотации и вывести выводу полей данной аннотации в консоль. Продемонстрировать работу метода в main
        */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation{
    String str();
    int value();
}

class AnnotationData{
    @MyAnnotation(str = "Test annotation", value = 777)
    public static void myMethod(){
        AnnotationData annotationData = new AnnotationData();
        try {
            Class<?> c = annotationData.getClass();
            Method method = c.getMethod("myMethod");
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation.str()+ " " + myAnnotation.value());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        myMethod();
    }
}
