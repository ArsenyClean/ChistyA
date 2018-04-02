package baltinfocom;

public class ElementsOfSortedList {
    Integer id;
    Group group;
    Integer counterGroup;

    public ElementsOfSortedList(Integer id, Integer counterGroup, Group group) {
        this.id = id;
        this.counterGroup = counterGroup;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }

    public Integer getCounterGroup() {
        return counterGroup;
    }
}