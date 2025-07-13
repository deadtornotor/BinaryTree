package cc.deadtornotor.binarytree.cli;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class MenuAction {
    private final String name;
    private final Object target;
    private final MethodParameter[] methodParameters;
    private final String methodName;
    private final CLI cli;

    public MenuAction(CLI cli, String name, Object target, String methodName, MethodParameter... methodParameters) {
        this.name = name;
        this.target = target;
        this.methodParameters = methodParameters;
        this.methodName = methodName;
        this.cli = cli;
    }

    public MenuAction(String name, Object target, String methodName, MethodParameter... methodParameters) {
        this(new CLI(), name, target, methodName, methodParameters);
    }

    public String name() {
        return name;
    }

    public void execute() {
        try {
            Class<?>[] paramTypes = Arrays.stream(methodParameters)
                    .map(MethodParameter::parameterType)
                    .toArray(Class<?>[]::new);
            Method method = target.getClass().getMethod(methodName, paramTypes);
            Object[] args = promptForArguments(methodParameters);
            method.invoke(target, args);
        } catch (Exception e) {
            System.err.println("Error executing action: " + e.getMessage());
        }
    }

    private Object[] promptForArguments(MethodParameter[] parameters) {
        Object[] args = new Object[parameters.length];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < parameters.length; i++) {
            Class<?> type = parameters[i].parameterType();
            String paramName = parameters[i].parameterName();

            args[i] = cli.input(type, () -> {
                cli.println("Enter " + paramName + " (" + type.getSimpleName() + ")");
            });
        }

        return args;
    }
}