package baltinfocom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {

    public class InnerKey {
        Long numberOne;
        Long numberTwo;
        Long numberThree;

        public InnerKey(Long numberOne, Long numberTwo, Long numberThree) {
            this.numberOne = numberOne;
            this.numberTwo = numberTwo;
            this.numberThree = numberThree;
        }

        public Long getNumberOne() {
            return numberOne;
        }

        public Long getNumberTwo() {
            return numberTwo;
        }

        public Long getNumberThree() {
            return numberThree;
        }

        @Override
        public int hashCode() {
            int hash = numberOne != null ? numberOne.hashCode() : 0;
            hash = hash * 31 + (numberTwo != null ? numberTwo.hashCode() : 0);
            hash = hash * 31 + (numberThree != null ? numberThree.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            InnerKey innerKey = (InnerKey) o;

            if (numberOne != null ? !numberOne.equals(innerKey.numberOne) : innerKey.numberOne != null) {
                return false;
            }
            if (numberTwo != null ? !numberTwo.equals(innerKey.numberTwo) : innerKey.numberTwo != null) {
                return false;
            }
            return numberThree != null ? numberThree.equals(innerKey.numberThree) : innerKey.numberThree == null;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(numberOne).append(", ")
                    .append(numberTwo).append(", ")
                    .append(numberThree).append(", ");
            return sb.toString();
        }
    }

    Map<InnerKey, Integer> setOfRows;

    Integer rowCounter = 0;
    Integer groupId;

    public Map<InnerKey, Integer> getSetOfRows() {
        return setOfRows;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Integer getRowCounter() {
        return rowCounter;
    }

    public Group(Integer groupId) {
        setOfRows = new HashMap<>();
        this.groupId = groupId;
    }

    public boolean checkAndAddIfUnique(List<Long> list) {
        InnerKey innerKey = new InnerKey(list.get(0), list.get(1), list.get(2));
        Boolean thisListExistInThisGroup = false;
        if (!setOfRows.containsKey(innerKey)) {
            setOfRows.put(innerKey, rowCounter++);
            thisListExistInThisGroup = true;
        }
        return thisListExistInThisGroup;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : setOfRows.entrySet()) {
            sb.append("Row №").append(entry.getValue())
                    .append(", ").append(entry.getKey().toString()).append("\n");
        }
        return sb.toString();
    }
}