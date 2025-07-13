package cc.deadtornotor.binarytree;

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

    public Menu(String name, CLI cli) {
        this.name = name;
        this.cli = cli;
    }

    public Menu(String name) {
        this.name = name;
        this.cli = new CLI();
    }

    public boolean add(int index, String name, Runnable runnable) {
        if (menuTree.exists((new MenuItem(index)))) {
            return false;
        }

        MenuItem item = new MenuItem(index, name, runnable);
        menuTree.insert(item);
        return true;
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
