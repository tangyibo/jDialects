/*
 * jDialects, a tiny SQL dialect tool 
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package com.github.drinkjava2.jdialects.model;

import com.github.drinkjava2.jdialects.Type;

/**
 * The platform-independent Column model
 * 
 * </pre>
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class Column {
	private String columnName;
	private Type columnType;
	private Boolean pkey = false;
	private Boolean notNull = false;
	private Boolean unique = false;
	private String uniqueConstraintName;
	private String check;
	private Boolean identity = false;
	private String pkeyName;
	private String defaultValue;

	private String sequenceName;
	private Integer sequenceStart = 1;
	private Integer sequenceIncrement = 1;
	private Boolean identityOrSequence = false;

	/** comment of this column */
	private String comment;

	/** precision, scale, length all share use lengths array */
	private Integer[] lengths = new Integer[] {};

	public Column(String columnName) {
		this.columnName = columnName;
	}

	public Column notNull() {
		this.notNull = true;
		return this;
	}

	public Column unique() {
		this.unique = true;
		return this;
	}

	public Column check(String check) {
		this.check = check;
		return this;
	}

	public Column unique(String uniqueConstraintName) {
		this.unique = true;
		this.uniqueConstraintName = uniqueConstraintName;
		return this;
	}

	public Column identity() {
		this.identity = true;
		return this;
	}

	public Column defaultValue(String value) {
		this.defaultValue = value;
		return this;
	}

	public Column comment(String comment) {
		this.comment = comment;
		return this;
	}

	public Column pkey() {
		this.pkey = true;
		return this;
	}

	public Column pkey(String pkeyName) {
		this.pkey = true;
		this.pkeyName = pkeyName;
		return this;
	}

	/**
	 * Define sequence column. Important note: value need use
	 * dialect.getNextSequenceValue("sequenceName") to obtain
	 */
	public Column sequence(String sequenceName, Integer sequenceStart, Integer sequenceIncrement) {
		this.sequenceName = sequenceName;
		this.sequenceStart = sequenceStart;
		this.sequenceIncrement = sequenceIncrement;
		return this;
	}

	/**
	 * Define sequence column. Important note: value need use
	 * dialect.getNextSequenceValue("sequenceName") to obtain
	 */
	public Column sequence(String sequenceName) {
		this.sequenceName = sequenceName;
		this.sequenceStart = 1;
		this.sequenceIncrement = 1;
		return this;
	}

	/**
	 * Define sequence column. Important note: value need use
	 * dialect.getIdentityOrNextSequenceValue("sequenceName") to obtain, if
	 * return null mean it's a identity column, otherwise will get a sequence
	 * value
	 */
	public Column identityOrSequence(String sequenceName, Integer sequenceStart, Integer sequenceIncrement) {
		this.sequenceName = sequenceName;
		this.sequenceStart = sequenceStart;
		this.sequenceIncrement = sequenceIncrement;
		this.identityOrSequence = true;
		return this;
	}

	//@formatter:off shut off eclipse's formatter
	public Column LONG() {this.columnType=Type.BIGINT;return this;} 
	public Column BOOLEAN() {this.columnType=Type.BOOLEAN;return this;} 
	public Column DOUBLE() {this.columnType=Type.DOUBLE;return this;} 
	public Column FLOAT(Integer... lengths) {this.columnType=Type.FLOAT;this.lengths=lengths;return this;} 
	public Column INTEGER() {this.columnType=Type.INTEGER;return this;} 
	public Column SHORT() {this.columnType=Type.SMALLINT;return this;} 
	public Column BIGDECIMAL(Integer precision, Integer scale) {this.columnType=Type.NUMERIC; this.lengths= new Integer[]{precision,scale}; return this;} 
	public Column STRING(Integer length) {this.columnType=Type.VARCHAR;this.lengths=new Integer[]{length}; return this;} 
	public Column DATE() {this.columnType=Type.DATE;return this;} 
	public Column TIME() {this.columnType=Type.TIME;return this;} 
	public Column TIMESTAMP() {this.columnType=Type.TIMESTAMP;return this;} 
	public Column BIGINT() {this.columnType=Type.BIGINT;return this;} 
	public Column BINARY(Integer... lengths) {this.columnType=Type.BINARY;this.lengths=lengths; return this;} 
	public Column BIT() {this.columnType=Type.BIT;return this;} 
	public Column BLOB(Integer... lengths) {this.columnType=Type.BLOB;this.lengths=lengths;return this;} 
	public Column CHAR(Integer... lengths) {this.columnType=Type.CHAR;this.lengths=lengths;return this;} 
	public Column CLOB(Integer... lengths) {this.columnType=Type.CLOB;this.lengths=lengths;return this;} 
	public Column DECIMAL(Integer... lengths) {this.columnType=Type.DECIMAL;this.lengths=lengths;return this;} 
	public Column JAVA_OBJECT() {this.columnType=Type.JAVA_OBJECT;return this;} 
	public Column LONGNVARCHAR(Integer length) {this.columnType=Type.LONGNVARCHAR;this.lengths=new Integer[]{length};return this;} 
	public Column LONGVARBINARY(Integer... lengths) {this.columnType=Type.LONGVARBINARY;this.lengths=lengths;return this;} 
	public Column LONGVARCHAR(Integer... lengths) {this.columnType=Type.LONGVARCHAR;this.lengths=lengths;return this;} 
	public Column NCHAR(Integer length) {this.columnType=Type.NCHAR;this.lengths=new Integer[]{length};return this;} 
	public Column NCLOB() {this.columnType=Type.NCLOB;return this;} 
	public Column NUMERIC(Integer... lengths) {this.columnType=Type.NUMERIC;this.lengths=lengths;return this;} 
	public Column NVARCHAR(Integer length) {this.columnType=Type.NVARCHAR;return this;} 
	public Column OTHER(Integer... lengths) {this.columnType=Type.OTHER;this.lengths=lengths;return this;} 
	public Column REAL() {this.columnType=Type.REAL;return this;} 
	public Column SMALLINT() {this.columnType=Type.SMALLINT;return this;} 
	public Column TINYINT() {this.columnType=Type.TINYINT;return this;} 
	public Column VARBINARY(Integer... lengths) {this.columnType=Type.VARBINARY;this.lengths=lengths;return this;} 
	public Column VARCHAR(Integer length) {this.columnType=Type.VARCHAR;this.lengths=new Integer[]{length};return this;}


	//getter & setters============== 
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Type getColumnType() {
		return columnType;
	}

	public void setColumnType(Type columnType) {
		this.columnType = columnType;
	}

	public Boolean getPkey() {
		return pkey;
	}

	public void setPkey(Boolean pkey) {
		this.pkey = pkey;
	}

	public Boolean getNotNull() {
		return notNull;
	}

	public void setNotNull(Boolean notNull) {
		this.notNull = notNull;
	}

	public Boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	public String getUniqueConstraintName() {
		return uniqueConstraintName;
	}

	public void setUniqueConstraintName(String uniqueConstraintName) {
		this.uniqueConstraintName = uniqueConstraintName;
	}
  
	public Boolean getIdentity() {
		return identity;
	}

	public void setIdentity(Boolean identity) {
		this.identity = identity;
	}

	public String getPkeyName() {
		return pkeyName;
	}

	public void setPkeyName(String pkeyName) {
		this.pkeyName = pkeyName;
	}
 
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer[] getLengths() {
		return lengths;
	}

	public void setLengths(Integer[] lengths) {
		this.lengths = lengths;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public Integer getSequenceStart() {
		return sequenceStart;
	}

	public void setSequenceStart(Integer sequenceStart) {
		this.sequenceStart = sequenceStart;
	}

	public Integer getSequenceIncrement() {
		return sequenceIncrement;
	}

	public void setSequenceIncrement(Integer sequenceIncrement) {
		this.sequenceIncrement = sequenceIncrement;
	}

	public Boolean getIdentityOrSequence() {
		return identityOrSequence;
	}

	public void setIdentityOrSequence(Boolean identityOrSequence) {
		this.identityOrSequence = identityOrSequence;
	}
  
}