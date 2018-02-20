package ru.job4j.service;

public class DynamicContainer <E> implements SimpleContainer {

    Object[] container;
    int index = 0;

    public DynamicContainer (Object[] container) {
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

    @Override
    public void add(Object value) {
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
    public Object get(int index) {
        return this.container[index];
    }
}
