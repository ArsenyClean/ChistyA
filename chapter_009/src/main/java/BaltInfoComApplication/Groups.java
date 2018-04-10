package BaltInfoComApplication;

import java.util.*;

public class Groups {

    Map<String, Group> collumnOne;
    Map<String, Group> collumnTwo;
    Map<String, Group> collumnThree;
    Map<Integer, Group> groupStore;
    List<List<String>> lists;
    List<Group> sortedList;
    Integer groupCounter = 0;

    public Groups(List<List<String>> lists) {
        collumnOne = new HashMap<>();
        collumnTwo = new HashMap<>();
        collumnThree = new HashMap<>();
        groupStore = new HashMap<>();
        sortedList = new ArrayList<>();
        this.lists = lists;
    }

    public String makeGroups() {
        Double i = new Double(0);
        for (List<String> list : this.lists) {
            this.checkThisLine(list);
            if ((i % 100000) == 0) {
                System.out.println(String.format("%.2g", (i / lists.size()) * 100) + " % of lines has been grouped ");
            }
            i++;
        }
        takeGroupsFromCollumns();
        sortStoreOfGroups();
        return this.toString();
    }

    public boolean checkThisLine(List<String> list) {
        boolean numberWasFound = false;
        Group group = null;
        Integer groupUnionId = null;
        if (collumnOne.containsKey(list.get(0))) {
            group = collumnOne.get(list.get(0));
            group.add(list);
            numberWasFound = true;
            groupUnionId = group.getGroupId();
        }
        if (collumnTwo.containsKey(list.get(1))) {
            if (groupUnionId != null) {
                Group group1 = collumnTwo.get(list.get(1));
                if (!groupUnionId.equals(group1.getGroupId())) {
//                    group.setRowCounter(group.getRowCounter() + group1.getRowCounter());
                    Map<InnerKey, Integer> rows = group.gerSetOfRows();
                    group.addRows(group1.gerSetOfRows());
                    group1.addRows(rows);
                    group1.setGroupId(group.getGroupId());
                }
            } else {
                group = collumnTwo.get(list.get(1));
                group.add(list);
                numberWasFound = true;
                groupUnionId = group.getGroupId();
            }
        }
        if (collumnThree.containsKey(list.get(2))) {
            if (groupUnionId != null) {
                Group group1 = (collumnThree.get(list.get(2)));
                if (!groupUnionId.equals(group1.getGroupId())) {
//                    group.setRowCounter(group.getRowCounter() + group1.getRowCounter());
                    Map<InnerKey, Integer> rows = group.gerSetOfRows();
                    group.addRows(group1.gerSetOfRows());
                    group1.addRows(rows);
                    group1.setGroupId(group.getGroupId());
                }
            } else {
                group = collumnThree.get(list.get(2));
                group.add(list);
                numberWasFound = true;
            }
        }
        if (group != null) {
            putToCollumns(list, group);
//            group.add(list);
            numberWasFound = true;
        } else {
            group = new Group(groupCounter++);
            group.add(list);
            putToCollumns(list, group);
        }
        return numberWasFound;
    }

    private void putToCollumns(List<String> list, Group group) {
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

    public List<List<String>> deleteDublic() {
        Map<InnerKey, Integer> innerList = new HashMap<>();
        List<List<String>> finalList = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < this.lists.size(); i++) {
            InnerKey innerKey = new InnerKey(this.lists.get(i).get(0),this.lists.get(i).get(1), this.lists.get(i).get(2));
            if (!innerList.containsKey(innerKey)) {
                counter++;
                innerList.put(innerKey, counter);
                List<String> list = new ArrayList<>();
                list.add(this.lists.get(i).get(0));
                list.add(this.lists.get(i).get(1));
                list.add(this.lists.get(i).get(2));
                finalList.add(list);
            }
        }
        this.lists = finalList;
        return lists;
    }

    public void takeGroupsFromCollumns() {
        int counter = 0;
        System.out.println("Analizing of the  1 collumn.. ");
        for (Map.Entry entry : collumnOne.entrySet()) {
            Group group = (Group) entry.getValue();
            if (group.getRowCounter() > 1) {
                if (!groupStore.containsKey(group.getGroupId())) {
                    group.setGroupId(counter);
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
                    group.setGroupId(counter);
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
                    group.setGroupId(counter);
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
        int innerCounterOfGroups = 0;
        int counterRows = 0;
        sb.append("\nCount of groups = ").append(sortedList.size()).append("\n");
        for (Group el : sortedList) {
            int counterOfRows = 0;
            el.setGroupId(innerCounterOfGroups++);
            sb.append("Group №").append(el.getGroupId())
                    .append("\nCounter of rows = ").append(el.getRowCounter())
                    .append("\n");
            for (Map.Entry entry : el.gerSetOfRows().entrySet()) {
                sb.append("Row: ").append(counterOfRows++).append(" ").append(entry.getKey().toString() + " " + counterRows).append("\n");
//            for (Group group : el.getInnerGroupStore()) {
//                sb.append(group.toString());
//            }
                counterRows++;
            }
        }
        sb.append("\nCounter of rows =  " + counterRows);
        return sb.toString();
    }
}