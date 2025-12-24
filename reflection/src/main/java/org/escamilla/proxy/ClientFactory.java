package org.escamilla.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public final class ClientFactory {
    public static Client fromPropertyMap(Map<String, Object> properties) {
        Object proxy = Proxy.newProxyInstance(
                Client.class.getClassLoader(),
                new Class<?>[]{Client.class},
                new Handler(properties)
        );
        return (Client) proxy;
    }

    private ClientFactory() {
    }

    private record Handler(Map<String, Object> properties) implements InvocationHandler {

        @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                if (method.getDeclaringClass().equals(Object.class)) {
                    try {
                        return method.invoke(properties, args);
                    } catch (InvocationTargetException e) {
                        throw e.getTargetException();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (methodName.length() <= 3 || !methodName.startsWith("get")) {
                    throw new RuntimeException("Method is not property getter: " + methodName);
                }
                String propertyName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                if (!properties.containsKey(propertyName)) {
                    throw new RuntimeException("No property named \"" + propertyName + "\" found in map.");
                }
                return properties.get(propertyName);
            }
        }
}
