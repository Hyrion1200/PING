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
	private Object content;

	// - Constructor
	public ExecReport(Status status, Object content, String message)
	{
		this.status = status;
		this.content = content;
		this.message = message;
	}

	public ExecReport(Status status, Object content)
	{
		this.status = status;
		this.content = content;
	}

	public ExecReport(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	public ExecReport(Status status) {
		this.status = status;

		if (status == Status.SUCCESS)
			this.message = "Success";
		else
			this.message = "Error";
	}

	// - Getters
	public Status getStatus() { return status; }
	public String getMessage() { return message; }
	public Object getContent() { return content;}

	// - Method
	public boolean isSuccess() {
		return getStatus() == Status.SUCCESS;
	}
}
