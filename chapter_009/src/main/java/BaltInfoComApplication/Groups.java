package BaltInfoComApplication;

import java.util.*;

public class Groups {

    Map<Long, Group> collumnOne;
    Map<Long, Group> collumnTwo;
    Map<Long, Group> collumnThree;
    Map<Integer, Group> groupStore;
    List<List<Long>> lists;
    List<Group> sortedList;
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
        Double i = new Double(0);
        for (List<Long> list : this.lists) {
            this.checkThisLine(list);
            if ((i % 100000) == 0) {
                System.out.println(String.format("%.2g", (i / lists.size()) * 100) + " % of lines has been grouped ");
            }
            i++;
        }
        unionCollumns();
        sortStoreOfGroups();
        return this.toString();
    }

    public boolean checkThisLine(List<Long> list) {
        boolean numberWasFound = false;
        Group group = null;
        Integer groupUnionId = null;
        if (collumnOne.containsKey(list.get(0))) {
            group = collumnOne.get(list.get(0));
            group.checkAndAddIfUnique(list);
            numberWasFound = true;
            groupUnionId = group.getGroupId();
        }
        if (collumnTwo.containsKey(list.get(1))) {
            if (groupUnionId != null) {
                Group group1 = collumnTwo.get(list.get(1));
                if (!groupUnionId.equals(group1.getGroupId())) {
                    group1.setRowCounter(group.getRowCounter() + group1.getRowCounter());
                    group1.setGroupId(group.getGroupId());
                    group.addGroupInInnerStire(group1);
                }
            } else {
                group = collumnTwo.get(list.get(1));
                group.checkAndAddIfUnique(list);
                numberWasFound = true;
                groupUnionId = group.getGroupId();
            }
        }
        if (collumnThree.containsKey(list.get(2))) {
            if (groupUnionId != null) {
                Group group1 = (collumnThree.get(list.get(2)));
                if (!groupUnionId.equals(group1.getGroupId())) {
                    group1.setRowCounter(group.getRowCounter() + group1.getRowCounter());
                    group1.setGroupId(group.getGroupId());
                    group.addGroupInInnerStire(group1);
                }
            } else {
                group = collumnThree.get(list.get(2));
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
            groupCounter++;
            indexOf(list, group);
        }
        return numberWasFound;
    }

    private void indexOf(List<Long> list, Group group) {
        if (list.get(0) != null) {
            collumnOne.put(list.get(0), group);
        }
        if (list.get(1) != null) {
            collumnTwo.put(list.get(1), group);
        }
        if (list.get(2) != null) {
            collumnThree.put(list.get(2), group);
        }
    }

    public List<List<Long>> deleteDublic() {
        Map<InnerKey, Integer> innerList = new HashMap<>();
        List<List<Long>> finalList = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < this.lists.size(); i++) {
            InnerKey innerKey = new InnerKey(this.lists.get(i).get(0),this.lists.get(i).get(1), this.lists.get(i).get(2));
            if (!innerList.containsKey(innerKey)) {
                counter++;
                innerList.put(innerKey, counter);
                List<Long> list = new ArrayList<>();
                list.add(this.lists.get(i).get(0));
                list.add(this.lists.get(i).get(1));
                list.add(this.lists.get(i).get(2));
                finalList.add(list);
            }
        }
        this.lists = finalList;
        return lists;
    }

    public void  unionCollumns() {
        int counter = 0;
        System.out.println("Analizing of the  1 collumn.. ");
        for (Map.Entry entry : collumnOne.entrySet()) {
            Group group = (Group) entry.getValue();
            if (group.getRowCounter() > 1) {
                if (!groupStore.containsKey(group.getGroupId())) {
                    groupStore.put(group.getGroupId(), group);
                    sortedList.add(group);
                    counter++;
                }
            }
        }
        System.out.println("Analizing of the  2 collumn... \nCount of founded groups is= " + counter);
        for (Map.Entry entry : collumnTwo.entrySet()) {
            Group group = (Group) entry.getValue();
            if (group.getRowCounter() > 1) {
                if (!groupStore.containsKey(group.getGroupId())) {
                    groupStore.put(group.getGroupId(), group);
                    sortedList.add(group);
                    counter++;
                }
            }
        }
        System.out.println("Analizing of the  3 collumn... \nCount of founded groups is= " + counter);
        for (Map.Entry entry : collumnThree.entrySet()) {
            Group group = (Group) entry.getValue();
            if (group.getRowCounter() > 1) {
                if (!groupStore.containsKey(group.getGroupId())) {
                    groupStore.put(group.getGroupId(), group);
                    sortedList.add(group);
                    counter++;
                }
            }
        }
        System.out.println("End of analizing of collumns... \nFinal count of founded groups is= " + counter);
    }

    public void sortStoreOfGroups() {
        Collections.sort(sortedList, new Comparator<Group>() {
            @Override
            public int compare(Group o2, Group o1) {
                return o1.getRowCounter().compareTo(o2.getRowCounter());
            }
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCount of groups = ").append(sortedList.size()).append("\n");
        for (Group el : sortedList) {
            sb.append("Group №").append(el.getGroupId())
                    .append("\nCounter of rows = ").append(el.getRowCounter())
                    .append("\n").append(el.toString());
            for (Group group : el.getInnerGroupStore()) {
                sb.append(group.toString());
            }
        }
        return sb.toString();
    }
}