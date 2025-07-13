package cc.deadtornotor.binarytree.demo;

import cc.deadtornotor.binarytree.cli.CLI;
import cc.deadtornotor.binarytree.cli.Menu;

public class BinaryTreeDemo {
    private final CLI cli;
    private final Menu menu;

    public BinaryTreeDemo(CLI cli) {
        this.cli = cli;
        this.menu = new Menu("Binary Tree Demo", cli);
    }

    public BinaryTreeDemo() {
        this(new CLI());
    }

    public void run() {
        menu.loop();
    }
}
