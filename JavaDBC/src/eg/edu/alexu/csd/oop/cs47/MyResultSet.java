/**
 * 
 * author Aboeleneen 
 */
package eg.edu.alexu.csd.oop.cs47;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import eg.edu.alexu.csd.oop.metaData.MyMetaData;

public class MyResultSet implements ResultSet {
    
	/**
	 * 
	 * first we know that we use it with select query to store sets of columns
	 * there is a pointer refer to the rows we can move towards or forwards using some functions we will implement 
	 * then we need to deal with current row with columnName or columnIndex and get their values 
	    ▪ absolute(integer row) pointer refer to this row if the index is true
		▪ afterLast() pointer = content.lenght 
		▪ beforeFirst() pointer = -1 
 		▪ close() MyResultSet = null 
		▪ findColumn(String columnLabel) return the index of column name
		▪ first() pointer = 0 ;
		▪ getInt(integer columnIndex) return object[pointer][columnIndex-1]
		▪ getInt(String columnLabel)  return object[pointer][findColumn(columnLabel)]
		▪ getMetaData() 
		▪ getObject(integer columnIndex) return object[pointer][columnIndex-1]
		▪ getStatement() return this.statement
		▪ getString(integer columnIndex) return object[pointer][columnIndex-1]
		▪ getString(String columnLabel) return object[pointer][columnIndex-1]
		▪ isAfterLast() return pointer == content.lenght-1 
		▪ isBeforeFirst() return pointer == -1
		▪ isClosed() return statement==null
		▪ isFirst() return pointer == 0
		▪ isLast() return pointer == content.lenght-1
		▪ last() pointer--;
		▪ next() pointer++;
		▪ previous() pointer--;
	 * first we declare pointer variable = -1 
	 * second  in the constructor we take array2D of object which contains content of selected columns
	 * in get methods it is so easy if it give me column index but if it give columnName i need to know it's index first 
	 * by findColumn method which is loop on columnNames array which i will take it in constructor
	 * 
	 */
	private int pointer ;
	private Object[][] content ;
	private String[] columnNames ;
	private Statement statement ;
	private String[] types ;
	private MyMetaData resultSetMetaData ;
	private String tableName ;
	
	public MyResultSet(Object[][] content ,String[] columnNames ,Statement statement,String tableName) {
		this.pointer=-1;
		this.content=content ;
		this.columnNames = columnNames ;
		this.statement=statement ;
		this.resultSetMetaData = null;
		this.tableName = tableName ;
		types = new String[columnNames.length];
		System.out.println(content.length);
		this.setTypes();
	}
	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
    
	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
   /**
    * 
    * @param integer represent row index
    * @return true if the cursor moved to the given position 
    * @return false if it before the first row or after the last row 
    * 
    */
	@Override
	public boolean absolute(int row) throws SQLException {
		// TODO Auto-generated method stub
		if( row < 0 && row > columnNames.length-1) {
			pointer = row-1 ;
			return true ;
		}
		return false;
	}
   /**
    * move the cursor after the last row 
    */
	@Override
	public void afterLast() throws SQLException {
		// TODO Auto-generated method stub
		pointer = content.length;
	}
    /**
     * move the cursor before the first row 
     */
	@Override
	public void beforeFirst() throws SQLException {
		// TODO Auto-generated method stub
		 pointer = -1 ;
	}

	@Override
	public void cancelRowUpdates() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		statement = null ;
	}

	@Override
	public void deleteRow() throws SQLException {
		// TODO Auto-generated method stub
		
	}
    /**
     * if there are multiple columns with the same name it returns the first case-sensitive match
     * if there is no case-sensitive match it return the first case-insensitive match
     * @param column name 
     * @return the index of column for the given column name 
     */
	@Override
	public int findColumn(String column) throws SQLException {
	
		// TODO Auto-generated method stub
		for(int i=0;i<columnNames.length;i++) {
			if(columnNames[i].equals(column)) {
				return i+1 ;
			}
		}
		
		for(int i=0;i<columnNames.length;i++) {
			if(columnNames[i].equalsIgnoreCase(column)) {
				return i+1 ;
			}
		}
		
		return -2 ;
		
	}
	
	/**
	 *  move the cursor to the first row 
	 *  @return true if  the cursor moved to the first row 
	 *  @return false if it is empty
	 */

	@Override
	public boolean first() throws SQLException {
		// TODO Auto-generated method stub
		if(content.length>=1) {
			pointer =  0 ;
			return true ;
		}
		return false;
	}

	@Override
	public Array getArray(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Array getArray(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getAsciiStream(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getAsciiStream(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(int arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getBigDecimal(String arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getBinaryStream(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getBinaryStream(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob getBlob(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blob getBlob(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getBoolean(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBoolean(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte getByte(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte getByte(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getBytes(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getCharacterStream(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getCharacterStream(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clob getClob(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clob getClob(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getConcurrency() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCursorName() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(int arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(String arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDouble(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDouble(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFetchDirection() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFetchSize() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getFloat(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getFloat(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
    /**
     * get integer value for this column in current row
     * 
     * @param index of the column
     * @return integer value of this column
     */
	@Override
	public int getInt(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		if(types[columnIndex-1].equals("int") && isValidIndex(columnIndex-1)) {
		return (int)content[pointer][columnIndex-1];
		}
		else {
			throw new  SQLException();
		}
	}
    
	 /**
     * get integer value for this column in current row
     * 
     * @param name of the column
     * @return integer value of this column
     */
	@Override
	public int getInt(String columnName) throws SQLException {
		// TODO Auto-generated method stub
		if(types[this.findColumn(columnName)-1].equals("int") && isValidIndex(this.findColumn(columnName)-1)) {
			return (int)content[pointer][this.findColumn(columnName)-1];
			}
			else {
				throw new  SQLException();
			}
		
	}

	@Override
	public long getLong(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLong(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MyMetaData getMetaData() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<ColumnData> data = new ArrayList<>();
		
		for(int i=0;i<columnNames.length;i++) {
			data.add(new ColumnData(this.tableName,columnNames[i],types[i]));
			System.out.println(columnNames[i]);
		}
		System.out.println("select" + data.get(0).getColunmName());
		MyMetaData m = new MyMetaData(data);
		return m;
	}

	@Override
	public Reader getNCharacterStream(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getNCharacterStream(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NClob getNClob(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NClob getNClob(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNString(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNString(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	 /**
     * get value for this column in current row
     * 
     * @param   index of the column
     * @return  value of this column as object
     */
	@Override
	public Object getObject(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		if(isValidIndex(columnIndex-1))
		return content[pointer][columnIndex-1];
		else {
			throw new SQLException();
		}
	}
	/**
     * get value for this column in current row
     * 
     * @param   name of the column
     * @return  value of this column as object
     */
	@Override
	public Object getObject(String columnName) throws SQLException {
		// TODO Auto-generated method stub
		if(isValidIndex(this.findColumn(columnName)-1))
		return content[pointer][this.findColumn(columnName)-1];
		else {
			throw new SQLException();
		}
	}

	@Override
	public Object getObject(int arg0, Map<String, Class<?>> arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getObject(String arg0, Map<String, Class<?>> arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getObject(int arg0, Class<T> arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getObject(String arg0, Class<T> arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ref getRef(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ref getRef(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRow() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RowId getRowId(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RowId getRowId(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML getSQLXML(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML getSQLXML(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short getShort(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short getShort(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Statement getStatement() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
     * get value for this column in current row
     * 
     * @param   index of the column
     * @return  value of this column as String
     */
	@Override
	public String getString(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		if(types[columnIndex-1].equals("string") && isValidIndex(columnIndex-1))
		return (String)content[pointer][columnIndex-1];
		else {
			throw new SQLException();
		}
	}
	/**
     * get value for this column in current row
     * 
     * @param   name of the column
     * @return  value of this column as String
     */
	@Override
	public String getString(String columnName) throws SQLException {
		// TODO Auto-generated method stub
		if(types[this.findColumn(columnName)-1].equals("string") && isValidIndex(this.findColumn(columnName)-1))
		return (String)content[pointer][this.findColumn(columnName)-1]; 
		else {
			throw new SQLException();
		}
	}

	@Override
	public Time getTime(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(int arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getTime(String arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(int arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getTimestamp(String arg0, Calendar arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getType() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public URL getURL(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getURL(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getUnicodeStream(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getUnicodeStream(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertRow() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return true if the cursor is after the last row 
	 * @return false otherwise
	 */

	@Override
	public boolean isAfterLast() throws SQLException {
		// TODO Auto-generated method stub
		if(pointer == content.length && content.length > 0) {
			return true ;
		}
		return false;
	}
	/**
	 * @return true if the cursor is before the first row 
	 * @return false otherwise
	 */
	@Override
	public boolean isBeforeFirst() throws SQLException {
		// TODO Auto-generated method stub
		if(pointer == -1 && content.length > 0) {
			return true ;
		}
		return false;
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		if(statement == null) {
			return true ;
		}
		return false;
	}
	/**
	 * @return true if the cursor is at the first row 
	 * @return false otherwise
	 */
	@Override
	public boolean isFirst() throws SQLException {
		// TODO Auto-generated method stub
		if(pointer == 0 && content.length > 0) {
			return true ;
		}
		return false;
	}
	/**
	 * @return true if the cursor is at the last row 
	 * @return false otherwise
	 */
	@Override
	public boolean isLast() throws SQLException {
		// TODO Auto-generated method stub
		if(pointer == content.length-1 && content.length > 0) {
			return true ;
		}
		return false;
	}
    /**
     * move the cursor to the last row 
     * 
     * @return true if the new current row is valid 
     * @return false if it is empty
     */
	@Override
	public boolean last() throws SQLException {
		// TODO Auto-generated method stub
		if(content.length > 0) {
			pointer = content.length-1;
			return true ;
		}
		return false;
	}

	@Override
	public void moveToCurrentRow() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveToInsertRow() throws SQLException {
		// TODO Auto-generated method stub
		
	}
   /**
    * move the cursor down one row from its current position
    * 
    * @return true if the new current row is valid
    * @return false if it is empty
    */
	@Override
	public boolean next() throws SQLException {
		// TODO Auto-generated method stub
		if(pointer +1 < content.length) {
			pointer++;
			return true ;
		}
		return false;
	}
	 /**
	    * move the cursor up one row from its current position
	    * 
	    * @return true if the new current row is valid
	    * @return false if it is empty
	    */
	@Override
	public boolean previous() throws SQLException {
		// TODO Auto-generated method stub
		if(pointer -1 >= -1) {
			pointer--;
			return true ;
		}
		return false;
	}

	@Override
	public void refreshRow() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean relative(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rowDeleted() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rowInserted() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rowUpdated() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFetchDirection(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFetchSize(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateArray(int arg0, Array arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateArray(String arg0, Array arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBigDecimal(String arg0, BigDecimal arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBlob(int arg0, Blob arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBlob(String arg0, Blob arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBlob(int arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBlob(String arg0, InputStream arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBlob(int arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBlob(String arg0, InputStream arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoolean(int arg0, boolean arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoolean(String arg0, boolean arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByte(int arg0, byte arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByte(String arg0, byte arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBytes(int arg0, byte[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBytes(String arg0, byte[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClob(int arg0, Clob arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClob(String arg0, Clob arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClob(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClob(String arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClob(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClob(String arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDate(int arg0, Date arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDate(String arg0, Date arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDouble(int arg0, double arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDouble(String arg0, double arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFloat(int arg0, float arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFloat(String arg0, float arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInt(int arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInt(String arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLong(int arg0, long arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLong(String arg0, long arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNCharacterStream(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNCharacterStream(String arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNCharacterStream(String arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNClob(int arg0, NClob arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNClob(String arg0, NClob arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNClob(int arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNClob(String arg0, Reader arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNClob(int arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNClob(String arg0, Reader arg1, long arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNString(int arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNString(String arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNull(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNull(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateObject(int arg0, Object arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateObject(String arg0, Object arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateObject(int arg0, Object arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateObject(String arg0, Object arg1, int arg2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRef(int arg0, Ref arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRef(String arg0, Ref arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRow() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRowId(int arg0, RowId arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRowId(String arg0, RowId arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSQLXML(int arg0, SQLXML arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSQLXML(String arg0, SQLXML arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateShort(int arg0, short arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateShort(String arg0, short arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateString(int arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateString(String arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTime(int arg0, Time arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTime(String arg0, Time arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTimestamp(int arg0, Timestamp arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTimestamp(String arg0, Timestamp arg1) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasNull() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setTypes() {
		if(content.length == 0) return ;
		for(int i=0;i<content[0].length ;i++) {
			if(isInteger(content[0][i].toString())) {
				types[i]="int";
			}
			else {
				types[i]="string";
			}
		}
	}
	public Boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	public boolean isValidIndex(int columnIndex) {
		if(columnIndex >= 0 && columnIndex < columnNames.length) {
			return true ;
		}
		else {
			return false ;
		}
	}


}
