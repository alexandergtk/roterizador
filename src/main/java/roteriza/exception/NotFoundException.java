package roteriza.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends WebException {

	private static final long serialVersionUID = 2799179465862669781L;

	public NotFoundException(Class<?> clazz) {
		super(HttpStatus.NOT_FOUND,
				"exception." + clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1) + ".notFound");
	}

	public NotFoundException(String found) {
		super(HttpStatus.NOT_FOUND, "exception." + found + ".notFound");
	}

}