package cc.deadtornotor.binarytree.cli;

public class MethodParameter {
    private final String parameterName;
    private final Class<?> parameterType;

    public MethodParameter(String parameterName, Class<?> parameterType) {
        this.parameterName = parameterName;
        this.parameterType = parameterType;
    }

    public Class<?> parameterType() {
        return this.parameterType;
    }

    public String parameterName() {
        return this.parameterName;
    }
}
