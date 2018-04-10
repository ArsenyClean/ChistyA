package BaltInfoComApplication;

public class InnerKey {
    String numberOne;
    String numberTwo;
    String numberThree;

    public InnerKey(String numberOne, String numberTwo, String numberThree) {
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
        this.numberThree = numberThree;
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
                .append(numberThree);
        return sb.toString();
    }
}