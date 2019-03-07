package files_creator;

public class Name {
    private String first;
    private String last;

    public Name(String first, String last, String patronymic) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
