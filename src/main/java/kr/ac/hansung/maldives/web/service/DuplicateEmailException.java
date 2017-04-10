package kr.ac.hansung.maldives.web.service;

public class DuplicateEmailException extends Exception {

	private static final long serialVersionUID = 6341916978542389498L;

	public DuplicateEmailException(String message) {
		super(message);
	}
}