package cc.deadtornotor.binarytree.demo;

import cc.deadtornotor.binarytree.cli.CLI;
import cc.deadtornotor.binarytree.cli.Menu;
import cc.deadtornotor.binarytree.cli.MenuAction;
import cc.deadtornotor.binarytree.cli.MethodParameter;

public class BinaryTreeDemo {
    private final CLI cli;
    private final Menu menu;

    public BinaryTreeDemo(CLI cli) throws NoSuchMethodException {
        this.cli = cli;
        this.menu = new Menu("Binary Tree Demo", cli);

        menu.add(1, new MenuAction(
                "Insert random values",
                this,
                "insertRandom",
                new MethodParameter("Amount", int.class),
                new MethodParameter("Max value", int.class),
                new MethodParameter("Min value", int.class))
        );
    }

    public BinaryTreeDemo() throws NoSuchMethodException {
        this(new CLI());
    }

    public void run() {
        menu.loop();
    }

    public void insertRandom(int amount, int maxValue, int minValue) {
        cli.println(amount + " " + maxValue + " " + minValue);
    }
}
