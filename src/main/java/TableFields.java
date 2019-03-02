public enum TableFields {
    NAME("ИМЯ"),
    SURNAME("ФАМИЛИЯ"),
    PATRONYMIC("ОТЧЕСТВО"),
    AGE("ВОЗРАСТ"),
    SEX("ПОЛ"),
    DATE_OF_BIRTH("ДАТА РОЖДЕНИЯ"),
    INN("ИНН"),
    POSTCODE("ПОЧТОВЫЙ ИНДЕКС"),
    COUNTRY("СТРАНА"),
    REGION("ОБЛАСТЬ"),
    CITY("ГОРОД"),
    STREET("УЛИЦА"),
    BUILDING("ДОМ"),
    APARTMENT("КВАРТИРА");

    private String field;

    TableFields(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
