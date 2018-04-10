package BaltInfoComApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {

    Map<InnerKey, Integer> setOfRows;
    List<Group> innerGroupStore;
    Integer rowCounter = 0;
    Integer groupId;

    public void addRows(Map<InnerKey, Integer> list) {
        this.rowCounter += list.size();
        this.setOfRows.putAll(list);
    }

    public Map<InnerKey, Integer> gerSetOfRows() {
        return this.setOfRows;
    }

//    public void addGroupInInnerStore(Group group) {
//        this.innerGroupStore.add(group);
//    }

    public void setRowCounter(Integer rowCounter) {
        this.rowCounter = rowCounter;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Integer getRowCounter() {
        return rowCounter;
    }

    public Group(Integer groupId) {
        setOfRows = new HashMap<>();
        innerGroupStore = new ArrayList<>();
        this.groupId = groupId;
    }

    public void add(List<String> list) {
        InnerKey innerKey = new InnerKey(list.get(0), list.get(1), list.get(2));
        setOfRows.put(innerKey, rowCounter++);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : setOfRows.entrySet()) {
            sb.append("Row № ").append(entry.getValue())
                    .append(", ").append(entry.getKey().toString()).append("\n");
        }
        for (Group group : innerGroupStore) {
            sb.append(group.toString());
        }
        return sb.toString();
    }
}