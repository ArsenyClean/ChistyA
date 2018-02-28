package ru.job4j.service;

public class Helper<T extends Base>{

    public int searcher(SimpleArray<T> store, String id){
        int i = 0;
        while (i < store.index){
            if (store.objects[i].getId().equals(id)){
                i++;
                return i - 1;
            }
            i++;
        }
        return -1;
    }

    public boolean shifter(SimpleArray<T> store, int index){
        boolean doneFlag = false;
        int i = index;
        while (i < store.index - 1){
            store.objects[i] = store.objects[i + 1];
            doneFlag = true;
            i++;
        }
        store.objects[i + 1] = null;
        return doneFlag;
    }

    public String toString(SimpleArray<T> store) {
        String result = null;
        for (int i = 0; i < store.index; i++){
            result += "[" + i + "]=" + store.objects[i].getId() + " ";
        }
        return result;
    }
}
