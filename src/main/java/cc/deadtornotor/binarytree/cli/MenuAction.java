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
    private final boolean askToContinue;

    public MenuAction(CLI cli, String name, Object target, String methodName, boolean askToContinue, MethodParameter... methodParameters) {
        this.name = name;
        this.target = target;
        this.methodParameters = methodParameters;
        this.methodName = methodName;
        this.cli = cli;
        this.askToContinue = askToContinue;
    }

    public MenuAction(String name, Object target, String methodName, boolean askToContinue, MethodParameter... methodParameters) {
        this(new CLI(), name, target, methodName, askToContinue, methodParameters);
    }

    public String name() {
        return name;
    }

    public void execute() {
        try {
            Class<?>[] paramTypes = Arrays.stream(methodParameters)
                    .map(MethodParameter::type)
                    .toArray(Class<?>[]::new);
            Method method = target.getClass().getMethod(methodName, paramTypes);
            Object[] args = promptForArguments(methodParameters);
            method.invoke(target, args);
        } catch (Exception e) {
            cli.error("Error executing action: " + e.getMessage());
        }

        if (askToContinue) {
            cli.input(null, () -> {
                cli.println("Press ENTER to continue");
            });
        }
    }

    private Object[] promptForArguments(MethodParameter[] parameters) {
        Object[] args = new Object[parameters.length];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < parameters.length; i++) {
            Class<?> type = parameters[i].type();
            String paramName = parameters[i].name();

            args[i] = cli.input(type, () -> {
                cli.println("Enter " + paramName + " (" + type.getSimpleName() + ")");
            });
        }

        return args;
    }
}