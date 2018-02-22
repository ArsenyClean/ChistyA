package ru.job4j.service;

import java.util.Iterator;

public class SimpleSet implements Iterator{
    Object[] container;
    int index = 0;
    int iteratorIndex = 0;

    public SimpleSet (Object[] container) {
        this.container = container;
    }

    public Object[] IncreaseContainer (Object[] container){
        int index = 0;
        Object[] copyContainer = new Object[container.length*2];
        while (container.length > index) {
            copyContainer[index] = container[index];
            index++;
        }
        return copyContainer;
    }

    public void add(Object value) {
        int i = 0;
        while (i < index ){
            if ( container[i] == value)
                return;
            i++;
        }
        if (this.container.length  > index) {
            this.container[index] = value;
            index++;
        } else {
            this.container = IncreaseContainer(this.container);
            this.container[index]   = value;
            index++;
        }
    }

    @Override
    public boolean hasNext() {
        if ( container.length > iteratorIndex)
            return true;
        return false;
    }

    @Override
    public Object next() {
        if ( container.length > iteratorIndex) {
            iteratorIndex++;
            return container[iteratorIndex - 1];
        }
        else
            return null;
    }
}
