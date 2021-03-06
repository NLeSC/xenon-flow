package nl.esciencecenter.computeservice.service.staging;

import org.commonwl.cwl.Parameter;

import nl.esciencecenter.xenon.filesystems.Path;

public class FileToStringStagingObject extends BaseStagingObject {
	private Path sourcePath;
	private String targetString;

	public FileToStringStagingObject(Path sourcePath, String targetString, Parameter parameter) {
		super(parameter);
		this.sourcePath = sourcePath;
		this.targetString = targetString;
	}

	public Path getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(Path sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getTargetString() {
		return targetString;
	}

	public void setTargetString(String targetString) {
		this.targetString = targetString;
	}

	@Override
	public String toString() {
		return "FileToStringStagingObject [sourcePath=" + sourcePath + ", targetString=" + targetString
				+ ", parameter=" + parameter + "]";
	}
}