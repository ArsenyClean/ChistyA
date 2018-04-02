package baltinfocom;

import java.util.*;

public class Groups {

    Map<Long, Integer> collumnOne;
    Map<Long, Integer> collumnTwo;
    Map<Long, Integer> collumnThree;
    Map<Integer, Group> groupStore;
    List<List<Long>> lists;
    List<ElementsOfSortedList> sortedList;
    Integer groupCounter = 0;

    public Groups(List<List<Long>> lists) {
        collumnOne = new HashMap<>();
        collumnTwo = new HashMap<>();
        collumnThree = new HashMap<>();
        groupStore = new HashMap<>();
        sortedList = new ArrayList<>();
        this.lists = lists;
    }

    public String makeGroups() {
        for (List<Long> list : this.lists) {
            this.checkThisLine(list);
        }
        return this.toString();
    }

    public boolean checkThisLine(List<Long> list) {
        boolean numberWasFound = false;
        Group group = null;
        Integer groupUnionId = null;
        if (collumnOne.containsKey(list.get(0))) {
            group = groupStore.get(collumnOne.get(list.get(0)));
            group.checkAndAddIfUnique(list);
            numberWasFound = true;
            groupUnionId = group.getGroupId();
        }
        if (collumnTwo.containsKey(list.get(1))) {
            if (groupUnionId != null) {
                Group group1 = groupStore.get(collumnTwo.get(list.get(1)));
                if (!groupUnionId.equals(group1.getGroupId())) {
                    group.getSetOfRows().putAll(group1.getSetOfRows());
                    updateOldGroupId(group.getGroupId(), group1);
                    groupStore.remove(group1.getGroupId());
                }
            } else {
                group = groupStore.get(collumnTwo.get(list.get(1)));
                group.checkAndAddIfUnique(list);
                numberWasFound = true;
                groupUnionId = group.getGroupId();
            }
        }
        if (collumnThree.containsKey(list.get(2))) {
            if (groupUnionId != null) {
                Group group1 = groupStore.get(collumnThree.get(list.get(2)));
                if (!groupUnionId.equals(group1.getGroupId())) {
                    group.getSetOfRows().putAll(group1.getSetOfRows());
                    updateOldGroupId(group.getGroupId(), group1);
                    groupStore.remove(group1.getGroupId());
                }
            } else {
                group = groupStore.get(collumnThree.get(list.get(2)));
                group.checkAndAddIfUnique(list);
                numberWasFound = true;
            }
        }
        if (group != null) {
            indexOf(list, group);
            numberWasFound = true;
        } else {
            group = new Group(groupCounter);
            group.checkAndAddIfUnique(list);
            groupStore.put(groupCounter++, group);
            indexOf(list, group);
        }
        return numberWasFound;
    }

    private void indexOf(List<Long> list, Group group) {
        if (list.get(0) != null) {
            collumnOne.put(list.get(0), group.groupId);
        }
        if (list.get(1) != null) {
            collumnTwo.put(list.get(1), group.groupId);
        }
        if (list.get(2) != null) {
            collumnThree.put(list.get(2), group.groupId);
        }
    }

    private void updateOldGroupId(Integer newGroupId, Group group) {
        Map<Group.InnerKey, Integer> setOfRows = group.getSetOfRows();
        for (Map.Entry entry : setOfRows.entrySet()) {
            Group.InnerKey innerKey = (Group.InnerKey) entry.getKey();
            collumnOne.put(innerKey.getNumberOne(), newGroupId);
            collumnTwo.put(innerKey.getNumberTwo(), newGroupId);
            collumnThree.put(innerKey.getNumberThree(), newGroupId);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {

            for (Map.Entry entry : groupStore.entrySet()) {
                Group group = (Group) entry.getValue();
                if (group.getRowCounter() > 1) {
                    ElementsOfSortedList el = new ElementsOfSortedList(group.getGroupId(),
                            group.getRowCounter(), group);
                    sortedList.add(el);
                }
            }
        } catch (OutOfMemoryError e) {
            return sb.toString();
        }
        Collections.sort(sortedList, new Comparator<ElementsOfSortedList>() {
            @Override
            public int compare(ElementsOfSortedList o2, ElementsOfSortedList o1) {
                return o1.getCounterGroup().compareTo(o2.getCounterGroup());
            }
        });
        sb.append("Count of Groups  = ").append(sortedList.size()).append("\n");
        for (ElementsOfSortedList el : sortedList) {
            sb.append("Group №").append(el.getId())
                    .append("\n").append(el.getGroup().toString()).append("\n");
        }
        return sb.toString();
    }
}