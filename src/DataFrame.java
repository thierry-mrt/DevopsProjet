public class DataFrame {
    private Object[][] data;
    private String[] columnNames;

    public Object[][] getData() {
        return data;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public DataFrame(Object[][] data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

}
