package file_creator.user;

public class Name extends User {
    private String first;
    private String last;
    private String patronymic;

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
}
