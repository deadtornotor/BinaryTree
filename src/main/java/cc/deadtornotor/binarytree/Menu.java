package cc.deadtornotor.binarytree;

import java.util.HashMap;

class MenuItem {
    public String name;
    public Runnable runnable;
    
    public MenuItem(String name, Runnable runnable) {
        this.name = name;
        this.runnable = runnable;
    }
}

public class Menu {
    private final HashMap<Integer, MenuItem> map = new HashMap<Integer, MenuItem>();
    private final String name;

    public Menu(String name) {
        this.name = name;
    }

    public boolean add(int index, String name, Runnable runnable) {
        if (map.containsKey(index)) {
            return false;
        }

        map.put(index, new MenuItem(name, runnable));
        return true;
    }

    public void print() {
        System.out.println("<---- " + name + " ---->");

        map.forEach((index, menuItem) -> {
            System.out.println("[" + index + "] " + menuItem.name);
        });
    }

    public boolean run(int index) {
        if (!map.containsKey(index)) {
            return false;
        }

        map.get(index).runnable.run();
        return true;
    }
}
