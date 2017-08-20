
package com.choranet.exception;

import java.io.Serializable;
/**
 *
 * @author rabbah
 */
public class DBInformation implements Serializable {
	// some SQL state standard constants
	public static final String SQL_STATUS_CLASS_CONNECTION_ERROR = "08";
	public static final String SQL_STATUS_CLASS_CARDINALITY_VIOLATION = "21";
	public static final String SQL_STATUS_CLASS_DATA_EXCEPTION = "22";
	public static final String SQL_STATUS_CLASS_INTEGRITY_CONSTRAINT_VIOLATION = "23";
	public static final String SQL_STATUS_CLASS_SYNTAX_ERROR = "42";
	
	// fields
	public String dbMessage;
	public String sqlString;
	public String sqlStatus;
	public String sqlStatusClass;
	public int sqlVendorCode;
	public String failedConstraint;
}

