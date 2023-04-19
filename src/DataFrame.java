public class DataFrame {

    private Object[][] data;
    private Integer[] index;
    private String[] columnNames;

    public Object[][] getData() {
        return data;
    }
    public Integer[] getIndex() {
        return index;
    }

    public String[] getColumnNames() {
        return columnNames;
    }


    public DataFrame(Object[][] data, Integer[] index, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
        this.index = index;
    }

}
