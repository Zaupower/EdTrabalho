import Listl.LinkedList;

import java.util.Iterator;

public class Person<T> {
    String name;
    LinkedList<Edge> friends;

    public Person(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Iterator getFriends() {
        return friends.iterator();
    }

    public void addFrind(Edge friend) {
        this.friends.add(friend);
    }
}
