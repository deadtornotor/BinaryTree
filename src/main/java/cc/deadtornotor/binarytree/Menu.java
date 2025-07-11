package cc.deadtornotor.binarytree;

import java.util.HashMap;

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

    @Override
    public boolean equals(Object obj) {
        if (this  == obj) {
            return true;
        }
        else if (!(obj instanceof MenuItem)) {
            return false;
        }
        MenuItem other = (MenuItem) obj;

        return this.index == other.index;
    }
}

public class Menu {
    private final BinaryTree<MenuItem> menuTree = new BinaryTree<MenuItem>();
    private final String name;

    public Menu(String name) {
        this.name = name;
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
        System.out.println("<---- " + name + " ---->");
        
        menuTree.forEach((menuItem) -> {
            System.out.println("[" + menuItem.index + "] " + menuItem.name);
        });
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
