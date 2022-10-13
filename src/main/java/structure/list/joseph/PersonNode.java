package structure.list.joseph;

public class PersonNode {
    int number;
    PersonNode next;

    PersonNode(int number) {
        this.number = number;
        this.next = null;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PersonNode{");
        sb.append("number=").append(number);
        sb.append(", next=").append(next == null ? null : next.number);
        sb.append('}');
        return sb.toString();
    }
}
