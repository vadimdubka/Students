package dao;// Created by sky-vd on 19.07.2017.

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ProfessionInterceptor implements MethodInterceptor {

    /*Как видите я не стал много придумывать — просто сделал два вывода на экран — до и после метода Object rval = invocation.proceed();. Если внимательно посмотреть в документации, что из себя представляет класс MethodInvocation, то можно найти у него метод getMethod, который в свою очередь возвращает объект типа java.lang.reflect.Method. А уж этот стандартный класс дает полную информацию о том, что и как в нашем методе происходит.*/
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before: invocation=[" + invocation + "]");
        Object rval = invocation.proceed();
        System.out.println("Invocation returned");
        return rval;
    }
}