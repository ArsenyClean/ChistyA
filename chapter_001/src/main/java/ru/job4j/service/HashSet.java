package ru.job4j.service;

public class HashSet<E> {

    Object[] users;
    int hashConst = 16;
    int tableFull = 0;

    public HashSet(Object[] users){
        this.users = users;
    }

    boolean add(E e) {
        int index = hashMake(e);
        while (true){
            if (index < users.length){
                while (users[index] == null){
                    users[index] = e;
                    tableFull++;
                    double K = ((double)tableFull)/(double) users.length;
                    if ( K > 0.75 ){
                        users = growTable();
                        break;
                    }
                    return true;
                }
                index++;
            }
            else
                return false;
        }
    }

    Object[] growTable(){
        Object[] usersCop = new Object[users.length*2];
        for ( int i = 0; i < users.length; i++ ){
            usersCop[i] = users[i];
        }
        return usersCop;
    }

    boolean contains(E e) {
        int index = hashMake(e);
        while (true){
            if (index < users.length){
                while (users[index] != null){
                    if (users[index] == e) {
                        index++;
                        return true;
                    }
                    index++;
                }
                index++;
            }
            else
                return false;
        }
    }

    boolean remove(E e) {
        int index = hashMake(e);
        int flagEnd = index;
        boolean endFound = false;
        while (true){
            if (index < users.length){
                while (users[index] != null){
                    if (users[index] == e) {
                        flagEnd++;
                        endFound = true;
                    }
                    index++;
                    if (index < users.length)
                        if (users[index] != e){
                            flagEnd--;
                            break;
                        }
                    if (index >= users.length) {
                        flagEnd--;
                        break;
                    }
                }
                if (endFound) {
                    users[flagEnd] = null;
                    return true;
                }
                index++;
            }
            else
                return false;
        }
    }

    int hashMake(E value){
        return (((Integer) value) * hashConst) % users.length;
    }

}

