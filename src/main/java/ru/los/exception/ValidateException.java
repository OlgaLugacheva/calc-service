package ru.los.exception;

import lombok.Getter;
import lombok.Setter;

public class ValidateException extends  Exception {
	@Getter
	@Setter
	private int typeId;

	public ValidateException() { super(); }
	public ValidateException(String message) { super(message); }
	public ValidateException(String message, Throwable cause) { super(message, cause); }
	public ValidateException(Throwable cause) { super(cause); }
	public ValidateException(String message, int typeId)
	{
		super(message);
		this.typeId = typeId;
	}

}
