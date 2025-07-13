package cc.deadtornotor.binarytree;

import java.util.Scanner;

class MenuItem implements Comparable<MenuItem>{
    public int index;
    public String name;
    public Runnable runnable;

    public MenuItem(int index) {
        this.index = index;
    }

    public MenuItem(int index, String name, Runnable runnable) {
        this.index = index;
        this.name = name;
        this.runnable = runnable;
    }

    @Override
    public int compareTo(MenuItem menuItem) {
        return Integer.compare(this.index, menuItem.index);
    }
}

public class Menu {
    private final BinaryTree<MenuItem> menuTree = new BinaryTree<MenuItem>();
    private final String name;
    private final CLI cli;
    private boolean loop = false;
    static Scanner sc = new Scanner(System.in);
    private boolean dirty = false;

    public Menu(String name, CLI cli) {
        this.name = name;
        this.cli = cli;

        add(0, "Exit", this::stop);
    }

    public Menu(String name) {
        this(name, new CLI());
    }

    public boolean add(int index, String name, Runnable runnable) {
        if (menuTree.exists((new MenuItem(index)))) {
            return false;
        }

        MenuItem item = new MenuItem(index, name, runnable);
        menuTree.insert(item);
        dirty = true;
        return true;
    }

    public void loop() {
        loop = true;

        while (loop) {
            if (dirty) {
                menuTree.balance();
                dirty = false;
            }

            this.print();

            int choice = sc.nextInt();

            if (!this.run(choice)) {
                cli.error("Invalid choice.");
            }
        }

        cli.info("Exiting...");
    }

    public void stop() {
        loop = false;
    }

    public void print() {
        // Colored example, adjust as you like

        cli.borderedPrintln(name, Color.GREEN, null, Color.GREEN);

        menuTree.forEach((menuItem) -> {
            cli.println("[" + menuItem.index + "] " + menuItem.name);
        });

        cli.print(System.getProperty("user.name"), Color.ORANGE);
        cli.print("@");
        cli.print("binarytree ", Color.CYAN);
        cli.rainbowPrint("~/ $ ");
    }

    public boolean run(int index) {
        MenuItem indexItem = new MenuItem(index);

        if (!menuTree.exists(indexItem)) {
            return false;
        }

        menuTree.get(indexItem).runnable.run();
        return true;
    }
}
