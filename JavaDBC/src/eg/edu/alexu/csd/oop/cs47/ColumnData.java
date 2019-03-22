package eg.edu.alexu.csd.oop.cs47;

public class ColumnData {
            private String tableName ;
            private String colunmName ;
            private String columnType ;
            
            public ColumnData(String tableName,String columnName , String columnType) {
            	 this.tableName = tableName ;
            	 this.columnType= columnType ;
            	 this.colunmName=columnName ;
            }

			public String getTableName() {
				return tableName;
			}

			public void setTableName(String tableName) {
				this.tableName = tableName;
			}

			public String getColunmName() {
				return colunmName;
			}

			public void setColunmName(String colunmName) {
				this.colunmName = colunmName;
			}

			public String getColumnType() {
				return columnType;
			}

			public void setColumnType(String columnType) {
				this.columnType = columnType;
			}
}
