package cc.deadtornotor.binarytree.cli;

import cc.deadtornotor.binarytree.BinaryTree;

class MenuItem implements Comparable<MenuItem> {
    public int index;
    public MenuAction action;

    public MenuItem(int index) {
        this.index = index;
    }

    public MenuItem(int index, MenuAction action) {
        this.index = index;
        this.action = action;
    }

    @Override
    public int compareTo(MenuItem menuItem) {
        return Integer.compare(this.index, menuItem.index);
    }
}

public class Menu {
    private final BinaryTree<MenuItem> menuTree = new BinaryTree<>();
    private final String name;
    private final CLI cli;
    private boolean loop = false;

    public Menu(String name) {
        this.cli = CLI.getInstance();
        this.name = name;

        add(0, new MenuAction("Exit", this, "stop", false));
    }

    public boolean add(int index, MenuAction action) {
        if (menuTree.exists((new MenuItem(index)))) {
            return false;
        }

        MenuItem item = new MenuItem(index, action);
        menuTree.insert(item);
        return true;
    }

    public void loop() {
        loop = true;

        while (loop) {
            if (menuTree.isUnbalanced()) {
                menuTree.balance();
            }

            this.print();
            int choice = (int) cli.input(int.class);

            if (!this.execute(choice)) {
                cli.error("Invalid choice");
            }
        }

        cli.info("Exiting...");
    }

    public void stop() {
        loop = false;
    }

    public void print() {
        cli.println();
        cli.borderedPrintln(name, Color.GREEN, null, Color.GREEN);

        menuTree.forEach((menuItem) -> {
            cli.println("[" + menuItem.index + "] " + menuItem.action.name());
        });
    }

    public boolean execute(int index) {
        MenuItem indexItem = new MenuItem(index);

        if (!menuTree.exists(indexItem)) {
            return false;
        }

        menuTree.get(indexItem).action.execute();
        return true;
    }
}
