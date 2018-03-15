package ru.job4j.servic;


import org.junit.Before;
import org.junit.Test;
import ru.job4j.service.*;
import ru.job4j.store.RoleStore;
import ru.job4j.store.UserStore;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class SimpleContainerTest <T extends Base> {

//    @Test
//    public void testAllContainers () {
//        UserStore<ru.job4j.store.User> a = new UserStore(7);
//        RoleStore r = new RoleStore(7);
//        a.add(new ru.job4j.store.User("first"));
//        a.add(new ru.job4j.store.User("second"));
//        a.add(new ru.job4j.store.User("third"));
//        a.add(new ru.job4j.store.User("four"));
//        a.add(new ru.job4j.store.User("five"));
//        a.add(new ru.job4j.store.User("six"));
//        System.out.println(a.toString());
//        boolean eee = a.delete("third");
//        System.out.println(a.toString());
//        boolean e = a.replace("first", new ru.job4j.store.User("new four"));
//        System.out.println(a.toString());
//
//        r.add(new ru.job4j.store.Role("first"));
//        r.add(new ru.job4j.store.Role("second"));
//        r.add(new ru.job4j.store.Role("third"));
//        r.add(new ru.job4j.store.Role("four"));
//        r.add(new ru.job4j.store.Role("five"));
//        r.add(new ru.job4j.store.Role("six"));
//        System.out.println(r.toString());
//        boolean edq = r.delete("third");
//        System.out.println(r.toString());
//        boolean fsde = r.replace("first", new ru.job4j.store.Role("new four"));
//        System.out.println(r.toString());
//        ru.job4j.store.User aaa = (ru.job4j.store.User) (a.findById("first"));
//        ru.job4j.store.Role dsa = (ru.job4j.store.Role) (r.findById("first"));
//    }

//    @Test
//    public void testListContaines(){
//        LinkedList node = new LinkedList();
//        LinkedList.Node nodee = node.first;
//        node.add("j ");
//        node.add(1);
//        node.add("h ");
//        node.add("k ");
//        node.add("v ");
//        node.add("e ");
//        node.add("n ");
//        node.add("c ");
//        node.add(", ");
//        System.out.println(node.toString());
//        System.out.println(node.get(3).toString());
//        System.out.println(node.iterator().hasNext());
//        System.out.println(node.iterator().next());
//        System.out.println(node.iterator().hasNext());
//        System.out.println(node.iterator().next());
//        System.out.println(node.iterator().hasNext());
//        System.out.println(node.iterator().next());
//        System.out.println(node.iterator().hasNext());
//        System.out.println(node.iterator().next());
//        System.out.println(node.iterator().hasNext());
//        System.out.println(node.iterator().next());
//        System.out.println(node.iterator().hasNext());
//        System.out.println(node.iterator().next());
//        System.out.println(node.iterator().hasNext());
//        System.out.println(node.iterator().next());
//
//    }
//
//    @Test
//    public  void QueuTest() {
//        SimpleQueu queu = new SimpleQueu();
//        queu.push(" aw");
//        queu.push("dwas");
//        queu.push("dwas");
//        queu.push("dwas");
//        queu.push("dwas");
//        queu.push("dwas");
//        queu.push("dwas");
//        queu.push("dwas");
//        System.out.println(queu.toString());
//
//        queu.poll();
//        System.out.println(queu.toString());
//
////        SimpleStack stack = new SimpleStack();
////        stack.push(" aw");
////        stack.push("dwas");
////        stack.push("dwas");
////        stack.push("dwas");
////        stack.push("dwas");
////        stack.push("dwas");
////        stack.push("dwas");
////        stack.push("dwas");
////        System.out.println(stack.toString());
////
////        stack.poll();
////        System.out.println(stack.toString());
////        stack.poll();
////        System.out.println(stack.toString());
////        stack.poll();
////        System.out.println(stack.toString());
////        stack.poll();
////        System.out.println(stack.toString());
////        stack.poll();
////        System.out.println(stack.toString());
////        stack.poll();
////        stack.poll();
////        System.out.println(stack.toString());
////        System.out.println(stack.toString());
////        stack.poll();
////        System.out.println(stack.toString());
////
//
//        SimpleQueu<Integer> queue = new SimpleQueu<>();
//        queue.push(1);
//        queue.push(2);
//        queue.push(3);
//        assertThat(queue.poll(), is(1));
//        assertThat(queue.poll(), is(2));
//        assertThat(queue.poll(), is(3));
//    }
//
//    @Test
//    public void f(){
//        SimpleSet set = new SimpleSet(5);
//        set.add("ghjk");
//        set.add("jkl");
//        set.add("ghjk");
//        set.add("ghjk");
//        set.add("ghjk");
//        set.add("ghjk");
//        set.add("ghjk");
//        set.add("ghjk");
//        if (set.hasNext())
//        System.out.println(set.next().toString());
//        if (set.hasNext())
//        System.out.println(set.next().toString());
//        if (set.hasNext())
//        System.out.println(set.next().toString());
//        if (set.hasNext())
//        System.out.println(set.next().toString());
//    }
//
    @Test
    public void simpleLinledSetTest(){
        SimpleLinkedSet set = new SimpleLinkedSet();
        set.add("dasfds");
        set.add("asd");
        set.add("asd");
        System.out.println(set.toString());
        Iterator it = set.iterator();
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.hasNext());
        System.out.println(it.next());
    }

//    @Test
//    public void simpleSet(){
//        Integer[] integ = new Integer[4];
//        HashSet set = new HashSet(integ);
//        set.add(-1);
//        set.add(1);
//        set.add(7);
//        set.add(0);
//        System.out.println(set.toString());
//        set.remove(7);
//        set.add(-1);
//        set.add(1);
//        set.add(7);
//        set.add(-6);
//        set.add(4);
//        set.add(2);
//        System.out.println(set.toString());
//        System.out.println(set.contains(7));
//        System.out.println(set.contains(10));
//        System.out.println(set.contains(-6));
//
//    }
//
//    @Test
//    public void dsad(){
//        int[] d = {1,2,3,4,5,2,2,1,1};
//        EvenNumbersIterator das = new EvenNumbersIterator(d);
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//    }
//
//    @Test
//    public void prime(){
//        int[] d = {1,2,3,4,5,2,2,1,1};
//        PrimeIterator das = new PrimeIterator(d);
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//        System.out.println(das.next());
//        System.out.println(das.hasNext());
//    }
//
//    private Iterator<Integer> it;
//
//    @Before
//    public void setUp(){
//        it = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 3571});
//    }
//
//    @Test(expected = NoSuchElementException.class)
//    public void shouldReturnPrimeNumbersOnly () {
//        assertThat(it.hasNext(), is(true));
//        assertThat(it.next(), is(2));
//        assertThat(it.hasNext(), is(true));
//        assertThat(it.next(), is(3));
//        assertThat(it.hasNext(), is(true));
//        assertThat(it.next(), is(5));
//        assertThat(it.hasNext(), is(true));
//        assertThat(it.next(), is(7));
//        assertThat(it.hasNext(), is(true));
//        assertThat(it.next(), is(3571));
//        assertThat(it.hasNext(), is(false));
//        it.next();
//    }
//
//    @Test
//    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder () {
//        assertThat(it.hasNext(), is(true));
//        assertThat(it.hasNext(), is(true));
//        assertThat(it.next(), is(2));
//        assertThat(it.next(), is(3));
//        assertThat(it.next(), is(5));
//        assertThat(it.next(), is(7));
//        assertThat(it.next(), is(3571));
//    }
//
//    @Test
//    public void shouldReturnFalseCauseThereIsNoAnyPrimeNumber (){
//        it = new PrimeIterator(new int[]{4,6});
//        assertThat("should return false, cause there is no any prime number",it.hasNext(), is(false));
//    }
//
//    @Test
//    public void dasd(){
//        ru.job4j.service.SimpleArray a = new ru.job4j.service.SimpleArray(10);
//
//        System.out.println(a.toString());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//        System.out.println(a.iterator().next());
//        System.out.println(a.iterator().hasNext());
//
//    }
}