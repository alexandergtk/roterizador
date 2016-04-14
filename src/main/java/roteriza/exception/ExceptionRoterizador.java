package roteriza.exception;

import org.springframework.http.HttpStatus;

public class ExceptionRoterizador {

	public static final WebException LINHA_INVALID = new WebException(HttpStatus.PRECONDITION_FAILED,
			"exception.invalid.linha");

	private ExceptionRoterizador() {

	}
}