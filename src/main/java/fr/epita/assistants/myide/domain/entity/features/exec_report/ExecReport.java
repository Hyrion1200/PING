package fr.epita.assistants.myide.domain.entity.features.exec_report;

import fr.epita.assistants.myide.domain.entity.Feature;

public class ExecReport implements Feature.ExecutionReport {
	// - Execution enum
	public enum Status {
		SUCCESS,
		ERROR
	}

	// - Private attributes
	private Status status;
	private String message;

	// - Constructor
	public ExecReport(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	// - Getters
	public Status getStatus() { return status; }
	public String getMessage() { return message; }

	// - Method
	public boolean isSuccess() {
		return getStatus() == Status.SUCCESS;
	}
}
