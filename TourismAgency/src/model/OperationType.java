package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OperationType {

	private StringProperty operationTypeCode;
	private StringProperty operationType;
	
	public OperationType() {
		this(null, null);
	}
	
	public OperationType(String operationTypeCode, String operationType) {
		this.operationTypeCode = new SimpleStringProperty(operationTypeCode);
		this.operationType = new SimpleStringProperty(operationType);
	}

	public String getOperationTypeCode() {
		return operationTypeCode.get();
	}

	public void setOperationTypeCode(String operationTypeCode) {
		this.operationTypeCode.set(operationTypeCode);;
	}
	
	public StringProperty operationTypeCodeProperty() {
		return operationTypeCode;
	}

	public String getOperationType() {
		return operationType.get();
	}

	public void setOperationType(String operationType) {
		this.operationType.set(operationType);
	}
	
	public StringProperty operationTypeProperty() {
		return operationType;
	}
	
}
