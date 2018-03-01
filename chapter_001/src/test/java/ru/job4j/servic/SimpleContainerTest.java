package ru.job4j.servic;


import org.junit.Test;
import ru.job4j.service.*;

public class SimpleContainerTest <T extends Base> {

    @Test
    public void testAllContainers () {
        UserStore a = new UserStore(7);
        RoleStore r = new RoleStore(7);
        a.add(new User("first"));
        a.add(new User("second"));
        a.add(new User("third"));
        a.add(new User("four"));
        a.add(new User("five"));
        a.add(new User("six"));
        System.out.println(a.toString());
        boolean eee = a.delete("third");
        System.out.println(a.toString());
        boolean e = a.replace("first", new User("new four"));
        System.out.println(a.toString());

        r.add(new Role("first"));
        r.add(new Role("second"));
        r.add(new Role("third"));
        r.add(new Role("four"));
        r.add(new Role("five"));
        r.add(new Role("six"));
        System.out.println(r.toString());
        boolean edq = r.delete("third");
        System.out.println(r.toString());
        boolean fsde = r.replace("first", new Role("new four"));
        System.out.println(r.toString());
        User aaa = (User) (a.findById("first"));
        Role dsa = (Role) (r.findById("first"));
    }

    @Test
    public void testListContaines(){
        LinkedList node = new LinkedList();
        LinkedList.Node nodee = node.first;
        node.add("j ");
        node.add("t ");
        node.add("h ");
        node.add("k ");
        node.add("v ");
        node.add("e ");
        node.add("n ");
        node.add("c ");
        node.add(", ");
        System.out.println(node.toString());
        System.out.println(node.get(3).toString());

    }

    @Test
    public  void QueuTest() {
        SimpleQueu queu = new SimpleQueu();
        queu.push(" aw");
        queu.push("dwas");
        queu.push("dwas");
        queu.push("dwas");
        queu.push("dwas");
        queu.push("dwas");
        queu.push("dwas");
        queu.push("dwas");
        System.out.println(queu.toString());

        queu.poll();
        System.out.println(queu.toString());

        SimpleStack stack = new SimpleStack();
        stack.push(" aw");
        stack.push("dwas");
        stack.push("dwas");
        stack.push("dwas");
        stack.push("dwas");
        stack.push("dwas");
        stack.push("dwas");
        stack.push("dwas");
        System.out.println(stack.toString());

        stack.poll();
        System.out.println(stack.toString());
        stack.poll();
        System.out.println(stack.toString());
        stack.poll();
        System.out.println(stack.toString());
        stack.poll();
        System.out.println(stack.toString());
        stack.poll();
        System.out.println(stack.toString());
        stack.poll();
        stack.poll();
        System.out.println(stack.toString());
        System.out.println(stack.toString());
        stack.poll();
        System.out.println(stack.toString());

    }

    @Test
    public void f(){
        SimpleSet set = new SimpleSet(5);
        set.add("ghjk");
        set.add("jkl");
        set.add("ghjk");
        set.add("ghjk");
        set.add("ghjk");
        set.add("ghjk");
        set.add("ghjk");
        set.add("ghjk");
        if (set.hasNext())
        System.out.println(set.next().toString());
        if (set.hasNext())
        System.out.println(set.next().toString());
        if (set.hasNext())
        System.out.println(set.next().toString());
        if (set.hasNext())
        System.out.println(set.next().toString());
    }

    @Test
    public void simpleLinledSetTest(){
        SimpleLinkedSet set = new SimpleLinkedSet();
        set.add("dasfds");
        set.add("asd");
        set.add("asd");
        System.out.println(set.toString());
        set.iterator();
        System.out.println(set.iterator().hasNext());
        System.out.println(set.iterator().next());
        System.out.println(set.iterator().hasNext());
        System.out.println(set.iterator().next());
        System.out.println(set.iterator().hasNext());
    }

    @Test
    public void simpleSet(){
        Integer[] integ = new Integer[4];
        HashSet set = new HashSet(integ);
        set.add(-1);
        set.add(1);
        set.add(7);
        set.add(0);
        System.out.println(set.toString());
        set.remove(7);
        System.out.println(set.toString());

    }
}